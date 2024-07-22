package org.example;

import org.example.service.CustomerService;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    static Scanner scanner = new Scanner(System.in);
    public static void main( String[] args ) {
        boolean check = true;
        while (check){
            System.out.println("-----------------------");
            System.out.println("|         MENU        |");
            System.out.println("-----------------------");
            System.out.println("| 1 - PRODUCTS        |");
            System.out.println("-----------------------");
            System.out.println("| 2 - ORDERS          |");
            System.out.println("-----------------------");
            System.out.println("| 3 - CUSTOMERS       |");
            System.out.println("-----------------------");
            System.out.println("| 4 - QUIT            |");
            System.out.println("-----------------------");
            System.out.print("CHOOSE MENU: ");
            int choose = scanner.nextInt();
            switch (choose){
                case 3:
                    customer();
                    break;

            }
        }
    }

    private static void customer() {
        CustomerService customerService = new CustomerService();
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
            System.out.println("| 6 - QUIT     ----       |");
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

            }
        }
    }
}
