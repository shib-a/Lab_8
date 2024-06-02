package common;

import java.io.Serializable;

/**
 * An abstract class for a command
 */
public abstract class AbstractCommand implements Serializable {
//    private static final long serialVersionUID = 1L;

    public final String name;
    private final String desc;
    private boolean isNeedData;
    public AbstractCommand(String name, String desc, boolean isNeedData){
        this.name = name; this.desc = desc; this.isNeedData=isNeedData;
    }
    public AbstractCommand(String name, String desc){
        this.name = name; this.desc = desc; this.isNeedData=false;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public boolean getIsNeedData() {
        return isNeedData;
    }

    /**
     * Executes the command
     *
     * @param args
     * @param user
     * @return Feedbacker
     */
    public Feedbacker execute(String args, User user){return new Feedbacker("");};
    @Override
    public String toString() {
        return "AbstractCommand{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
