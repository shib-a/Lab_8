package org.example.commands;

import org.example.CommandLine;
import org.example.exceptions.RecursiveLimitExceededException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class RuntimeEnv {
    private CommandLine cl;
    private CommandManager cm;
    private ArrayList<String> scriptExecutionList = new ArrayList<>();
    private int recursiveDepth= -1;
    public RuntimeEnv(CommandLine cl, CommandManager cm){this.cl=cl;this.cm=cm;}
    public void mannedMode(){
        try{
            Feedbacker completionFeedback;
            String[] inputCommand = new String[]{"",""};
            while (true){
                cl.printLine();
                inputCommand = (cl.readln().trim()+" ").split(" ",2);
                if(inputCommand.length>2){cl.printException("Too many arguments! Check the amount of whitespaces or arguments.");} else{
                    cm.addToHistory(inputCommand[0]);
//                    if(inputCommand[0].equals("execute_script")){autoMode(inputCommand[1].trim());}
                    completionFeedback = executeCommand(inputCommand);
                    if(completionFeedback.getMessage().equals("exit")) break;
                    cl.printLn(completionFeedback.getMessage());
                }
            }
        } catch (NoSuchElementException | IllegalStateException e){cl.printException("Fatal error.");}
    }
    public Feedbacker autoMode(String path){
        String[] inputCommand = new String[]{"",""};
        String result = "";
        if (!new File(path.trim()).exists()) return new Feedbacker(false, "File does not exist");
        if (!Files.isReadable(Paths.get(path.trim()))) return new Feedbacker(false, "Not enough rights to read the file");
        scriptExecutionList.add(path);
        Feedbacker wtfIsGoingOn;
        System.out.println(scriptExecutionList.get(0));
        try(Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(path.trim())))) {
            do{
            if (!scanner.hasNext()) throw new NoSuchElementException();
            cl.selectFileScanner(scanner);

            inputCommand = (cl.readln().trim() + " ").split(" ", 2);
            while (cl.canReadln() && inputCommand.equals("")) {
                inputCommand = (cl.readln().trim() + " ").split(" ", 2);

            }
            result += inputCommand;
            boolean temp = true;
            if (inputCommand[0].equals("execute_script")) {
                temp = recursiveChecker(inputCommand[1], scanner);
            }
            if (temp) {
                wtfIsGoingOn = executeCommand(inputCommand);
            } else {
                wtfIsGoingOn = new Feedbacker("Max recursion depth exceeded");
            }
            if (inputCommand[0].equals("execute_script")) {
                cl.selectFileScanner(scanner);
                result += " " + wtfIsGoingOn.getMessage();
            }
        }while (wtfIsGoingOn.getIsSuccessful() && cl.canReadln() && !wtfIsGoingOn.getMessage().equals("exit"));
            cl.selectConsoleScanner();
            if(!(inputCommand[0].equals("execute_script")) && !wtfIsGoingOn.getIsSuccessful()){result+=" Something went wrong. Check script data.";}
            return new Feedbacker(wtfIsGoingOn.getIsSuccessful(), result.toString());
        } catch (IOException | NoSuchElementException | IllegalStateException e) {return new Feedbacker(false,"Error");}
        finally {
            scriptExecutionList.remove(scriptExecutionList.size()-1);
        }
    }
    private Feedbacker executeCommand(String[] inputCommand){
        if (inputCommand[0].equals("")) return new Feedbacker("");
        var command = cm.getCommandList().get(inputCommand[0]);
        if (command==null) return new Feedbacker(false,"Command "+inputCommand[0]+" not found. See 'help' for reference.");
        else if (inputCommand[0].equals("execute_script")){
            Feedbacker fp = cm.getCommandList().get("execute_script").execute(inputCommand);
            if(!fp.getIsSuccessful()) return fp;
            Feedbacker fp2 = autoMode(inputCommand[1].trim());
            return new Feedbacker(fp2.getIsSuccessful(),fp2.getMessage());
        } else return command.execute(inputCommand);
    }
    public boolean recursiveChecker(String path, Scanner scanner){
        int rd = recursiveDepth;
        int i = 0;
        for(String line:scriptExecutionList){
            i+=1;
            if(path.equals(line)){
                rd = i;
                cl.printLn("Script has recursive instructions. Do ypu wish to proceed? (yes/no)");
                cl.selectConsoleScanner();
                try{String l = cl.readln();
                if(!(l.equals("yes"))){throw new RecursiveLimitExceededException();}
                else{
                    cl.selectFileScanner(scanner);
                }
                }catch (RecursiveLimitExceededException e){}
            } if (i>10){return false;}
        }
        return true;
    }
}
