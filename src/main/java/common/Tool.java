package common;

import java.io.Serializable;

public class Tool extends Item implements Serializable {
    public ToolKinds kind;
    public StoneDurability canBreak;
    public Tool(String name, ToolKinds kind){
        super(name);
        this.kind = kind;
        switch (kind){
            case SHOVEL -> canBreak = StoneDurability.SOLID;
            case JACKHAMMER -> canBreak = StoneDurability.TOUGH;
            case DRILL -> canBreak = StoneDurability.HARD;
        }
    }

    @Override
    public String toString() {
        return "Tool{" + itemName+ ","+
                "kind=" + kind +
                ", canBreak=" + canBreak +
                '}';
    }

    /**
     * Determines whether a Tool instance was correctly created
     * @return
     */
    @Override
    public boolean validate() {
        if (this.kind==null) {return false;}
        return super.validate();
    }
}
