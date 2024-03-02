package org.example.commands;

import org.example.collection.HumanCollection;

public abstract class AbstractCommand {
    public final String name;
    private final String desc;
    public AbstractCommand(String name, String desc){
        this.name = name; this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
    public Feedbacker execute(String[] args){return new Feedbacker("");};
    @Override
    public String toString() {
        return "AbstractCommand{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
