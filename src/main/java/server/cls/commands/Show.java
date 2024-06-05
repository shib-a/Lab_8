package server.cls.commands;

import common.AbstractCommand;
import common.Feedbacker;
import common.Human;
import common.User;
import server.*;
import server.managers.CollectionManager;

import java.util.Objects;

/**
 * Executes the "show" command
 */
public class Show extends AbstractCommand {
    private CommandLine cl;
    private CollectionManager cm;
    public Show(CommandLine cl, CollectionManager cm) {
        super("show", "Shows the elements of collection.");
        this.cl = cl;
        this.cm=cm;
    }
    /**
     * Executes the "show" command
     *
     * @param arg
     * @param user
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, User user) {
        if (!arg.isEmpty()) return new Feedbacker(false, ">Wrong argument usage. See 'help' for reference.", user);
        if (cm.getCollection().isEmpty()) return new Feedbacker(">Collection is empty.", user);
        StringBuilder str = new StringBuilder();
        str.append(cm.getCollection().stream().map(Human::toCsvStr).reduce((a, b)-> a+"\n"+b).get());
        return new Feedbacker(str.toString(), user);
    }
}
