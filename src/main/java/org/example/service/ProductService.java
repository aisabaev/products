package org.example.service;

import org.example.dao.ProductDao;
import org.example.models.Product;

import java.util.List;
import java.util.Scanner;

public class ProductService {
    Scanner sc = new Scanner(System.in);
    ProductDao productDao = new ProductDao();
    public void createProduct(){
        Product product = new Product();
        System.out.println("Enter product name: ");
        String productName = sc.nextLine();
        product.setProductName(productName);
        System.out.println("Enter product description: ");
        String description = sc.nextLine();
        product.setDescription(description);
        System.out.println("Enter product price: ");
        Double price = sc.nextDouble();
        sc.nextLine();
        product.setPrice(price);
        productDao.create(product);
        System.out.println("Product created successfully");
    }

    public void getById(){
        System.out.println("Enter product's id: ");
        int id = sc.nextInt();
        sc.nextLine();
        Product product = productDao.getById(id);
        printProduct(product);
    }



    public void updateProduct(){
        System.out.println("Enter product's id: ");
        int id = sc.nextInt();
        sc.nextLine();
        Product product = productDao.getById(id);
        printProduct(product);

        System.out.println("Enter name: ");
        String name = sc.nextLine();
        if(!name.isEmpty()){
            product.setProductName(name);
        }
        System.out.println("Enter description: ");
        String description = sc.nextLine();
        if(!description.isEmpty()){
            product.setDescription(description);
        }
        System.out.println("Enter price: ");
        Double price = sc.nextDouble();
        sc.nextLine();
        if(!price.isNaN()){
            product.setPrice(price);
        }

        productDao.updateProduct(product);
        System.out.println("Product updated");

    }

    public void deleteProduct(){
        System.out.println("Enter id product: ");
        int id = sc.nextInt();
        sc.nextLine();
        productDao.deleteProduct(id);
    }


    public void getAll(){
        List<Product> products = productDao.getAll();
        for(Product product : products){
            printProduct(product);
        }
    }

    protected void printProduct(Product product){
        System.out.println("----------------------------");
        System.out.println("ID: " + product.getProductId());
        System.out.println("NAME: " + product.getProductName());
        System.out.println("DESCRIPTION: " + product.getDescription());
        System.out.println("PRICE: " + product.getPrice());
        System.out.println("----------------------------");
    }


}
