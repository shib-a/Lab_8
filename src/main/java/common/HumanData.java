package common;

import java.io.Serializable;
import java.util.Arrays;

public class HumanData implements Serializable {
    String name;
    ToolKinds ptt;
    ResearcherType rt;
    String stats;
    Boolean isAl;
    String dc;
    public HumanData(String name, ToolKinds ptt, ResearcherType rt, String stats, Boolean isAl, String dc){
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

    public String getDc() {
        return dc;
    }

    public ResearcherType getRt() {
        return rt;
    }

    public ToolKinds getPtt() {
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