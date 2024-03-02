package org.example.commands;

public class Feedbacker {
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
