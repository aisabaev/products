package org.example.service;

import org.example.dao.CustomerDao;
import org.example.models.Customer;

import java.util.Scanner;

public class CustomerService {

    CustomerDao customerDao = new CustomerDao();
    Scanner scanner = new Scanner(System.in);

    public void saveCustomer(){
        Customer customer = new Customer();
        System.out.println("Enter customer name: ");
        customer.setCustomerName(scanner.nextLine());

        System.out.println("Enter contact info: ");
        customer.setContactInfo(scanner.nextLine());
        customerDao.create(customer);

        System.out.println("Customer saved!");
    }

    public void getById(){
        System.out.println("Enter customer id: ");
        int id = scanner.nextInt();

        Customer customer = customerDao.getById(id);
        printCustomer(customer);
    }

    public void updateCustomer(){
        System.out.println("Enter customer id: ");
        int id = scanner.nextInt();
        Customer customer = customerDao.getById(id);
        printCustomer(customer);
        System.out.println("Enter customer name: ");
        String customerName = scanner.nextLine();
        if (!customerName.isEmpty()){
            customer.setCustomerName(customerName);
        }
        System.out.println("Enter contact info: ");
        String contactInfo = scanner.nextLine();
        if (!contactInfo.isEmpty()){
            customer.setContactInfo(contactInfo);
        }
        customerDao.updateCustomer(customer);
        System.out.println("Customer updated!");
    }

    public void deleteCustomerById(){
        System.out.println("Enter customer id: ");
        int id = scanner.nextInt();
        customerDao.deleteCustomer(id);
        System.out.println("Customer with id: " + id + "deleted");
    }

    private   void printCustomer(Customer customer){
        System.out.println("--------------");
        System.out.println("customer id: " + customer.getCustomerId());
        System.out.println("customer name: " + customer.getCustomerName());
        System.out.println("contact info: " + customer.getContactInfo());
        System.out.println("---------------");
    }
}
