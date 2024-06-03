//package common;
//
//import java.io.Serializable;
//
//public class Tool extends Item implements Serializable {
//    public Status kind;
//    public StoneDurability canBreak;
//    public Tool(String name, Status kind){
//        super(name);
//        this.kind = kind;
//        switch (kind){
//            case RULER -> canBreak = StoneDurability.SOLID;
//            case JACKHAMMER -> canBreak = StoneDurability.TOUGH;
//            case FARMER -> canBreak = StoneDurability.HARD;
//        }
//    }
//
//    @Override
//    public String toString() {
//        return "Tool{" + itemName+ ","+
//                "kind=" + kind +
//                ", canBreak=" + canBreak +
//                '}';
//    }
//
//    /**
//     * Determines whether a Tool instance was correctly created
//     * @return
//     */
//    @Override
//    public boolean validate() {
//        if (this.kind==null) {return false;}
//        return super.validate();
//    }
//}
