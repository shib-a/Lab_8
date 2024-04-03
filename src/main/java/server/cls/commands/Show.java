package server.cls.commands;

import org.example.CommandLine;
import org.example.classes.CollectionManager;
import org.example.classes.Human;

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
     * @param arg
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String[] arg) {
        if (!arg[1].isEmpty()) return new Feedbacker(false, ">Wrong argument usage. See 'help' for reference.");
        if (cm.getCollection().isEmpty()) return new Feedbacker(">Collection is empty.");
        else{for(Human el:cm.getCollection()){
            cl.printLn(el.toString());
        }
        return new Feedbacker(">Elements shown.");}
    }
}
