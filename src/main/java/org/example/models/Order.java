package org.example.models;

import org.example.utils.DbConnector;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Order {
    public Order() {

    }

    private int orderId;
    private String orderStatus;
    private Date date;
    private Customer customer;
    private List<Product> productList;

    public Order(int orderId, String orderStatus, Date date, Customer customer, List<Product> productList) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.date = date;
        this.customer = customer;
        this.productList = productList;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
