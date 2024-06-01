package server;

import common.HumanData;
import common.ResearcherType;
import common.ToolKinds;
import common.UserData;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static server.cls.commands.RuntimeEnv.addToLog;
/**
 * This class Asks user/script for arguments to create a Human instance
 */
public class Ask {
    /**
     * This class stops the asking process
     */
    public static class AskBreaker extends Exception{}
    /**
     * This method creates a Human instance using a series of user/scripted inputs using a Human constructor
     * @return Human
     * @throws AskBreaker
     */
    public static Human askHuman(HumanData hd, int id, UserData userData) throws AskBreaker{
//        System.out.println("cock");
        try{
            String name = hd.getName();
//            System.out.println(name);
            ToolKinds ptt = hd.getPtt();
            ResearcherType rt = hd.getRt();
            Double[] stats = new Double[5];
            String[] st = hd.getStats().replace("[","").replace("]","").split(",");
            for (int i=0;i<stats.length;i++){stats[i]=Double.parseDouble(st[i]);}
            boolean ia = hd.getAl();
            int dc = Integer.parseInt(hd.getDc());
            String owner = userData.getName();
            DataConnector.addHumanInfo(id, name, ptt, rt,stats[0]+","+stats[1]+","+stats[2]+","+stats[3]+","+stats[4], ia ,dc, owner);
            return new Human(id,name, ptt, rt, ia,stats[0],stats[1],stats[2], stats[3], stats[4],dc, owner);
        } catch (NoSuchElementException   e){
            System.out.println(">Failed to read"+ e);
            return null;
        }
    }

    /**
     *Asks user/script for a preferredTool value
     * @return preferredTool
     * @throws AskBreaker
     */
    public static ToolKinds askPreferredTool(CommandLine cl) throws AskBreaker {
        try {
            ToolKinds ptt;
            while (true) {
                cl.print("Enter favourite tool: (" + Arrays.toString(ToolKinds.values()).replace("[","").replace("]","") + ")");
                String line = cl.readln().trim();
                if (line.equals("exit")) throw new AskBreaker();
                if (!line.isEmpty()) {
                    try {
                        ptt = ToolKinds.valueOf(line);
                        addToLog(line);
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println(">Wrong tool value. Try again or enter 'exit' to stop the process.");
                    }
                } else return null;
            }
            return ptt;
        } catch (NoSuchElementException e) {
            cl.printException(">Failed to read");
            return null;
        }
    }
    /**
     *Asks user/script for a researcherType value
     * @return researcherType
     * @throws AskBreaker
     */
//    public static ResearcherType askResType() throws AskBreaker {
//        try{
//            ResearcherType rt;
//            while(true){
////                System.out.println("Enter researcher type: (" + Arrays.toString(ResearcherType.values()).replace("[","").replace("]","") +")");
//                if (line.equals("exit")) throw new AskBreaker();
//                if (!line.isEmpty() && !line.isBlank()){
//                    try{
//                        rt = ResearcherType.valueOf(line);
//                        addToLog(line);
//                        break;
//                    } catch (IllegalArgumentException e){System.out.println(">Wrong researcher type value. Try again or enter 'exit' to stop the process.");}
//                } else return null;
//            }
//            return rt;
//        } catch (NoSuchElementException e){
//            cl.printException(">Failed to read");
//            return null;
//        }
//    }
    /**
     *Asks user/script for a boolean isAlive value
     * @return isAlive
     * @throws AskBreaker
     */
    public static Boolean askIsAlive(CommandLine cl) throws AskBreaker {
        while (true) {
            try {
                cl.print("Enter whether the object is alive: true for alive or false for not alive:");
                String line = cl.readln().trim();
                if (line.equals("exit")) throw new AskBreaker();
                if (!line.isEmpty() && !line.isBlank()) {
                    try {
                        if (line.trim().equals("true")) {addToLog(line);return true;} else if (line.trim().equals("false")){addToLog(line);return false;} else System.out.println(">Wrong value. Try again (enter true/false) or enter 'exit' to stop the process.");
                    } catch (IllegalArgumentException | NullPointerException e) {System.out.println(">Wrong value. Try again (enter true/false) or enter 'exit' to stop the process.");}
                }else System.out.println(">Wrong value. Try again (enter true/false) or enter 'exit' to stop the process.");
            }catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(">Failed to read. Set default value, false.");
        }
        }
    }
    /**
     *Asks user/script for stat values
     * @return stats[]
     * @throws AskBreaker
     */
    public static Double[] askStats(CommandLine cl) throws AskBreaker {
        while (true){
            Double[] mas = new Double[5];
            try{
                cl.print("Enter the five stats in following format: x.x,y.y,etc... ");
                String line = cl.readln().trim();
                if(line.equals("exit")) throw new AskBreaker();
                if(!line.isBlank() && !line.isEmpty()) {
                    int i = 0;
                    String[] ls = line.split(",");
                    if(ls.length!=0){
                    for (String entry : line.split(",")) {
                        if (!entry.isEmpty() && !entry.isBlank()){
                        try {
                            mas[i] = Double.parseDouble(entry);
                            i++;
                        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException | NullPointerException e) {
                            System.out.println(">Wrong value. Try again (enter e.g 50.0,30.0,10.0,15.0,60.0) or enter 'exit' to stop the process.");
                        }}else throw new NullPointerException();}
                    }else throw new NullPointerException();
                    addToLog(line);
                    return mas;
                } else throw new NullPointerException();
            }catch (IllegalArgumentException | ArrayIndexOutOfBoundsException | NullPointerException e){
                System.out.println(">Failed to read");
            }
//            System.out.println(">Wrong value. Try again (enter e.g 50.0,30.0,10.0,15.0,60.0) or enter 'exit' to stop the process.");
        }
    }
    /**
     *Asks user/script for a dugCounter value
     * @return dugCounter
     * @throws AskBreaker
     */
    public static Integer askDugCounter(CommandLine cl) throws AskBreaker {
        while (true) {
            cl.print("Enter the dug_counter value: ");
            String line = cl.readln().trim();
            if (line.equals("exit")) throw new AskBreaker();
            if (!line.isBlank() && !line.isEmpty()) {
                try {
                    var i = Integer.parseInt(line);
                    addToLog(line);
                    return i;
                } catch (NumberFormatException e) {
                    System.out.println(">Wrong argument");
                }
            } else return 0;
        }
    }
}