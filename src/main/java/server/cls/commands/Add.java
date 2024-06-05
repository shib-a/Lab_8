package server.cls.commands;

import common.*;
import server.CommandLine;
import server.managers.CollectionManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Logger;

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
    private static Logger logger = Logger.getLogger("ADd");
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
            if(!user.isVerified()) return new Feedbacker(false, ">You need to log in first." + user.getName() + user.isVerified(), user);
            if(user.getAccess().equals(Access.RESTRICTED_ACCESS)) return new Feedbacker(false, ">You don't have permission for this.", user);
            if(!arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. see 'help' for reference.", user);
//            if(!bl.containsKey(arg.trim())){return new Feedbacker(false, "no such banner", user);}
            AbstractBanner banner = re.getBanner();
            logger.info(banner.toString() + banner.getLootPool().size());
            if (user.getRollAmount()<warrantConst){
                Human loot = banner.roll();
                loot.setOwner(user.getName());
                loot.setRandomCords();
                cm.add(loot, user);
                return new Feedbacker(loot.toCsvStr(), user);
            } else {
                Human loot = banner.rollWarrant();
                loot.setOwner(user.getName());
                loot.setRandomCords();
                cm.add(loot, user);
                return new Feedbacker(loot.toCsvStr(), user);
            }
        } catch (NullPointerException e) {
            return new Feedbacker(false,">Error occurred:"+ e.getMessage() + Arrays.toString(e.getStackTrace()), user);
        }
    }
}
