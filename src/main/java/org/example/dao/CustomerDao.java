package org.example.dao;


import org.example.exception.NotFoundByIDException;
import org.example.models.Customer;
import org.example.utils.DbConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    Connection connection = null;

    public CustomerDao(){
        DbConnector connector = new DbConnector();
        this.connection = connector.getConnection();
        String sql =
                "CREATE TABLE IF NOT EXISTS customer(" +
                        "customer_id BIGSERIAL primary key," +
                        "customer_name  VARCHAR NOT NULL," +
                        "contact_info VARCHAR NOT NULL);";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean create(Customer customer){
        String sql = "insert into customer(customer_name, contact_info) values (? , ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getCustomerName());
            preparedStatement.setString(2, customer.getContactInfo());
            boolean resultSet = preparedStatement.execute();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Customer getById(int id){
        String sql = "select * from customer where customer_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Customer customer = new Customer();
                customer.setCustomerId(resultSet.getInt("customer_id"));
                customer.setCustomerName(resultSet.getString("customer_name"));
                customer.setContactInfo(resultSet.getString("contact_info"));
                return customer;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void updateCustomer(Customer customer){
        String sql = "update customer set customer_name=?, contact_info=? where customer_id=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getCustomerName());
            preparedStatement.setString(2, customer.getContactInfo());
            preparedStatement.setInt(3, customer.getCustomerId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public List<Customer> getAll(){
        try {
            String sql = "select * from customer";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Customer> customers = new ArrayList<>();

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(resultSet.getInt("customer_id"));
                customer.setCustomerName(resultSet.getString("customer_name"));
                customer.setContactInfo(resultSet.getString("contact_info"));
                customers.add(customer);
            }
            return customers;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void deleteCustomer(int id) throws NotFoundByIDException{
        String sql = "delete from customer where customer_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            boolean result = preparedStatement.execute();
            if (!result){
                throw new NotFoundByIDException("customer", id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
