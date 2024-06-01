package common;

import java.io.Serializable;

public class UserData implements Serializable {
    String name;
    Permissinons permissinon;
    public UserData(String name, Permissinons perm){this.name=name;this.permissinon = perm;}
    public void setPermissinon(Permissinons p){this.permissinon = p;}

    public String getName() {
        return name;
    }

    public Permissinons getPermissinon() {
        return permissinon;
    }
}
