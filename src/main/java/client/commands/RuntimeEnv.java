package client.commands;

import client.CommandLine;
import client.classes.AskHumanData;
import common.CommandObject;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import common.Feedbacker;
import common.HumanData;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class acts as a runtime environment and handles user inputs as well as scripted inputs.
 */
public class RuntimeEnv {
    private CommandLine cl;
    private static CommandManager cm;
    private ArrayList<String> scriptExecutionList = new ArrayList<>();
    private static BufferedWriter bw;
    private SocketChannel ssc;
    private Socket ss;
    public RuntimeEnv(CommandLine cl, CommandManager cm, Socket ss){this.cl=cl;this.cm=cm;this.ss=ss;try{bw = new BufferedWriter(new FileWriter("log.txt"));} catch (IOException e){bw = null;}}
    public RuntimeEnv(CommandLine cl, CommandManager cm, SocketChannel ssc){this.cl=cl;this.cm=cm;this.ssc=ssc;try{bw = new BufferedWriter(new FileWriter("log.txt"));} catch (IOException e){bw = null;}}

    /**
     * Takes user inputs and executes entered commands
     */
    public void mannedMode(){
        try{
            Feedbacker completionFeedback;
            String[] inputCommand = new String[]{"",""};
            while (true){
                cl.printLine();
                inputCommand = (cl.readln().trim()+" ").split(" ",2);
                if(inputCommand.length>2){cl.printException(">Too many arguments! Check the amount of whitespaces or arguments.");} else{
                    addToLog(inputCommand[0]+" "+inputCommand[1]);
                    completionFeedback = executeCommand(inputCommand);
//                    cm.addToHistory(inputCommand[0]+" "+inputCommand[1]);
                    if(completionFeedback.getMessage().equals("exit")) break;
                    cl.printLn(completionFeedback.getMessage());

                }
            }
        } catch (NoSuchElementException | IllegalStateException e){cl.printException(">Fatal error.");}
    }

    /**
     *
     * @param path
     * @return Feedbacker
     */
    public Feedbacker autoMode(String path){
        String[] inputCommand = new String[]{"",""};
        if (!new File(path.trim()).exists()) return new Feedbacker(false, ">File does not exist.");
        if (!Files.isReadable(Paths.get(path.trim()))) return new Feedbacker(false, ">Not enough rights to read the file.");
        scriptExecutionList.add(path);
        Feedbacker wtfIsGoingOn;
        try(Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(path.trim())))) {
            do{
            if (!scanner.hasNext()) throw new NoSuchElementException();
            cl.selectFileScanner(scanner);
            inputCommand = (cl.readln().trim() + " ").split(" ", 2);
            while (cl.canReadln() && inputCommand.equals("")) {
                inputCommand = (cl.readln().trim() + " ").split(" ", 2);
            }
            boolean temp = true;
            if (inputCommand[0].trim().equals("execute_script")) {
                temp = recursiveChecker(inputCommand[1].trim(), scanner);
            }
            if (temp) {
                wtfIsGoingOn = executeCommand(inputCommand);
            } else {
                wtfIsGoingOn = new Feedbacker(">Recursion blocked for your own safety.");
            }
            if (inputCommand[0].equals("execute_script")) {
                cl.selectFileScanner(scanner);
            }
        }while (wtfIsGoingOn.getIsSuccessful() && cl.canReadln() && !wtfIsGoingOn.getMessage().equals("exit"));
            cl.selectConsoleScanner();
            if(!(inputCommand[0].equals("execute_script")) && !wtfIsGoingOn.getIsSuccessful()){System.out.println(">Something went wrong. Check script data.");}
            return new Feedbacker(wtfIsGoingOn.getIsSuccessful(),wtfIsGoingOn.getMessage()+"\n"+">Script completed.");
        } catch (IOException | NoSuchElementException | IllegalStateException e) {return new Feedbacker(false,">Error.");}
        finally {
            scriptExecutionList.remove(scriptExecutionList.size()-1);
        }
    }

    /**
     *
     * @param inputCommand
     * @return Feedbacker
     */
    public Feedbacker executeCommand(String[] inputCommand){
        if (inputCommand[0].equals("")) return new Feedbacker("");
        var command = cm.getCommandList().get(inputCommand[0]);
        if (command==null) return new Feedbacker(false,">Command "+inputCommand[0]+" not found. See 'help' for reference.");
        else if (inputCommand[0].equals("execute_script")){
            Feedbacker fp = cm.getCommandList().get("execute_script").execute(inputCommand[1]);
            if(!fp.getIsSuccessful()) return fp;
            Feedbacker fp2 = autoMode(inputCommand[1].trim());
            return new Feedbacker(fp2.getIsSuccessful(),fp2.getMessage());
        } else {
            HumanData hd = null;
            CommandObject co = new CommandObject(command,inputCommand[1],hd);
            if (co.getCommand().getIsNeedData()){
                try{
                    hd = AskHumanData.askHuman(cl);
                } catch (AskHumanData.AskBreaker a){}
            }
            try{
                if (hd!=null){
                co.setHd(hd);}
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(co);
                oos.flush();
                byte[] serializedObj = bos.toByteArray();
                ByteBuffer buf = ByteBuffer.wrap(serializedObj);
                ssc.write(buf);
//                    ByteBuffer inputBuf = ByteBuffer.allocate(1024);
                ByteBuffer inputBuf = ByteBuffer.allocate(1024);
                while (ssc.read(inputBuf)==0){
                    System.out.println("Cock");
                }
                ssc.read(inputBuf);
                inputBuf.flip();
                    byte[] receivedObj = new byte[inputBuf.remaining()];
                    inputBuf.get(receivedObj);
                    ByteArrayInputStream bis = new ByteArrayInputStream(receivedObj);
                    ObjectInputStream ois = new ObjectInputStream(bis);
                    Feedbacker fb = (Feedbacker) ois.readObject();
//                    System.out.println(fb);
                    inputBuf.clear();
                    inputBuf.flip();
                    return fb;

            }catch (IOException | ClassNotFoundException e){System.err.println(e);}
//            return command.execute(inputCommand);
            return null;
        }
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
}
