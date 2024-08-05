package org.example.exception;

import java.lang.reflect.Array;

public class CommandNotFound extends Exception{
    public CommandNotFound(){
        super("Input command not found, please try again!");
    }
}
