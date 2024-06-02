package server.cls.commands;

import common.*;
import server.CommandLine;
import server.Ask;
import server.CollectionManager;

import java.util.Arrays;

/**
 * Class for "add" command
 */
public class Add extends AbstractCommand {
    private CommandLine cl;
    private CollectionManager cm;
    private RuntimeEnv re;
    private final int warrantConst = 10;
    public Add(CommandLine cl, CollectionManager cm, RuntimeEnv re) {
        super("add", "Add a new element to collection.");
        this.cl = cl;
        this.cm=cm;
        this.re=re;
    }

    /**
     * Executes the "add" command
     *
     * @param arg
     * @param user
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, User user) {
        try {
            if(!arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. see 'help' for reference.");
            if(!user.isVerified()) return new Feedbacker(false, ">You need to log in first.");
            if(user.getAccess().equals(Access.RESTRICTED_ACCESS)) return new Feedbacker(false, ">You don't have permission for this.");
//            Human h = Ask.askHuman(re.getCurrHumanData(),cm.getUnusedId(), userData);
            AbstractBanner banner = re.getBannerList().get(arg);
            if (user.getRollAmount()<warrantConst){
                Human loot = banner.roll();
                loot.setOwner(user.getName());
                cm.add(loot);
                return new Feedbacker(">Rolled successfully.");
            } else {
                Human loot = banner.rollWarrant();
                loot.setOwner(user.getName());
                cm.add(loot);
                return new Feedbacker(">Rolled successfully.");
            }
        } catch (NullPointerException e) {
            return new Feedbacker(false,">Error occurred:"+ e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
    }
}
