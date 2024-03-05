package org.example.commands;

import org.example.CommandLine;
import org.example.exceptions.RecursiveLimitExceededException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
    private int recursiveDepth= -1;
    public RuntimeEnv(CommandLine cl, CommandManager cm){this.cl=cl;this.cm=cm;try{bw = new BufferedWriter(new FileWriter("log.txt"));} catch (IOException e){bw = null;}}

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
            Feedbacker fp = cm.getCommandList().get("execute_script").execute(inputCommand);
            if(!fp.getIsSuccessful()) return fp;
            Feedbacker fp2 = autoMode(inputCommand[1].trim());
            return new Feedbacker(fp2.getIsSuccessful(),fp2.getMessage());
        } else return command.execute(inputCommand);
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
    public BufferedWriter getBw(){
        return bw;
    }
    public static void addToLog(String com){
        try {
            cm.addToHistory(com);
            bw.append(com+"\n");
            bw.flush();
        } catch (IOException e){}
    }
}
