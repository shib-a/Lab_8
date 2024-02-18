package org.example.exceptions;

public class InvalidAbsolutePathException extends Exception{
    public InvalidAbsolutePathException(String message){
        super("Invalid path. See 'help' for reference.");
    }
}
