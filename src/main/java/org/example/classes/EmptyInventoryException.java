package org.example.classes;

public class EmptyInventoryException extends Exception{
    public EmptyInventoryException(String msg){
        super(msg);
    }
}
