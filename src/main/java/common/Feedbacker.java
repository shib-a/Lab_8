package common;

import java.io.Serializable;

/**
 * This class is used for tracking the status of a command execution
 */
public class Feedbacker implements Serializable {
    private boolean isSuccessful;
    private String message;
    private User user;

    public Feedbacker(boolean isSuccessful, String message, User user){this.isSuccessful=isSuccessful;this.message=message;this.user=user;}
    public Feedbacker(String message, User user){this.isSuccessful=true;this.message=message;this.user=user;}
    public boolean getIsSuccessful() {
        return isSuccessful;
    }
    public String getMessage(){
        return message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "isSuccessful: " + isSuccessful +
                ", message: " + message + ", user: " + user;
    }
}
