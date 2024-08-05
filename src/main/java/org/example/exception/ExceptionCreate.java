package org.example.exception;

public class ExceptionCreate extends Exception{
    public ExceptionCreate(String src) {
        super("Couldn't create " + src);
    }
}
