package client.commands;

import common.AbstractCommand;
import common.Feedbacker;
import common.User;

public class Register extends AbstractCommand {
    public Register() {
        super("register {user} {password}", "authorize");
    }
    /**
     * Executes the "info" command
     *
     * @param arg
     * @param user
     * @return Feedbacker
     */
    @Override
    public common.Feedbacker execute(String arg, User user) {
        return new Feedbacker("", user);
    }
}
