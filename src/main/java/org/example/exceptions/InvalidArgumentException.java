package org.example.exceptions;

public class InvalidArgumentException extends Exception{
    public InvalidArgumentException(){
        super("Invalid argument. See 'help' for reference.");
    }
}
