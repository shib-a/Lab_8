package server.cls.commands;

import common.*;
import server.*;
import server.managers.CommandManager;
import server.managers.DataConnector;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

/**
 * This class acts as a runtime environment and handles user inputs as well as scripted inputs.
 */
public class RuntimeEnv {
    Logger logger = Logger.getLogger(RuntimeEnv.class.getName());
    private CommandLine cl;
    private static CommandManager cm;
    private ArrayList<String> scriptExecutionList = new ArrayList<>();
    private static BufferedWriter bw;
//    private HashMap<String, AbstractBanner> bannerList = new HashMap<>();
    private StandardBanner banner;
    private SocketChannel sc;
    User currUser;
    private HumanData currHumanData = null;
    public RuntimeEnv(CommandLine cl, CommandManager cm){this.cl=cl;this.cm=cm;try{bw = new BufferedWriter(new FileWriter("log.txt"));} catch (IOException e){bw = null;}}
    public RuntimeEnv(CommandLine cl, CommandManager cm, SocketChannel ssc){this.cl=cl;this.cm=cm;this.sc=ssc;try{bw = new BufferedWriter(new FileWriter("log.txt"));} catch (IOException e){bw = null;}}

    /**
     * Takes user inputs and executes entered commands
     */
    public void mannedMode() throws CustomException {
        try{
            Feedbacker completionFeedback = null;
            while (true){
                if(sc!=null) {
                    try{
                        CommandObject co = recieve();
                        Feedbacker fb = executeCommand(co);
//                        sleep(1000);
                        send(fb);
                        if (fb.getMessage().equals("exit")){throw new CustomException();}
                    } catch (IOException e){}
                } else break;
            }
        } catch (NoSuchElementException | ClassNotFoundException | IllegalStateException e){
            logger.info(e.getMessage());
        }
    }

    /**
     *
     * @param co
     * @return Feedbacker
     */
    public Feedbacker executeCommand(CommandObject co) {
            currHumanData = co.getHd();
            currUser = co.getUser();
            if (co.getCommand().getName().equals("")) return new Feedbacker("", co.getUser());
            if (co.getCommand().getName().equals("exit")) {
                return new Feedbacker("exit", co.getUser());}
            var command = cm.getCommandList().get(co.getCommand().getName());
            logger.info("Processing received command: "+command);
            if (command == null)
                return new Feedbacker(false, ">Command " + co.getCommand() + " not found. See 'help' for reference.", co.getUser());
            command = cm.getCommandList().get(co.getCommand().getName());
            String arg = co.getArgument();
            logger.info(command.getName());
            Feedbacker fb = command.execute(arg,co.getUser());
            logger.info("Command processed, answer is ready");
        return fb;
    }

    public static void addToLog(String com){
        try {
            cm.addToHistory(com);
            bw.append(com+"\n");
            bw.flush();
        } catch (IOException e){}
    }

    public HumanData getCurrHumanData() {
        return currHumanData;
    }
    public void send(Feedbacker completionFeedback){
        try {
            logger.info("Sending answer to client..." + completionFeedback.getMessage());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(completionFeedback);
            oos.flush();
            byte[] answer = bos.toByteArray();
            ByteBuffer outputBuf = ByteBuffer.wrap(answer);
            sc.write(outputBuf);
            logger.info("Answer sent successfully");
        }catch (IOException e){}
    }
    public CommandObject recieve() throws IOException, ClassNotFoundException {
        ByteBuffer buf = ByteBuffer.allocate(1024*1024);
        sc.read(buf);
        ByteArrayInputStream bis = new ByteArrayInputStream(buf.array());
        ObjectInputStream ois = new ObjectInputStream(bis);
        logger.info("Receiving data from client... ");
        CommandObject co = (CommandObject) ois.readObject();
        logger.info("Data received");
        System.out.println(co.getArgument());
        addToLog(co.getCommand()+" "+co.getArgument());
        return co;
    }
    public void setSc(SocketChannel sc){
        this.sc=sc;
    }
    public SocketChannel getSc() {
        return sc;
    }

    public User getUser() {
        return currUser;
    }

    public void setUserData(User ud) {
        this.currUser = ud;
    }

    public StandardBanner getBanner() {
        return banner;
    }

    public void setBanner(StandardBanner banner) {
        this.banner = banner;
    }
}
