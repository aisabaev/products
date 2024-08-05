package org.example.exception;

public class NotFoundByIDException extends Exception{
    private int id;
    public int getID(){
        return id;
    }
    public NotFoundByIDException(String msg, int id){
        super("Couldn't find the " + msg + " by given ID. ID = " + id);
        this.id = id;
    }

}
