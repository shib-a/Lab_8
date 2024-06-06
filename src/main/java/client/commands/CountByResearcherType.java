package client.commands;

import common.AbstractCommand;

import common.Feedbacker;
import common.User;

public class CountByResearcherType extends AbstractCommand{
    public CountByResearcherType() {
        super("count_by_rarity {THREE_STAR/FOUR_STAR/FIVE_STAR}", "Shows amount of elements with entered RESEARCHER_TYPE value.");
    }

    @Override
    public Feedbacker execute(String arg, User user) {
//        if(arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. see 'help' for reference.");
//        try{
//            var val = ResearcherType.valueOf(arg.trim());
//            if (cm.getCollection().isEmpty()){return new Feedbacker(">Empty collection.");} else{
//                int count = 0;
//                for(Human el: cm.getCollection()){
//                    if (el.getType()==val) count++;
//                }
//                cl.printLn(count);
//                return new Feedbacker(">Elements counted successfully.");}
//        } catch(IllegalArgumentException e){ return new Feedbacker(false,">Wrong argument.");}
        return new Feedbacker("", user);
    }
}
