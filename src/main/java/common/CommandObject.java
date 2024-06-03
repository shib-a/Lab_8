package common;

import com.sun.source.tree.UsesTree;

import java.io.Serializable;

public class CommandObject implements Serializable {
    private static final long serialVersionUID = 1L;
    String argument;
    AbstractCommand command;
    HumanData hd;
    User user;
    public CommandObject(AbstractCommand command, String arg, HumanData hd, User ud){
        this.argument = arg;
        this.command = command;
        this.hd=hd;
        this.user = ud;
    }
    public String deriveCommand(){
        return command + argument;
    }

    @Override
    public String toString() {
        return "CommandObject{" +
                "argument='" + argument + '\'' +
                ", command=" + command + ", userData = " + user.getName()+
                '}';
    }

    public AbstractCommand getCommand() {
        return command;
    }

    public String getArgument() {
        return argument;
    }

    public HumanData getHd() {
        return hd;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setHd(HumanData hd) {
        this.hd = hd;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }
}
