package org.example.service;

import org.example.dao.CustomerDao;
import org.example.exception.ExceptionCreate;
import org.example.exception.NotFoundByIDException;
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
        try {
            boolean result = customerDao.create(customer);
            if (!result){
                throw new ExceptionCreate("customer");
            }
            System.out.println("Customer saved!");
        }catch (ExceptionCreate ex){
            System.out.println(ex.getMessage());
        }



    }

    public void getById(){
        System.out.println("Enter customer id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            Customer customer = customerDao.getById(id);
            if (customer==null){
                throw new NotFoundByIDException("customer", id);
            }
            printCustomer(customer);
        }catch (NotFoundByIDException ex){
            System.out.println(ex.getMessage());
        }

    }

    public void updateCustomer(){
        System.out.println("Enter customer id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            Customer customer = customerDao.getById(id);
            printCustomer(customer);
            if (customer==null){
                throw new NotFoundByIDException("customer", id);
            }
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
        } catch (NotFoundByIDException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteCustomerById(){
        System.out.println("Enter customer id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            customerDao.deleteCustomer(id);
            System.out.println("Customer with id: " + id + "deleted");
        } catch (NotFoundByIDException e) {
            System.out.println(e.getMessage());
        }
    }



    public void getAll(){
        for (Customer customer : customerDao.getAll()) {
            printCustomer(customer);
        }
    }
    private void printCustomer(Customer customer){
        System.out.println("--------------");
        System.out.println("customer id: " + customer.getCustomerId());
        System.out.println("customer name: " + customer.getCustomerName());
        System.out.println("contact info: " + customer.getContactInfo());
        System.out.println("---------------");
    }
}
