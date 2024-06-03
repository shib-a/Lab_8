package server.multithread;

import common.*;
import server.CommandLine;
import server.managers.CommandManager;

import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Logger;

public class InteraciveServerConsole implements Runnable {
    CommandLine cl;
    CommandManager cm;
    Scanner scanner = new Scanner(System.in);
    HumanData currHumanData = null;
    User user = new User("admin", Access.FULL_ACCESS, true);
    Logger logger = Logger.getLogger("IntServCons");
    public InteraciveServerConsole(CommandLine cl, CommandManager cm){this.cl = cl; this.cm = cm;}
    @Override
    public void run() {
        while(true) {
            try {
                Feedbacker completionFeedback = null;
                if (scanner.hasNext()) {
                    String[] inputCommand = new String[]{"", ""};
                    inputCommand = (cl.readln().trim() + " ").split(" ", 2);
                    if (inputCommand.length > 2) {
                        cl.printException(">Too many arguments! Check the amount of whitespaces or arguments.");
                    } else {
//                        addToLog(inputCommand[0]+" "+inputCommand[1]);
                        completionFeedback = executeCommand(inputCommand, user);
//                    cm.addToHistory(inputCommand[0]+" "+inputCommand[1]);
                        if (completionFeedback.getMessage().equals("exit")) System.exit(0);
                        cl.printLn(completionFeedback.getMessage());
                    }
                }
            } catch (NullPointerException e) {
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
        }
    }


    public Feedbacker executeCommand (CommandObject co){
        currHumanData = co.getHd();
        if (co.getCommand().getName().equals("")) return new Feedbacker("", user);
        if (co.getCommand().getName().equals("exit")) {
            return new Feedbacker("exit", user);
        }
        var command = cm.getCommandList().get(co.getCommand().getName());
        logger.info("Processing received command: " + command);
        if (command == null)
            return new Feedbacker(false, ">Command " + co.getCommand() + " not found. See 'help' for reference.", user);
        command = cm.getCommandList().get(co.getCommand().getName());
        Feedbacker fb = command.execute(co.getArgument(), user);
        logger.info("Command processed, answer is ready");
        return fb;
    }

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
            public Feedbacker executeCommand (String[] inputCommand, User user){
                if (inputCommand[0].equals("")) return new Feedbacker("", user);
                var command = cm.getCommandList().get(inputCommand[0]);
                if (command == null)
                    return new Feedbacker(false, ">Command " + inputCommand[0] + " not found. See 'help' for reference.", user);
                else if (inputCommand[0].equals("execute_script")) {
                    Feedbacker fp = cm.getCommandList().get("execute_script").execute(inputCommand[1], user);
                    if (!fp.getIsSuccessful()) return fp;
//            Feedbacker fp2 = autoMode(inputCommand[1].trim());
                    Feedbacker fp2 = null;
                    return new Feedbacker(fp2.getIsSuccessful(), fp2.getMessage(), fp2.getUser());
                } else {
                    HumanData hd = null;
                    CommandObject co = new CommandObject(command, inputCommand[1], hd, user);
                    if (co.getCommand().getIsNeedData()) {
                        try {
                            hd = server.AskHumanData.askHuman(cl);
                        } catch (server.AskHumanData.AskBreaker a) {
                        }
                    }
                    try {
                        if (hd != null) {
                            co.setHd(hd);
                        }
                        return executeCommand(co);
                    } catch (NullPointerException e) {
                    }
                }
                return null;
            }
        }