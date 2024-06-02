package server.cls.commands;

import common.*;
import server.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Class for the "sort" command
 */
public class Sort extends AbstractCommand {
    private CommandLine cl;
    private CollectionManager cm;
    public Sort(CommandLine cl, CollectionManager cm) {
        super("sort", "Sorts collection by default (in this case by DAMAGE stat values).");
        this.cl = cl;
        this.cm=cm;
    }
    /**
     * Executes the "sort" command
     *
     * @param arg
     * @param user
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, User user) {
            if(!arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.");
            cm.setCollection((ArrayList<Human>) cm.getCollection().stream().sorted(new HumanComparator()).collect(Collectors.toList()));
            return new Feedbacker(">Sorted successfully.");
    }
}
