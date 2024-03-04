package org.example.commands;

/**
 * An abstract class for a command
 */
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

    /**
     * Executes the command
     * @param args
     * @return Feedbacker
     */
    public Feedbacker execute(String[] args){return new Feedbacker("");};
    @Override
    public String toString() {
        return "AbstractCommand{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
