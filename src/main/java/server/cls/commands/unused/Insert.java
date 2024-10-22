//package server.cls.commands.unused;
//
//import common.*;
//import server.*;
//import server.cls.commands.RuntimeEnv;
//import server.managers.CollectionManager;
//
///**
// * Executes the "insert" command
// */
//public class Insert extends AbstractCommand {
//    private CommandLine cl;
//    private CollectionManager cm;
//    private RuntimeEnv re;
//    public Insert(CommandLine cl, CollectionManager cm, RuntimeEnv re) {
//        super("insert (positive_integer_value)", "Inserts a new element into entered position.",true);
//        this.cl = cl;
//        this.cm=cm;
//        this.re=re;
//    }
//    /**
//     * Executes the "insert" command
//     *
//     * @param arg
//     * @param user
//     * @return Feedbacker
//     */
//    @Override
//    public Feedbacker execute(String arg, User user) {
//        try {
//            if (arg.isEmpty()) return new Feedbacker(false, ">Wrong argument usage. See 'help' for reference.", user);
//            var val = Integer.parseInt(arg.trim());
//            if(val<=cm.getCollection().size() && !(val<0)){
//                cl.printLn(">Creating new Human for insertion:");
//                Human h = Ask.askHuman(re.getCurrHumanData(), cm.getUnusedId(), user);
//                if (h != null && h.validate()) {
//                    cm.insert(val,h);
//                    return new Feedbacker(">Inserted successfully.", user);
//                } else return new Feedbacker(false, ">Failed to insert. Invalid arguments.", user);
//            } else return new Feedbacker(false,">Failed. No such index", user);
//        } catch (NumberFormatException|Ask.AskBreaker e) {
//            return new Feedbacker(false, ">Failed.", user);
//        }
//    }
//}
