package org.example.commands;
import org.example.classes.*;
import org.example.collection.CollectionLoaderSaver;
import org.example.collection.HumanCollection;
import org.example.exceptions.InvalidArgumentException;
import org.example.exceptions.UnknownCommandException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class RuntimeEnv {
    public void executeCommand(AbstractCommand c, CollectionLoaderSaver cls){
        Scanner sc = new Scanner(System.in);
        if (!c.isNeedsArg() && !c.isNeedsCol()) {
            c.execute();
        } else if (!c.isNeedsArg() && c.isNeedsCol()){
            c.execWithCol(cls.getHc());
        } else {
            sc.nextLine();
        }
    }
    public void startRuntime() throws InvalidArgumentException, IOException, IllegalAccessException {
        CollectionLoaderSaver cls = new CollectionLoaderSaver();
        cls.readFrom("E:\\IdeaProjects\\lab_3\\test.txt");
        HashMap<String, Object> commandMap = new HashMap<>();
        commandMap.put("help", new Help());
        commandMap.put("exit",new Exit());
        commandMap.put("clear",new Clear());
        commandMap.put("show", new Show());
        commandMap.put("sort", new Sort());

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Command Line Interface. Enter your command. If you don't know what to enter, try printing the 'help' command.");
        while(scanner.hasNext()){
            String input = scanner.nextLine();
            if(commandMap.containsKey(input)){
                if (input.equals("exit")){
                    break;
                }
                executeCommand((AbstractCommand) commandMap.get(input),cls);
            } else {
                System.out.println(new UnknownCommandException().getMessage());
            }
        }
        System.out.println("Exit complete. Have a nice cock!");
    }
}
