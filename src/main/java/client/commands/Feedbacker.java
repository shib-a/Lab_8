package client.commands;

import java.io.Serializable;

/**
 * This class is used for tracking the status of a command execution
 */
public class Feedbacker implements Serializable {
    private boolean isSuccessful;
    private String message;
    public Feedbacker(boolean isSuccessful, String message){this.isSuccessful=isSuccessful;this.message=message;}
    public Feedbacker(String message){this.isSuccessful=true;this.message=message;}
    public boolean getIsSuccessful() {
        return isSuccessful;
    }
    public String getMessage(){
        return message;
    }
    @Override
    public String toString() {
        return "isSuccessful: " + isSuccessful +
                ", message: " + message;
    }
}
