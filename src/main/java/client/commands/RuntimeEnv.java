package client.commands;

import client.CommandLine;
import client.classes.AskHumanData;
import common.*;
import common.Feedbacker;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import java.util.logging.Logger;

import static java.lang.Thread.sleep;

/**
 * This class acts as a runtime environment and handles user inputs as well as scripted inputs.
 */
public class RuntimeEnv {
    private CommandLine cl;
    private static CommandManager cm;
    private ArrayList<String> scriptExecutionList = new ArrayList<>();
    private static BufferedWriter bw;
    private SocketChannel ssc;
    Logger logger = Logger.getLogger("RuntimeEnv");
    private Socket ss;
    public static Selector selector;
    public Feedbacker currentFeedbacker;
    private UserData userData;
    private static SelectionKey key;

    public RuntimeEnv(CommandLine cl, CommandManager cm, Socket ss){this.cl=cl;this.cm=cm;this.ss=ss;try{bw = new BufferedWriter(new FileWriter("log.txt"));} catch (IOException e){bw = null;}}
    public RuntimeEnv(CommandLine cl, CommandManager cm, SocketChannel ssc){this.cl=cl;this.cm=cm;this.ssc=ssc;try{bw = new BufferedWriter(new FileWriter("log.txt"));} catch (IOException e){bw = null;}}

    /**
     * Takes user inputs and executes entered commands
     */
    public void mannedMode(Selector selector){
        try{
//            selector.select();
            Feedbacker completionFeedback;
            String[] inputCommand = new String[]{"",""};
            RuntimeEnv.selector = selector;
            while (true){
                cl.printLine();
                inputCommand = (cl.readln().trim()+" ").split(" ",2);
                if(inputCommand.length>2){cl.printException(">Too many arguments! Check the amount of whitespaces or arguments.");} else{
                    addToLog(inputCommand[0]+" "+inputCommand[1]);
                    completionFeedback = executeCommand(inputCommand);
//                    cm.addToHistory(inputCommand[0]+" "+inputCommand[1]);
                    if(completionFeedback.getMessage().equals("exit")){System.exit(0);}
                    cl.printLn(completionFeedback.getMessage());

                }
            }
        } catch (NoSuchElementException | IllegalStateException e){cl.printException("Fatal error: " + e +  Arrays.toString(e.getStackTrace()));}
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
            Feedbacker fp = cm.getCommandList().get("execute_script").execute(inputCommand[1], getUserData());
            if(!fp.getIsSuccessful()) return fp;
            Feedbacker fp2 = autoMode(inputCommand[1].trim());
            return new Feedbacker(fp2.getIsSuccessful(),fp2.getMessage());
        } else {
            HumanData hd = null;
            CommandObject co = new CommandObject(command,inputCommand[1], hd, getUserData());
            if (co.getCommand().getIsNeedData()){
                try{
                    hd = AskHumanData.askHuman(cl);
                } catch (AskHumanData.AskBreaker a){}
            }
            try{
                if (hd!=null){
                co.setHd(hd);}
                Feedbacker temp = null;
                Selector tempSel = selector;
                while(true) {
                    selector.select();
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keys.iterator();
                    while(iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key==null){continue;}
                        if(key.isWritable()){
                            var sc = (SocketChannel) key.channel();
                            send(co, sc);
//                            sc.register(selector,SelectionKey.OP_READ);
                            key.interestOps(SelectionKey.OP_READ);
                        }
                        if (key.isReadable()){
                            var sc = (SocketChannel) key.channel();
                            var rec = recieve(selector, sc);
//                            sc.register(selector, SelectionKey.OP_WRITE);
                            key.interestOps(SelectionKey.OP_WRITE);
                            if(rec!=null){temp = rec;break;}
                        }

                    }
                    if(temp!=null){return temp;}
                }
            }catch (IOException  | ClassNotFoundException e){System.err.println(Arrays.toString(e.getStackTrace()));} catch (
                    NumberFormatException e) {
                throw new RuntimeException(e);
            }
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
    public void send(Object serializedObj) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(serializedObj);
        oos.flush();
        byte[] data = bos.toByteArray();
        ByteBuffer buf = ByteBuffer.wrap(data);
        ssc.write(buf);
        bos.close();
        oos.close();
//                buf.compact();
        logger.info("Answer sent");
    }
    public void send(Object serializedObj, SocketChannel sc) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(serializedObj);
        oos.flush();
        byte[] data = bos.toByteArray();
        ByteBuffer buf = ByteBuffer.wrap(data);
        sc.write(buf);
//        bos.close();
//        oos.close();
//        if(key!=null){key.interestOps(SelectionKey.OP_READ);}
//                buf.compact();
        logger.info("Answer sent");
    }
    public Feedbacker recieve() throws IOException, ClassNotFoundException {
        Feedbacker fb = null;
        ByteBuffer buffer = ByteBuffer.allocate(1024*1024);
        ssc.read(buffer);
//        recObj.flip();
//        byte[] receivedObj = new byte[recObj.remaining()];
//                            logger.info(String.valueOf(recObj.remaining()));
//        recObj.get(receivedObj);
        ByteArrayInputStream bis = new ByteArrayInputStream(buffer.array());
        ObjectInputStream ois = new ObjectInputStream(bis);
//                            logger.info(ois.readObject().toString());
        fb = (Feedbacker) ois.readObject();
        logger.info("Answer read" + fb.getMessage());
        return fb;
    }
    public Feedbacker recieve(Selector selector ,SocketChannel sc) throws IOException, ClassNotFoundException {
        Feedbacker fb = null;
        ByteBuffer buffer = ByteBuffer.allocate(1024*1024);
        sc.read(buffer);
        ByteArrayInputStream bis = new ByteArrayInputStream(buffer.array());
        ObjectInputStream ois = new ObjectInputStream(bis);
        fb = (Feedbacker) ois.readObject();
//        if(key!=null){key.attach(fb);}
//        if(key!=null){key.interestOps(SelectionKey.OP_WRITE);}
//        sc.register(selector, SelectionKey.OP_WRITE);
        logger.info("Answer read " + fb.getMessage());
        return fb;
    }
    public Feedbacker askAuthWrite(SelectionKey key, Selector selector){
        try {
            var sc = (SocketChannel) key.channel();
            System.out.println("Enter login and password: ");
            String input = cl.readln();
            if (input.isEmpty() || input.isBlank()) {
                return new Feedbacker(false, "Вы неправы.");
            }
            String[] inputArr = input.trim().split(" ");
            if (inputArr.length > 3 || inputArr.length == 1) {
                return new Feedbacker(false, "Вы неправы.");
            }
            setUserData(new UserData(inputArr[0], Permissinons.NORMAL_ACCESS));
            CommandObject co = new CommandObject(new Login(), input, null, getUserData());

            send(co, (SocketChannel) key.channel());

            sc.register(selector, SelectionKey.OP_READ);
            logger.info("sent");
        }catch (IOException | NumberFormatException e) {
            System.out.println(e.getCause().toString() + Arrays.toString(e.getStackTrace()));
        }
        return null;
    }
    public Feedbacker askAuthRead(SelectionKey key, Selector selector){
        try{
            var sc = (SocketChannel) key.channel();
            Feedbacker fb = recieve(selector, sc);
            currentFeedbacker = fb;
            System.out.println(fb.getMessage());
            sc.register(selector, SelectionKey.OP_WRITE);
            return fb;
        } catch (IOException | NumberFormatException | ClassNotFoundException e){
            System.out.println(e.getCause().toString() + Arrays.toString(e.getStackTrace()));
        }
        return null;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setSelector(Selector selector){this.selector = selector;}
}
