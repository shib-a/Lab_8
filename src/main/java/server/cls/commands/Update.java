package server.cls.commands;

import common.*;
import server.*;

/**
 * Class for the "update" command
 */
public class Update extends AbstractCommand {
    private transient CommandLine cl;
    private transient CollectionManager cm;
    private RuntimeEnv re;
    public Update(CommandLine cl, CollectionManager cm, RuntimeEnv re) {
        super("update (id)", "Updates the element at entered id.",true);
        this.cl = cl;
        this.cm=cm;
        this.re=re;
    }
    /**
     * Executes the "update" command
     *
     * @param arg
     * @param user
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, User user) {
        if(arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference");
        cl.printLn(">Updating a Human. Input new values:");
        System.out.println(Integer.parseInt(arg.trim()));
        try {
            Human h=Ask.askHuman(re.getCurrHumanData(),Integer.parseInt(arg.trim()), user);
            if (h != null && h.validate()) {
                cm.updateEl(h, user);
                return new Feedbacker(">Updated successfully.");
            } else return new Feedbacker(false, ">Failed to update. Invalid arguments.");
        } catch (NumberFormatException | Ask.AskBreaker e){ return new Feedbacker(false,">Invalid number.");}
    }
}
