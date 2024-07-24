package org.example.dao;


import org.example.models.Order;
import org.example.models.Product;
import org.example.utils.DbConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    Connection connection = null;
    CustomerDao customerDao = new CustomerDao();
    ProductDao productDao = new ProductDao();

    public OrderDao() {
        DbConnector connector = new DbConnector();
        this.connection = connector.getConnection();
        String sql =
                "CREATE TABLE IF NOT EXISTS product(" +
                        "product_id BIGSERIAL primary key," +
                        "product_name VARCHAR NOT NULL," +
                        "description VARCHAR ," +
                        "price double precision NOT NULL );";
        String sqlOrder =
                "CREATE TABLE IF NOT EXISTS orders(" +
                        "order_id BIGSERIAL primary key," +
                        "order_status VARCHAR NOT NULL," +
                        "order_date DATE NOT NULL," +
                        "customer_id INTEGER NOT NULL CONSTRAINT fk_customer_order REFERENCES customer(customer_id));";
        String productSql =
                "CREATE TABLE IF NOT EXISTS orders_products(" +
                        "product_id INTEGER NOT NULL CONSTRAINT fk_product_order REFERENCES product(product_id)," +
                        "order_id INTEGER NOT NULL CONSTRAINT fk_order_product REFERENCES orders(order_id));";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.execute(sqlOrder);
            statement.execute(productSql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void create(Order order) {
        String createOrder = "insert into orders(order_status,order_date,customer_id) values(?,?,?) RETURNING order_id";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(createOrder, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, order.getOrderStatus());
            preparedStatement.setDate(2, order.getDate());
            preparedStatement.setInt(3, order.getCustomer().getCustomerId());
            // Нижний preparedStatement не удалять, он нужен для верхнего Query
            preparedStatement.execute(); // <- Вот его не надо удалять

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                // начинаем писать в таблицу many to many
                String createManyToManyRecord = "insert into orders_products (product_id, order_id) values (?,?)";
                for (Product product : order.getProductList()) {
                    System.out.println(product);
                    PreparedStatement preparedStatement1 = connection.prepareStatement(createManyToManyRecord);
                    preparedStatement1.setInt(1, product.getProductId());
                    preparedStatement1.setInt(2, id);
                    preparedStatement1.execute();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Order getById(int id) {
        String sql = "select * from orders where order_id=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Order order = null;
            if (resultSet.next()) {
                order = new Order();
                order.setCustomer(customerDao.getById(resultSet.getInt("customer_id")));
                order.setOrderStatus(resultSet.getString("order_status"));
                order.setOrderId(resultSet.getInt("order_id"));
                order.setDate(resultSet.getDate("order_date"));
            }
            String getProducts = "select * from orders_products where order_id=?;";
            PreparedStatement preparedStatement1 = connection.prepareStatement(getProducts);
            preparedStatement1.setInt(1, id);
            ResultSet resultSetProducts = preparedStatement1.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSetProducts.next()) {
                products.add(productDao.getById(resultSetProducts.getInt("product_id")));
            }
            order.setProductList(products);
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateOrder(Order order) {
        String sql = "UPDATE order SET order_status = ?, order_date = ?, customer_id = ? WHERE order_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, order.getOrderStatus());
            preparedStatement.setDate(2, order.getDate());
            preparedStatement.setInt(3, order.getCustomer().getCustomerId());
            preparedStatement.setInt(4, order.getOrderId());
            preparedStatement.executeQuery();


            List<Product> productList = order.getProductList();
            String getProducts = "UPDATE orders_products SET product_id = ? where order_id = ?";
            for (Product product : productList) {
                PreparedStatement preparedStatement1 = connection.prepareStatement(getProducts);
                preparedStatement1.setInt(1, product.getProductId());
                preparedStatement1.setInt(2, order.getOrderId());
                preparedStatement1.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteOrder(int id) {

        String delete_many_to_many = "DELETE FROM orders_products WHERE order_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete_many_to_many);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            String sql = "DELETE FROM orders WHERE order_id = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
            preparedStatement1.setInt(1, id);
            preparedStatement1.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public List<Order> getAll() {
        String sql = "select * from orders";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                Order order = new Order();
                order.setCustomer(customerDao.getById(resultSet.getInt("customer_id")));
                order.setOrderStatus(resultSet.getString("order_status"));
                order.setOrderId(resultSet.getInt("order_id"));
                order.setDate(resultSet.getDate("order_date"));
                String secondTable = "select * from orders_products where order_id = ?";
                PreparedStatement preparedStatement1 = connection.prepareStatement(secondTable);
                preparedStatement1.setInt(1, resultSet.getInt("order_id"));
                ResultSet resultSet1 = preparedStatement1.executeQuery();
                List<Product> products = new ArrayList<>();
                while (resultSet1.next()) {
                    Product product = productDao.getById(resultSet1.getInt("product_id"));
                    products.add(product);
                }
                order.setProductList(products);
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
