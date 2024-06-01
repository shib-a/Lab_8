package client.exceptions;

public class RecursiveLimitExceededException extends Exception{
    public RecursiveLimitExceededException(){
        super("Recursive depth exceeded.");
    }
}
