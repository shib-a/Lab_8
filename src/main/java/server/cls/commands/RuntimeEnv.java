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
                        sleep(1000);
                        send(fb);
                        if (fb.getMessage().equals("exit")){throw new CustomException();}
                    } catch (IOException e){}
                } else break;
            }
        } catch (NoSuchElementException | ClassNotFoundException | IllegalStateException | InterruptedException e){
            logger.info(e.getMessage());
        }
    }

    /**
     *
     * @param path
     * @return Feedbacker
     */
//    public Feedbacker autoMode(String path){
//        CommandObject co;
//        if (!new File(path.trim()).exists()) return new Feedbacker(false, ">File does not exist.");
//        if (!Files.isReadable(Paths.get(path.trim()))) return new Feedbacker(false, ">Not enough rights to read the file.");
//        scriptExecutionList.add(path);
//        Feedbacker wtfIsGoingOn;
//        try(Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(path.trim())))) {
//            do{
//            if (!scanner.hasNext()) throw new NoSuchElementException();
//            cl.selectFileScanner(scanner);
//            inputCommand = (cl.readln().trim() + " ").split(" ", 2);
//            while (cl.canReadln() && inputCommand.equals("")) {
//                inputCommand = (cl.readln().trim() + " ").split(" ", 2);
//            }
//            boolean temp = true;
//            if (inputCommand[0].trim().equals("execute_script")) {
//                temp = recursiveChecker(inputCommand[1].trim(), scanner);
//            }
//            if (temp) {
//                wtfIsGoingOn = executeCommand(co);
//            } else {
//                wtfIsGoingOn = new Feedbacker(">Recursion blocked for your own safety.");
//            }
//            if (inputCommand[0].equals("execute_script")) {
//                cl.selectFileScanner(scanner);
//            }
//        }while (wtfIsGoingOn.getIsSuccessful() && cl.canReadln() && !wtfIsGoingOn.getMessage().equals("exit"));
//            cl.selectConsoleScanner();
//            if(!(inputCommand[0].equals("execute_script")) && !wtfIsGoingOn.getIsSuccessful()){System.out.println(">Something went wrong. Check script data.");}
//            return new Feedbacker(wtfIsGoingOn.getIsSuccessful(),wtfIsGoingOn.getMessage()+"\n"+">Script completed.");
//        } catch (IOException | NoSuchElementException | IllegalStateException e) {return new Feedbacker(false,">Error.");}
//        finally {
//            scriptExecutionList.remove(scriptExecutionList.size()-1);
//        }
//    }

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
            logger.info(co.getArgument());
            Feedbacker fb = command.execute(arg,co.getUser());
            logger.info("Command processed, answer is ready");
        return fb;
    }

//    public Feedbacker executeCommand(String[] inputCommand){
//        if (inputCommand[0].equals("")) return new Feedbacker("");
//        var command = cm.getCommandList().get(inputCommand[0]);
//        if (command==null) return new Feedbacker(false,">Command "+inputCommand[0]+" not found. See 'help' for reference.");
//        else if (inputCommand[0].equals("execute_script")){
//            Feedbacker fp = cm.getCommandList().get("execute_script").execute(inputCommand[1]);
//            if(!fp.getIsSuccessful()) return fp;
////            Feedbacker fp2 = autoMode(inputCommand[1].trim());
//            Feedbacker fp2 = null;
//            return new Feedbacker(fp2.getIsSuccessful(),fp2.getMessage());
//        } else {
//            HumanData hd = null;
//            CommandObject co = new CommandObject(command,inputCommand[1],hd);
//            if (co.getCommand().getIsNeedData()){
//                try{
//                    hd = server.AskHumanData.askHuman(cl);
//                } catch (server.AskHumanData.AskBreaker a){}
//            }
//            try{
//                if (hd!=null){
//                    co.setHd(hd);}
//                return executeCommand(co);
//            } catch (NullPointerException  e){}
//        }
//        return null;
//    }
    /**
     * Checks whether a referenced file has already been executed
     * @param path
     * @param scanner
     * @return boolean
     */
    public boolean recursiveChecker(String path, Scanner scanner){
        if (scriptExecutionList.contains(path.trim()))return false; else return true;
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
