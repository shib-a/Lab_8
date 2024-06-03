package server.cls.commands;

import common.AbstractCommand;
import common.Access;
import common.Feedbacker;
import common.User;
import server.CommandLine;
import server.CustomException;
import server.managers.CollectionManager;
import server.managers.DataConnector;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Logger;

public class Register extends AbstractCommand {
    private Logger logger = Logger.getLogger("reg");

    public Register() {
        super("register {user} {password}", "authorize");
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
        if(arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. see 'help' for reference.", user);
        Feedbacker temp = null;
        try {
            var cargs = arg.trim().split(" ");
            logger.info(String.valueOf(cargs.length));
            if (cargs.length != 2){return new Feedbacker(false,"Wrong argument usage", user);}
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(cargs[1].getBytes(StandardCharsets.UTF_8));
            String encoded = Base64.getEncoder().encodeToString(hash);
            logger.info("branching");

            try {
                ArrayList<String> data_arr = DataConnector.getUserInfo(cargs[0]);
                logger.info("user info got");
                if (!data_arr.isEmpty()) {
                    return new Feedbacker(false, "Account with this username already exists.", user);
                } else {
                    logger.info("adding a user");
                    DataConnector.addUserInfo(cargs[0], encoded, Access.NORMAL_ACCESS);
                    user.setVerified(true);user.setName(cargs[0]);
                    return new Feedbacker("Registered successfully", user);
                }
            } catch (CustomException | RuntimeException e){
                logger.info(e.getMessage() + Arrays.toString(e.getStackTrace()));
            }
        } catch (NoSuchAlgorithmException e){
            System.out.println(Arrays.toString(e.getStackTrace()) + e.getMessage() + e.getCause());
        }
        return new Feedbacker(false, "An error has occurred", user);
    }
}
