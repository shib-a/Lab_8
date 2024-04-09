package client.commands;
import common.AbstractCommand;
import common.Feedbacker;
/**
 * Class for the "exit" command
 */
public class Exit extends AbstractCommand {
    public Exit() {
        super("exit", "Stops the program without saving the collection.");
    }
    /**
     * Executes the "exit" command
     * @param arg
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg) {
//        if(!arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.");
        return new Feedbacker("");
    }
}
