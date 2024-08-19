package org.example;

import org.example.config.ProjectConfig;
import org.example.service.CustomerService;
import org.example.service.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * Hello world!
 *
 */

@Component

public class App {
    static Scanner scanner = new Scanner(System.in);
    static AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(ProjectConfig.class);
    public static void main( String[] args ) {
        boolean check = true;
        while (check){
            System.out.println("-----------------------");
            System.out.println("|         MENU        |");
            System.out.println("-----------------------");
            System.out.println("| 1 - Продукт         |");
            System.out.println("-----------------------");
            System.out.println("| 2 - ORDERS          |");
            System.out.println("-----------------------");
            System.out.println("| 3 - CUSTOMERS       |");
            System.out.println("------------------------");
            System.out.println("| 4 - QUIT            |");
            System.out.println("-----------------------");
            System.out.print("CHOOSE MENU: ");
            int choose = scanner.nextInt();
            switch (choose){
                case 1:
                    product();
                    break;
                case 3:
                    customer();
                    break;
                default:
                    System.out.println("There is no such command.");

            }
        }
    }

    private static void customer() {
        CustomerService customerService = context.getBean(CustomerService.class);
        boolean check = true;
        while (check){
            System.out.println("-----------------------");
            System.out.println("|    MENU CUSTOMER    |");
            System.out.println("-----------------------");
            System.out.println("| 1 - CREATE          |");
            System.out.println("-----------------------");
            System.out.println("| 2 - GET BY ID       |");
            System.out.println("-----------------------");
            System.out.println("| 3 - UPDATE          |");
            System.out.println("-----------------------");
            System.out.println("| 4 - DELETE          |");
            System.out.println("-----------------------");
            System.out.println("| 5 - GET ALL         |");
            System.out.println("-----------------------");
            System.out.println("| 6 - QUIT            |");
            System.out.println("-----------------------");
            System.out.print("CHOOSE CUSTOMER MENU: ");
            int choose = scanner.nextInt();
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
                    break;
                case 6:
                    check = false;
                    break;
                default:
                    System.out.println("There is no such command.");

            }
        }
    }

    private static void product() {
        ProductService productService = context.getBean(ProductService.class);

        boolean check = true;
        while (check){
            System.out.println("-----------------------");
            System.out.println("|    MENU PRODUCT    |");
            System.out.println("-----------------------");
            System.out.println("| 1 - CREATE          |");
            System.out.println("-----------------------");
            System.out.println("| 2 - GET BY ID       |");
            System.out.println("-----------------------");
            System.out.println("| 3 - UPDATE          |");
            System.out.println("-----------------------");
            System.out.println("| 4 - DELETE          |");
            System.out.println("-----------------------");
            System.out.println("| 5 - GET ALL         |");
            System.out.println("-----------------------");
            System.out.println("| 6 - QUIT            |");
            System.out.println("-----------------------");
            System.out.print("CHOOSE PRODUCT MENU: ");
            int choose = scanner.nextInt();
            switch (choose){
                case 1:
                    productService.saveProduct();
                    break;
                case 2:
                    productService.getById();
                    break;
                case 3:
                    productService.updateProduct();
                    break;
                case 4:
                    productService.deleteProductById();
                    break;
                case 5:
                    productService.getAllProducts();
                    break;
                case 6:
                    check = false;
                    break;
                default:
                    System.out.println("There is no such command.");

            }
        }
    }
}
