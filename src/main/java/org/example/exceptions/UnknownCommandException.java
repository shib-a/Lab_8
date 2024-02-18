package org.example.exceptions;

public class UnknownCommandException extends Exception{
    public UnknownCommandException(){
        super("Unknown command. Check your spelling or amount of arguments. See 'help' for reference");
    }
}
