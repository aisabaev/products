package org.example.service;

import org.example.dao.ProductDao;
import org.example.models.Product;

import java.util.Scanner;

public class ProductService {
    ProductDao productDao = new ProductDao();
    Scanner scanner = new Scanner(System.in);

    public void saveProduct(){
        Product product = new Product();

        System.out.println("Enter product name: ");
        String productName = scanner.nextLine();
        if (!productName.isEmpty()) {
            product.setProductName(productName);
        }

        System.out.println("Enter product description: ");
        String productDescription = scanner.nextLine();
        if (!productDescription.isEmpty()){
            product.setDescription(productDescription);
        }

        System.out.println("Enter product price: ");
        double productPrice = scanner.nextDouble();
        if (productPrice != 0){
            product.setPrice(productPrice);
        }

        productDao.create(product);

        System.out.println("Product saved!");
    }

    public void noProductResponse (int id) {
        System.out.printf("There is no product with this ID %s", id);
        System.out.println();
    }

    public void getById(){
        System.out.println("Enter product id: ");
        int id = scanner.nextInt();

        Product product = productDao.getById(id);
        if (productDao.getById(id) != null){
            printProduct(product);
        } else {
            noProductResponse(id);
        }
    }

    public void updateProduct(){
        System.out.println("Enter product id: ");
        int id = scanner.nextInt();

        Product product = productDao.getById(id);
        if (productDao.getById(id) != null) {
            System.out.println("Enter product name: ");
            System.out.println();
            String productName = scanner.nextLine();
            if (!productName.isEmpty()){
                product.setProductName(productName);
            }
            System.out.println("Enter product description: ");
            String productDescription = scanner.nextLine();
            if (!productDescription.isEmpty()){
                product.setDescription(productDescription);
            }
            System.out.print("Enter product price: ");
            double productPrice = scanner.nextDouble();
            if (productPrice != 0){
                product.setPrice(productPrice);
            }
            productDao.updateProduct(product);
            System.out.println("Product updated!");
        } else {
            noProductResponse(id);
        }
    }

    public void deleteProductById(){
        System.out.println("Enter product id: ");
        int id = scanner.nextInt();
        productDao.deleteProduct(id);
        System.out.println("Product with id: " + id + "deleted");
    }

    private   void printProduct(Product product){
            System.out.println("--------------");
            System.out.println("product id: " + product.getProductId());
            System.out.println("product name: " + product.getProductName());
            System.out.println("product description: " + product.getDescription());
            System.out.println("product price: " + product.getPrice());
            System.out.println("---------------");

    }

    public void getAllProducts(){
        if (!productDao.getAllProducts().isEmpty()) {
            for (Product product: productDao.getAllProducts()) {
                printProduct(product);
            }
        } else {
            System.out.println("No products.");
        }
    }
}