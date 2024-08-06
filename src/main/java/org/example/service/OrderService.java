package org.example.service;


import org.example.dao.CustomerDao;
import org.example.dao.OrderDao;
import org.example.dao.ProductDao;
import org.example.exception.ExceptionCreate;
import org.example.exception.NotFoundByIDException;
import org.example.models.Customer;
import org.example.models.Order;
import org.example.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class OrderService {
    OrderDao orderDao = new OrderDao();
    Scanner sc = new Scanner(System.in);
    CustomerDao customerDao = new CustomerDao();
    ProductDao productDao = new ProductDao();

    // Создание заказа
    public void createOrder(){
        // Спрашиваем статус заказа
        System.out.println("Enter order status: ");
        String orderStatus = sc.nextLine();
        Order order = new Order();
        order.setOrderStatus(orderStatus);
        // Спрашиваем айди потребителя
        System.out.println("Enter customer's id: ");
        int customerId = sc.nextInt();
        // Подчищаем за sc.nextInt()
        sc.nextLine();

        // Проверка потребителя по ID
        try {
            Customer customer = customerDao.getById(customerId);
            if (customer==null){
                throw new NotFoundByIDException("customer", customerId);
            }
            order.setCustomer(customer);
        }catch (NotFoundByIDException ex){
            System.out.println(ex.getMessage());
        }

        // Устанавлиаем дату заказа
        setDate(order);
        // Создаем список и отдельным методом по циклу добавляем товары в список
        List<Product> productList = new ArrayList<>();
        productList = addArrayList(productList);
        order.setProductList(productList);

        // Проверяем сможем ли мы создать
        try {
            orderDao.create(order);
        }catch (ExceptionCreate ex){
            System.out.println(ex.getMessage());
        }

    }
    // Показать заказ по ID
    public void getOrderById(){
        System.out.println("Enter order id: ");
        int id = sc.nextInt();
        sc.nextLine();
        Order order = null;
        // Проверка на ID
        try {
            order = orderDao.getById(id);
            printOrder(order);
        } catch (NotFoundByIDException e) {
            System.out.println(e.getMessage());
        }

    }


    // Обновляет заказ
    public void updateOrder(){
        System.out.println("Enter order id: ");
        Order order = null;
        // Обработка исключения где ID не существует
        try {
            order = orderDao.getById(sc.nextInt());
        } catch (NotFoundByIDException e) {
            System.out.println(e.getMessage());
        }
        sc.nextLine();
        List<Product> productList = order.getProductList();

        System.out.println("Enter order status: ");
        String orderStatus = sc.nextLine();
        order.setOrderStatus(orderStatus);

        setDate(order);
        // Добавление в список продуктов по циклу отдельным методом
        productList = addArrayList(productList);
        order.setProductList(productList);
        orderDao.updateOrder(order);

    }

    // Удаляет заказ по ID
    public void deleteOrder(){
        System.out.println("Enter order id: ");
        int id = sc.nextInt();
        sc.nextLine();
        // Обработка исключения где ID не существует
        try{
            orderDao.deleteOrder(id);
            System.out.println("Order with id: " + id + " is deleted");
        }catch (NotFoundByIDException ex){
            System.out.println(ex.getMessage());
        }
    }

    // Добавляет товары в список, принимает уже существующий список, который будет дополняться
    private List<Product> addArrayList(List<Product> productList){
        boolean alive = true;
        sc.nextLine();
        // Добавление товара по циклу пока не напишут "exit"
        while(alive){
            System.out.println("Enter product's id: .../ And if you're done type 'exit' to exit");
            String productId = sc.nextLine();
            if (productId.equals("exit")){
                alive = false;
            } else  {
                // Введенная строка переводится в числовой формат
                int idProduct = Integer.parseInt(productId);
                // Проверка есть ли такой продукт по ID
                try {
                    Product product = productDao.getById(idProduct);
                    if (product == null){
                        throw new NotFoundByIDException("product", idProduct);
                    }
                    productList.add(product);
                    System.out.println("Product with id: " + idProduct + " added to list");
                }catch (NotFoundByIDException ex){
                    System.out.println(ex.getMessage());
                }
            }
        }
        return productList;
    }


    // Метод создает дату
    private Order setDate(Order order){
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        order.setDate(date);
        return order;
    }

    // Выводит все заказы
    public void getAll(){
       for(Order order : orderDao.getAll()){
           printOrder(order);
       }
    }


    // Вывод красиво в консоль
    private void printOrder(Order order){
        System.out.println("----------------------------");
        System.out.println("ORDER ID: " + order.getOrderId());
        System.out.println("ORDER STATUS: " + order.getOrderStatus());
        System.out.println("CUSTOMER ID: " + order.getCustomer().getCustomerId());
        List<Product> productList = order.getProductList();
        for(Product product: productList){
            printProduct(product);
        }
        System.out.println("----------------------------");


    }

    // Выводит красиво в консоль продукты
    protected void printProduct(Product product){

        System.out.println("ID: " + product.getProductId());
        System.out.println("NAME: " + product.getProductName());
        System.out.println("DESCRIPTION: " + product.getDescription());
        System.out.println("PRICE: " + product.getPrice());

    }
}
