package server.cls.commands;

import client.classes.AskHumanData;
import common.CommandObject;
import common.Feedbacker;
import common.HumanData;
import server.*;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.NoSuchElementException;
import java.util.Scanner;
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
    private Socket ss;
    private SocketChannel sc;
    private HumanData currHumanData = null;
    public RuntimeEnv(CommandLine cl, CommandManager cm){this.cl=cl;this.cm=cm;try{bw = new BufferedWriter(new FileWriter("log.txt"));} catch (IOException e){bw = null;}}
    public RuntimeEnv(CommandLine cl, CommandManager cm, SocketChannel ssc){this.cl=cl;this.cm=cm;this.sc=ssc;try{bw = new BufferedWriter(new FileWriter("log.txt"));} catch (IOException e){bw = null;}}

    /**
     * Takes user inputs and executes entered commands
     */
    public void mannedMode() throws CustomException {
        try{
            askAuth(sc);
            Feedbacker completionFeedback = null;
//            Scanner scanner = new Scanner(System.in);
//            if(scanner.hasNext()){
//                String[] inputCommand = new String[]{"",""};
//                inputCommand = (cl.readln().trim()+" ").split(" ",2);
//                if(inputCommand.length>2){cl.printException(">Too many arguments! Check the amount of whitespaces or arguments.");} else{
////                        addToLog(inputCommand[0]+" "+inputCommand[1]);
//                    completionFeedback = executeCommand(inputCommand);
////                    cm.addToHistory(inputCommand[0]+" "+inputCommand[1]);
//                    if(completionFeedback.getMessage().equals("exit")) return;
//                    cl.printLn(completionFeedback.getMessage());
//                }}
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
//                Feedbacker completionFeedback = null;
//                while(true) {
//                    ServerConnector.getSelector().select();
//                    Set<SelectionKey> selectedKeys = ServerConnector.getSelector().selectedKeys();
//                    if (ServerConnector.getSelector().select()>0){
//                        for (SelectionKey sk : selectedKeys) {
//                            if (sk.isReadable() && sk.isValid()) {
////                                ByteBuffer buf = ByteBuffer.allocate(3048);
////                                ssc.read(buf);
////                                buf.flip();
////                                byte[] serializedCommand = new byte[buf.remaining()];
////                                logger.info(String.valueOf(buf.remaining()));
////                                buf.get(serializedCommand);
////                                ByteArrayInputStream bis = new ByteArrayInputStream(serializedCommand);
////                                ObjectInputStream ois = new ObjectInputStream(bis);
//////                ObjectInputStream ois = new ObjectInputStream(ss.getInputStream());
////                                logger.info("Receiving data from client... ");
////                                CommandObject co = (CommandObject) ois.readObject();
////                                logger.info("Data received");
////                                buf.clear();
////                                buf.flip();
////                                cl.printLine();
////                                addToLog(co.getCommand()+" "+co.getArgument());
////                                completionFeedback = executeCommand(co);
//                                if(sk.isValid() && sk.isWritable()){
//                                    try {
//                                        logger.info("Sending answer to client..." + completionFeedback.getMessage());
//                                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                                        ObjectOutputStream oos = new ObjectOutputStream(bos);
//                                        oos.writeObject(completionFeedback);
//                                        oos.flush();
//                                        byte[] answer = bos.toByteArray();
//                                        ByteBuffer outputBuf = ByteBuffer.wrap(answer);
////                                        outputBuf.flip();
//                                        logger.info("before output: " + outputBuf.remaining());
//                                        ssc.write(outputBuf);
////                                        outputBuf.compact();
//                                        outputBuf.flip();
//                                        logger.info("Answer sent successfully");
//                                    }catch (IOException e){}
//                                }
//                                break;
////                    if(completionFeedback.getMessage().equals("exit")) break;
//                            }
//                            System.out.println("cock");
//                        }
//                    }
//                }

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
            if (co.getCommand().getName().equals("")) return new Feedbacker("");
            if (co.getCommand().getName().equals("exit")) {
                return new Feedbacker("exit");}
            var command = cm.getCommandList().get(co.getCommand().getName());
            logger.info("Processing received command: "+command);
            if (command == null)
                return new Feedbacker(false, ">Command " + co.getCommand() + " not found. See 'help' for reference.");
            command = cm.getCommandList().get(co.getCommand().getName());
            Feedbacker fb = command.execute(co.getArgument());
            logger.info("Command processed, answer is ready");
        return fb;
    }

    public Feedbacker executeCommand(String[] inputCommand){
        if (inputCommand[0].equals("")) return new Feedbacker("");
        var command = cm.getCommandList().get(inputCommand[0]);
        if (command==null) return new Feedbacker(false,">Command "+inputCommand[0]+" not found. See 'help' for reference.");
        else if (inputCommand[0].equals("execute_script")){
            Feedbacker fp = cm.getCommandList().get("execute_script").execute(inputCommand[1]);
            if(!fp.getIsSuccessful()) return fp;
//            Feedbacker fp2 = autoMode(inputCommand[1].trim());
            Feedbacker fp2 = null;
            return new Feedbacker(fp2.getIsSuccessful(),fp2.getMessage());
        } else {
            HumanData hd = null;
            CommandObject co = new CommandObject(command,inputCommand[1],hd);
            if (co.getCommand().getIsNeedData()){
                try{
                    hd = server.AskHumanData.askHuman(cl);
                } catch (server.AskHumanData.AskBreaker a){}
            }
            try{
                if (hd!=null){
                    co.setHd(hd);}
                return executeCommand(co);
            } catch (NullPointerException  e){}
        }
        return null;
    }
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
        addToLog(co.getCommand()+" "+co.getArgument());
        return co;
    }
    public void setSc(SocketChannel sc){
        this.sc=sc;
    }
    public void askAuth(SocketChannel sc) {
        logger.info("Auth started");
        try {
            CommandObject co = recieve();
            String arg = co.getArgument();

            var cargs = arg.trim().split(" ",2);
//            String[] cargs = co.getArgument().split(" ");
            ArrayList<String> data = DataConnector.getUserInfo(Integer.parseInt(cargs[1]));
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(cargs[1].getBytes("UTF-8"));
            String encoded = Base64.getEncoder().encodeToString(hash);
            if(encoded.equals(data.get(1))){return;}
        } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException e){} catch (CustomException e){}
    }
}
