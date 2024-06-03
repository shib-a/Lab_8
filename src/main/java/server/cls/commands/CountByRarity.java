package server.cls.commands;

import common.*;
import server.*;
import server.managers.CollectionManager;

public class CountByRarity extends AbstractCommand {
    private CommandLine cl;
    private CollectionManager cm;
    public CountByRarity(CommandLine cl, CollectionManager cm) {
        super("count_by_researcher_type {THREE_STAR/FOUR_STAR/FIVE_STAR}", "Shows amount of elements with entered RARITY value.");
        this.cl = cl;
        this.cm=cm;
    }

    @Override
    public Feedbacker execute(String arg, User user) {
        if(!user.isVerified()) return new Feedbacker(false,"Log in!", user);
        if(arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. see 'help' for reference.", user);
        try{
            var val = Rarity.valueOf(arg.trim());
            if (cm.getCollection().isEmpty()){return new Feedbacker(">Empty collection.", user);} else{
                return new Feedbacker(new StringBuilder().append(cm.getCollection().stream().filter(el -> el.getRarity().equals(val) && el.getOwner().equals(user.getName())).count()).append("\n").append(">Elements counted successfully.").toString(), user);}
        } catch(IllegalArgumentException e){ return new Feedbacker(false,">Wrong argument.", user);}
    }
}
