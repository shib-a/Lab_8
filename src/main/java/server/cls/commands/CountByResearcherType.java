package server.cls.commands;

import common.AbstractCommand;
import common.Feedbacker;
import common.ResearcherType;
import server.*;

public class CountByResearcherType extends AbstractCommand {
    private CommandLine cl;
    private CollectionManager cm;
    public CountByResearcherType(CommandLine cl, CollectionManager cm) {
        super("count_by_researcher_type (EXPEDITIONIST/FOLK_RESEARCHER)", "Shows amount of elements with entered RESEARCHER_TYPE value.");
        this.cl = cl;
        this.cm=cm;
    }

    @Override
    public Feedbacker execute(String arg) {
        if(arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. see 'help' for reference.");
        try{
            var val = ResearcherType.valueOf(arg.trim());
            if (cm.getCollection().isEmpty()){return new Feedbacker(">Empty collection.");} else{
                int count = 0;
                for(Human el: cm.getCollection()){
                    if (el.getType()==val) count++;
                }
                return new Feedbacker(new StringBuilder().append(count).append("\n").append(">Elements counted successfully.").toString());}
        } catch(IllegalArgumentException e){ return new Feedbacker(false,">Wrong argument.");}
    }
}
