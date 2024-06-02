package client.commands;
import common.AbstractCommand;
import client.CommandLine;
import client.classes.CollectionManager;
import common.Feedbacker;
import common.User;

/**
 * Executes the "clear" command
 */
public class SaveLogToFile extends AbstractCommand{
    private CommandLine cl;
    private CollectionManager cm;
    public SaveLogToFile(CommandLine cl, CollectionManager cm) {
        super("save", "Saves collection by writing it into a file ");
        this.cl = cl;
        this.cm=cm;
    }
    /**
     * Executes the "clear" command
     *
     * @param arg
     * @param user
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, User user) {
        if (!arg.isEmpty()) return new Feedbacker(false, "Wrong argument usage. see 'help' for reference");
        cm.saveToFile();
        return new Feedbacker("Collection saved");
    }
}
