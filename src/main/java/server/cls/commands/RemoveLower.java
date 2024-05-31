package server.cls.commands;

import common.AbstractCommand;
import common.Feedbacker;
import common.Stat;
import common.UserData;
import server.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Class for the "remove_lower" command
 */
public class RemoveLower extends AbstractCommand {
    private CommandLine cl;
    private CollectionManager cm;
    public RemoveLower(CommandLine cl, CollectionManager cm) {
        super("remove_lower (double_value)", "Removes elements with DAMAGE stat value lower than entered.");
        this.cl = cl;
        this.cm=cm;
    }
    /**
     * Executes the "remove_lower" command
     *
     * @param arg
     * @param userData
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, UserData userData) {
        if(arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.");
        try{
            var val = Double.parseDouble(arg.trim());
//            var idArr = new ArrayList<Integer>();
            if (cm.getCollection().isEmpty()){return new Feedbacker(">Empty collection.");} else{
//                for(Human el: cm.getCollection()){
//                    if (el.getStat(Stat.DAMAGE)<val) idArr.add(el.getId());
//                }
//                for (Integer el: idArr){cm.removeById(el);}
                cm.setCollection((ArrayList<Human>) cm.getCollection().stream().filter(el -> el.getStat(Stat.DAMAGE)>=val).collect(Collectors.toList()));
                return new Feedbacker(">Elements removed successfully.");}
        } catch(NumberFormatException e){ return new Feedbacker(false,">Wrong argument.");}
    }
}
