package client.commands;
import common.AbstractCommand;
import common.Feedbacker;
import common.User;

/**
 * Class for the "sort" command
 */
public class Sort extends AbstractCommand{
    public Sort() {
        super("sort", "Sorts collection by default (in this case by DAMAGE stat values).");
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
//            if(!arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.");
//            else {
////                cm.getCollection().sort(new HumanComparator());
//            }
//            return new Feedbacker(">Sorted successfully.");
        return new Feedbacker("", user);
    }
}
