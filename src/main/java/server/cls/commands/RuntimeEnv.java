package server.cls.commands;

import common.CommandObject;
import common.Feedbacker;
import common.HumanData;
import server.CommandLine;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private SocketChannel ssc;
    private HumanData currHumanData = null;
    public RuntimeEnv(CommandLine cl, CommandManager cm, Socket ss){this.cl=cl;this.cm=cm;this.ss=ss;try{bw = new BufferedWriter(new FileWriter("log.txt"));} catch (IOException e){bw = null;}}
    public RuntimeEnv(CommandLine cl, CommandManager cm, SocketChannel ssc){this.cl=cl;this.cm=cm;this.ssc=ssc;try{bw = new BufferedWriter(new FileWriter("log.txt"));} catch (IOException e){bw = null;}}

    /**
     * Takes user inputs and executes entered commands
     */
    public void mannedMode() throws IOException {
        try{
            Feedbacker completionFeedback;
            while (true){
                ByteBuffer buf = ByteBuffer.allocate(1024);
                ssc.read(buf);
                buf.flip();
                byte[] serializedCommand = new byte[buf.remaining()];
                buf.get(serializedCommand);
                ByteArrayInputStream bis = new ByteArrayInputStream(serializedCommand);
                ObjectInputStream ois = new ObjectInputStream(bis);
//                ObjectInputStream ois = new ObjectInputStream(ss.getInputStream());
                logger.info("Receiving data from client... ");
                CommandObject co = (CommandObject) ois.readObject();
                logger.info("Data received");
                buf.clear();
                buf.flip();
                cl.printLine();
                    addToLog(co.getCommand()+" "+co.getArgument());
                    completionFeedback = executeCommand(co);
//                    if(completionFeedback.getMessage().equals("exit")) break;
                    try {
                        logger.info("Sending answer to client..." + completionFeedback.getMessage());
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        ObjectOutputStream oos = new ObjectOutputStream(bos);
                        oos.writeObject(completionFeedback);
                        oos.flush();
                        byte[] answer = bos.toByteArray();
                        ByteBuffer outputBuf = ByteBuffer.wrap(answer);
                        ssc.write(outputBuf);
//                        ObjectOutputStream oos = new ObjectOutputStream(ss.getOutputStream());
//                        oos.writeObject(completionFeedback);
                        logger.info("Answer sent successfully");
                    }catch (IOException e){}
                }
        } catch (NoSuchElementException | ClassNotFoundException | IllegalStateException e){
            logger.info(e.getMessage());
        } catch (IOException e){throw e;}
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
    public Feedbacker executeCommand(CommandObject co){
            currHumanData = co.getHd();
            if (co.getCommand().equals("")) return new Feedbacker("");
            var command = cm.getCommandList().get(co.getCommand().getName());
            logger.info("Processing received command: "+command);
            if (command == null)
                return new Feedbacker(false, ">Command " + co.getCommand() + " not found. See 'help' for reference.");
            command = cm.getCommandList().get(co.getCommand().getName());
            Feedbacker fb = command.execute(co.getArgument());
            logger.info("Command processed, answer is ready");
            return fb;
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
}
