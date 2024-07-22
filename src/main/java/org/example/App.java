package org.example;

import org.example.models.Customer;
import org.example.models.Order;
import org.example.models.Product;
import org.example.service.CustomerService;
import org.example.service.OrderService;
import org.example.service.ProductService;

import java.util.Scanner;

/**
 * Hello world!
 *
 */

public class App {
    static Scanner sc = new Scanner(System.in);

    public static void main( String[] args ) {

        boolean check = true;

        while(check){
            System.out.println("---------------------");
            System.out.println("        MENU         ");
            System.out.println("---------------------");
            System.out.println("| 1     PRODUCTS     ");
            System.out.println("---------------------");
            System.out.println("| 2     ORDERS       ");
            System.out.println("---------------------");
            System.out.println("| 3     CUSTOMERS    ");
            System.out.println("---------------------");
            System.out.println("| 4     QUIT         ");
            System.out.println("---------------------");
            System.out.print("CHOOSE MENU: ");
            int choose = sc.nextInt();
            sc.nextLine();
            switch (choose){
                case 1:
                    products();
                    break;
                case 2:
                    orders();
                    break;
                case 3:
                    customer();
                    break;
                case 4:
                    check = false;
                    break;
            }

        }
    }


    private static void products(){
        ProductService productService = new ProductService();
        boolean check = true;
        while(check){
            System.out.println("---------------------");
            System.out.println("    MENU PRODUCTS    ");
            System.out.println("---------------------");
            System.out.println("| 1     CREATE       ");
            System.out.println("---------------------");
            System.out.println("| 2     GET BY ID    ");
            System.out.println("---------------------");
            System.out.println("| 3     UPDATE       ");
            System.out.println("---------------------");
            System.out.println("| 4     DELETE       ");
            System.out.println("---------------------");
            System.out.println("| 5     GET ALL      ");
            System.out.println("---------------------");
            System.out.println("| 6     QUIT         ");
            System.out.println("---------------------");
            System.out.println("CHOOSE PRODUCT MENU: ");
            int choose = sc.nextInt();
            sc.nextLine();
            switch (choose){
                case 1:
                    productService.createProduct();
                    break;
                case 2:
                    productService.getById();
                    break;
                case 3:
                    productService.updateProduct();
                    break;
                case 4:
                    productService.deleteProduct();
                    break;
                case 5:
                    productService.getAll();
                    break;
                case 6:
                    check = false;
                    break;
            }

        }
    }
    private static void orders(){
        OrderService orderService = new OrderService();
        boolean check = true;
        while(check){
            System.out.println("---------------------");
            System.out.println("    MENU ORDERS      ");
            System.out.println("---------------------");
            System.out.println("| 1     CREATE       ");
            System.out.println("---------------------");
            System.out.println("| 2     GET BY ID    ");
            System.out.println("---------------------");
            System.out.println("| 3     UPDATE       ");
            System.out.println("---------------------");
            System.out.println("| 4     DELETE       ");
            System.out.println("---------------------");
            System.out.println("| 5     GET ALL      ");
            System.out.println("---------------------");
            System.out.println("| 6     QUIT         ");
            System.out.println("---------------------");
            System.out.println("CHOOSE ORDERS MENU: ");
            int choose = sc.nextInt();
            sc.nextLine();
            switch (choose){
                case 1:
                    orderService.createOrder();
                    break;
                case 2:
                    orderService.getOrderById();
                    break;
                case 3:
                    orderService.updateOrder();
                    break;
                case 4:
                    orderService.deleteOrder();
                    break;
                case 5:
                    orderService.getAll();
                    break;
                case 6:
                    check = false;
                    break;
            }

        }
    }
    private static void customer(){
        CustomerService customerService = new CustomerService();
        boolean check = true;
        while(check){
            System.out.println("---------------------");
            System.out.println("    MENU CUSTOMER    ");
            System.out.println("---------------------");
            System.out.println("| 1     CREATE       ");
            System.out.println("---------------------");
            System.out.println("| 2     GET BY ID    ");
            System.out.println("---------------------");
            System.out.println("| 3     UPDATE       ");
            System.out.println("---------------------");
            System.out.println("| 4     DELETE       ");
            System.out.println("---------------------");
            System.out.println("| 5     GET ALL      ");
            System.out.println("---------------------");
            System.out.println("| 6     QUIT         ");
            System.out.println("---------------------");
            System.out.println("CHOOSE CUSTOMER MENU: ");
            int choose = sc.nextInt();
            sc.nextLine();
            switch (choose){
                case 1:
                    customerService.saveCustomer();
                    break;
                case 2:
                    customerService.getById();
                    break;
                case 3:
                    customerService.updateCustomer();
                    break;
                case 4:
                    customerService.deleteCustomerById();
                    break;
                case 5:
                    customerService.getAll();
                    break;
                case 6:
                    check = false;
                    break;
            }

        }
    }
}
