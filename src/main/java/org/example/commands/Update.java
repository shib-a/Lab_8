package org.example.commands;

import org.example.CommandLine;
import org.example.classes.Ask;
import org.example.classes.CollectionManager;
import org.example.classes.Human;
/**
 * Class for the "update" command
 */
public class Update extends AbstractCommand{
    private CommandLine cl;
    private CollectionManager cm;
    public Update(CommandLine cl, CollectionManager cm) {
        super("update (id)", "Updates the element at entered id.");
        this.cl = cl;
        this.cm=cm;
    }
    /**
     * Executes the "update" command
     * @param arg
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String[] arg) {
        if(arg[1].isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference");
        cl.printLn(">Updating a Human. Input new values:");
        System.out.println(Integer.parseInt(arg[1].trim()));
        try {
            Human h=Ask.askHuman(cl,Integer.parseInt(arg[1].trim()));
            if (h != null && h.validate()) {
                cm.updateEl(h);
                return new Feedbacker(">Updated successfully.");
            } else return new Feedbacker(false, ">Failed to update. Invalid arguments.");
        } catch (NumberFormatException | Ask.AskBreaker e){ return new Feedbacker(false,">Invalid number.");}
    }
}
