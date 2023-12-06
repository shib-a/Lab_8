package org.example.classes;

public class Tool extends Item{
    ToolKinds kind;
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
}
