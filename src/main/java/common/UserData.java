package common;

import java.io.Serializable;

public class UserData implements Serializable {
    String name;
    Access permissinon;
    public UserData(String name, Access perm){this.name=name;this.permissinon = perm;}
    public void setPermissinon(Access p){this.permissinon = p;}

    public String getName() {
        return name;
    }

    public Access getPermissinon() {
        return permissinon;
    }
}
