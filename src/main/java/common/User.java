package common;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private Access access;
    private boolean isVerified;
    int rollAmount;
    public User(String name, Access access){
        this.name=name;
        this.access=access;
    }
    public User(String name, Access access, boolean isVerified){
        this.name=name;
        this.access=access;
        this.isVerified=isVerified;
    }
    public void setAccess(Access access) {
        this.access = access;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public void setRollAmount(int rollAmount) {
        this.rollAmount = rollAmount;
    }

    public int getRollAmount() {
        return rollAmount;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public Access getAccess() {
        return access;
    }

    public String getName() {
        return name;
    }
}
