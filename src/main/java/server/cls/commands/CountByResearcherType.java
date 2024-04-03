package server.cls.commands;

import org.example.CommandLine;
import org.example.classes.CollectionManager;
import org.example.classes.Human;
import org.example.classes.ResearcherType;

public class CountByResearcherType extends AbstractCommand {
    private CommandLine cl;
    private CollectionManager cm;
    public CountByResearcherType(CommandLine cl, CollectionManager cm) {
        super("count_by_researcher_type (EXPEDITIONIST/FOLK_RESEARCHER)", "Shows amount of elements with entered RESEARCHER_TYPE value.");
        this.cl = cl;
        this.cm=cm;
    }

    @Override
    public Feedbacker execute(String[] arg) {
        if(arg[1].isEmpty()) return new Feedbacker(false,">Wrong argument usage. see 'help' for reference.");
        try{
            var val = ResearcherType.valueOf(arg[1].trim());
            if (cm.getCollection().isEmpty()){return new Feedbacker(">Empty collection.");} else{
                int count = 0;
                for(Human el: cm.getCollection()){
                    if (el.getType()==val) count++;
                }
                cl.printLn(count);
                return new Feedbacker(">Elements counted successfully.");}
        } catch(IllegalArgumentException e){ return new Feedbacker(false,">Wrong argument.");}
    }
}
