package client.exceptions;

public class EmptyInventoryException extends Exception{
    public EmptyInventoryException(String msg){
        super(msg);
    }
}
