package common;

import java.io.Serializable;

public class HumanData implements Serializable {
    String name;
    Status ptt;
    Color rt;
    String stats;
    Boolean isAl;
    String dc;
    UserData userData;
    public HumanData(String name, Status ptt, Color rt, String stats, Boolean isAl, String dc, UserData ud){
        this.name=name;
        this.ptt=ptt;
        this.rt=rt;
        this.stats=stats;
        this.isAl=isAl;
        this.dc=dc;
        this.userData = ud;
    }
    public HumanData(String name, Status ptt, Color rt, String stats, Boolean isAl, String dc){
        this.name=name;
        this.ptt=ptt;
        this.rt=rt;
        this.stats=stats;
        this.isAl=isAl;
        this.dc=dc;
    }

    public String getName() {
        return name;
    }

    public Boolean getAl() {
        return isAl;
    }

    public String getStats() {
        return stats;
    }

    public UserData getUserData() {
        return userData;
    }

    public String getDc() {
        return dc;
    }

    public Color getRt() {
        return rt;
    }

    public Status getPtt() {
        return ptt;
    }

    @Override
    public String toString() {
        return "HumanData{" +
                "name='" + name + '\'' +
                ", ptt=" + ptt +
                ", rt=" + rt +
                ", stats='" + stats + '\'' +
                ", isAl=" + isAl +
                ", dc='" + dc + '\'' +
                '}';
    }
}