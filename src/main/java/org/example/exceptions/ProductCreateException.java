package org.example.exceptions;

public class ProductCreateException extends Exception {
    public ProductCreateException() {
        super("Не удалось создать продукта!");
    }
}
