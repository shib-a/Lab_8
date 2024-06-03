package server.cls.commands;

import common.*;
import server.*;
import server.managers.CollectionManager;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Class for the "update" command
 */
public class Update extends AbstractCommand {
    private transient CommandLine cl;
    private transient CollectionManager cm;
    private RuntimeEnv re;
    public Update(CommandLine cl, CollectionManager cm, RuntimeEnv re) {
        super("update {id}", "Updates the element at entered id.",true);
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
        if(!user.isVerified()) return new Feedbacker(false, ">You need to log in first.", user);
        if(!user.getAccess().equals(Access.FULL_ACCESS)) return new Feedbacker(false, ">You don't have permission for this.", user);
        if(arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference", user);
        try {
            int id = Integer.parseInt(arg.trim());
//            Human h=Ask.askHuman(re.getCurrHumanData(),Integer.parseInt(arg.trim()), user);
            Set<Human> set = new HashSet<Human>();
//            for(AbstractBanner el: re.getBannerList().values()) {
            re.getBanner().getLootPool().stream().filter((a) -> a.getRarity().equals(cm.getById(id).getRarity())).forEach(set::add);
//            }
            Human[] ns = (Human[]) set.toArray();
            Random random = new Random();
            var newHuman = ns[random.nextInt(ns.length)];
            newHuman.setOwner(cm.getById(id).getOwner());
            cm.updateEl(id, newHuman);
            return new Feedbacker(">Updated successfully.", user);
//            else return new Feedbacker(false, ">Failed to update. Invalid arguments.");
        } catch (NumberFormatException | NullPointerException e){ return new Feedbacker(false,">Invalid number.", user);}
    }
}
