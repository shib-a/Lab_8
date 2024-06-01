package client.commands;
import common.AbstractCommand;
import common.Feedbacker;
import common.UserData;

/**
 * Class for the "update" command
 */
public class Update extends AbstractCommand{
    public Update() {
        super("update (id)", "Updates the element at entered id.",true);
    }
    /**
     * Executes the "update" command
     *
     * @param arg
     * @param userData
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, UserData userData) {
//        if(arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference");
//        cl.printLn(">Updating a Human. Input new values:");
//        System.out.println(Integer.parseInt(arg.trim()));
//        try {
//            Human h= Ask.askHuman(cl,Integer.parseInt(arg.trim()));
//            if (h != null && h.validate()) {
//                cm.updateEl(h);
//                return new Feedbacker(">Updated successfully.");
//            } else return new Feedbacker(false, ">Failed to update. Invalid arguments.");
//        } catch (NumberFormatException | Ask.AskBreaker e){ return new Feedbacker(false,">Invalid number.");}
        return new Feedbacker("");
    }
}
