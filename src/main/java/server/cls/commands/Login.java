package server.cls.commands;

import common.AbstractCommand;
import common.Feedbacker;
import common.User;
import server.CollectionManager;
import server.CommandLine;

public class Login extends AbstractCommand {
    private CommandLine cl;
    private CollectionManager cm;
    private RuntimeEnv re;
    public Login(CommandLine cl, CollectionManager cm, RuntimeEnv re) {
        super("login", "authorize");
        this.cl = cl;
        this.cm=cm;
        this.re=re;
    }

    /**
     * Executes the "add" command
     *
     * @param arg
     * @param user
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, User user) {
        if(arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. see 'help' for reference.");
        re.logger.info("check started");
        Feedbacker temp = null;
        int count = 0;
        while(temp==null){
            temp = re.askAuth(arg);
            re.logger.info("chekage happening");
            count+=1;
        }
        return temp;
    }
}
