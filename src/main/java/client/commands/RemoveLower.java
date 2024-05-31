package client.commands;
import common.AbstractCommand;

import common.Feedbacker;
import common.UserData;

/**
 * Class for the "remove_lower" command
 */
public class RemoveLower extends AbstractCommand{
    public RemoveLower() {
        super("remove_lower (double_value)", "Removes elements with DAMAGE stat value lower than entered.");
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
//        if(arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.");
//        try{
//            var val = Double.parseDouble(arg.trim());
//            var idArr = new ArrayList<Integer>();
//            if (cm.getCollection().isEmpty()){return new Feedbacker(">Empty collection.");} else{
//                for(Human el: cm.getCollection()){
//                    if (el.getStat(Stat.DAMAGE)<val) idArr.add(el.getId());
//                }
//                for (Integer el: idArr){cm.removeById(el);}
//                return new Feedbacker(">Elements removed successfully.");}
//        } catch(NumberFormatException e){ return new Feedbacker(false,">Wrong argument.");}
        return new Feedbacker("");
    }
}
