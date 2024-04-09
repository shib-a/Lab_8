package server.cls.commands;

import common.CommandObject;
import common.Feedbacker;
import common.HumanData;
import server.CommandLine;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
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
    private Socket ss;
    private HumanData currHumanData = null;
    public RuntimeEnv(CommandLine cl, CommandManager cm, Socket ss){this.cl=cl;this.cm=cm;this.ss=ss;try{bw = new BufferedWriter(new FileWriter("log.txt"));} catch (IOException e){bw = null;}}

    /**
     * Takes user inputs and executes entered commands
     */
    public void mannedMode(){
        try{
            Feedbacker completionFeedback;
//            ObjectInputStream ois = new ObjectInputStream(ss.getInputStream());
//            CommandObject co = (CommandObject) ois.readObject();
            while (true){
                ObjectInputStream ois = new ObjectInputStream(ss.getInputStream());
                CommandObject co = (CommandObject) ois.readObject();
                cl.printLine();
                    addToLog(co.getCommand()+" "+co.getArgument());
                    completionFeedback = executeCommand(co);
                    if(completionFeedback.getMessage().equals("exit")) break;
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(ss.getOutputStream());
                        oos.writeObject(completionFeedback);
                    }catch (IOException e){}
                }
        } catch (NoSuchElementException | IOException | ClassNotFoundException | IllegalStateException e){
            System.err.println(e);
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
    public Feedbacker executeCommand(CommandObject co){
            currHumanData = co.getHd();
            if (co.getCommand().equals("")) return new Feedbacker("");
            var command = cm.getCommandList().get(co.getCommand().getName());
            System.out.println(command);
            if (command == null)
                return new Feedbacker(false, ">Command " + co.getCommand() + " not found. See 'help' for reference.");
//        else if (co.command.equals("execute_script")){
//            Feedbacker fp = cm.getCommandList().get("execute_script").execute(co);
//            if(!fp.getIsSuccessful()) return fp;
//            Feedbacker fp2 = autoMode(co.argument.trim());
//            return new Feedbacker(fp2.getIsSuccessful(),fp2.getMessage());
//        } else {
            command = cm.getCommandList().get(co.getCommand().getName());
            Feedbacker fb = command.execute(co.getArgument());
            return fb;
//        }

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
