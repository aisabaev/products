package org.example.models;



public class Customer {
    public Customer(int customerId, String customerName, String contactInfo) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.contactInfo = contactInfo;
    }
    public Customer(){

    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    private int customerId;
    private String customerName;
    private String contactInfo;
}
