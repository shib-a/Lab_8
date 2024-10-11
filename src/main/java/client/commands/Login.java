package client.commands;
import common.AbstractCommand;
import common.Feedbacker;
import common.User;

/**
 * Class for the "info" command
 */
public class Login extends AbstractCommand {
    public Login() {
        super("login {user} {password}", "authorize");
    }
    /**
     * Executes the "info" command
     *
     * @param arg
     * @param user
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, User user) {
        return new Feedbacker("", user);
    }
}
