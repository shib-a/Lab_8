package org.example.classes;

import org.example.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Ask{
    public static class AskBreaker extends Exception{}
    public static Human askHuman(CommandLine cl) throws AskBreaker{
        try{
            String name;
            while (true){
                cl.print("enter name: ");
                name = cl.readln().trim();
                if(name.equals("exit")) throw new AskBreaker();
                if(!name.isEmpty()) break;
            }
            ToolKinds ptt = askPreferredTool(cl);
            ResearcherType rt = askResType(cl);
            boolean ia = askIsAlive(cl);
            return new Human(name, ptt, rt, ia);
        } catch (NoSuchElementException e){
            System.out.println("Failed to read");
            return null;
        }
    }
    public static Human askHuman(CommandLine cl, int id) throws AskBreaker{
        try{
            String name;
            while (true){
                cl.print("enter name: ");
                name = cl.readln().trim();
                if(name.equals("exit")) throw new AskBreaker();
                if(!name.isEmpty()) break;
            }
            ToolKinds ptt = askPreferredTool(cl);
            ResearcherType rt = askResType(cl);
            Double[] stats = askStats(cl);
            boolean ia = askIsAlive(cl);
            int dc = askDugCounter(cl);
            return new Human(id,name, ptt, rt, ia,stats[0],stats[1],stats[2], stats[3], stats[4],dc);
        } catch (NoSuchElementException e){
            System.out.println("Failed to read");
            return null;
        }
    }

    public static ToolKinds askPreferredTool(CommandLine cl) throws AskBreaker {
        try {
            ToolKinds ptt;
            while (true) {
                cl.print("enter favourite tool (" + Arrays.toString(ToolKinds.values()) + ")");
                String line = cl.readln().trim();
                if (line.equals("exit")) throw new AskBreaker();
                if (!line.isEmpty()) {
                    try {
                        ptt = ToolKinds.valueOf(line);
                        break;
                    } catch (IllegalArgumentException e) {
                    }
                } else return null;
            }
            return ptt;
        } catch (NoSuchElementException e) {
            cl.printException("Failed to read");
            return null;
        }
    }
    public static ResearcherType askResType(CommandLine cl) throws AskBreaker {
        try{
            ResearcherType rt;
            while(true){
                cl.print("enter researcher type (" + Arrays.toString(ResearcherType.values()) +")");
                String line = cl.readln().trim();
                if (line.equals("exit")) throw new AskBreaker();
                if (!line.isEmpty() && !line.isBlank()){
                    try{
                        rt = ResearcherType.valueOf(line);
                        break;
                    } catch (IllegalArgumentException e){}
                } else return null;
            }
            return rt;
        } catch (NoSuchElementException e){
            cl.printException("Failed to read");
            return null;
        }
    }
    public static boolean askIsAlive(CommandLine cl) throws AskBreaker {
        try {
            boolean isAl;
            while (true) {
                cl.print("enter whether the object is alive: true for alive or false for not alive:");
                String line = cl.readln().trim();
                if (line.equals("exit")) throw new AskBreaker();
                if (!line.isEmpty() && !line.isBlank()) {
                    try {
                        isAl = Boolean.valueOf(line);
                        break;
                    } catch (IllegalArgumentException e) {}

                } else return false;
            }
            return isAl;
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to read. Set default value, false.");
            return false;
        }
    }
    public static Double[] askStats(CommandLine cl) throws AskBreaker {
        try{
            Double[] mas = new Double[5];
            while (true){
                cl.print("enter the five stats in following format: x.x,y.y,etc... ");
                String line = cl.readln().trim();
                if(line.equals("exit")) throw new AskBreaker();
                if(!line.isBlank() && !line.isEmpty()){
                    int i = 0;
                    for(String entry:line.split(",")){
                        try{
                            mas[i] = Double.parseDouble(entry);
                            i++;
                        } catch(IllegalArgumentException e){}
                    }
                    return mas;
                } else return new Double[]{0.0, 0.0, 0.0, 0.0, 0.0};
            }
        } catch (IllegalArgumentException e){
            System.out.println("Failed to read");
            return new Double[]{0.0, 0.0, 0.0, 0.0, 0.0};
        }
    }
    public static Integer askDugCounter(CommandLine cl) throws AskBreaker {
        while (true) {
            cl.print("enter the dug_counter value: ");
            String line = cl.readln().trim();
            if (line.equals("exit")) throw new AskBreaker();
            if (!line.isBlank() && !line.isEmpty()) {
                try {
                    var i = Integer.parseInt(line);
                    return i;
                } catch (NumberFormatException e) {
                    System.out.println("Wrong argument");
                }
            } else return 0;
        }
    }
}