package org.example.commands.redo;

import com.sun.jdi.Field;
import org.example.CommandLine;
import org.example.commands.AbstractCommand;
import org.example.commands.CommandManager;
import org.example.commands.Feedbacker;
import org.example.commands.RuntimeEnv;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class for the "redo_f" command
 */
public class RedoFile extends AbstractCommand {
    private CommandLine cl;
    private CommandManager com;
    private RuntimeEnv re;
    public RedoFile(CommandLine cl, CommandManager com, RuntimeEnv re) {
        super("redo_f (integer_value/empty_space)", "Redo an entered amount of previous commands or the whole log if the amount is not entered. (working bud shit code)");
        this.cl = cl;
        this.com=com;
        this.re=re;
    }
    /**
     * Executes the "redo" command
     * @param arg
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String[] arg) {
        if(arg[1].isEmpty()){
            re.executeCommand(new String[]{"execute_script", "log.txt"});
            return new Feedbacker(">Redone successfully.");
        }
        try{
            int ind = Integer.parseInt(arg[1].trim());
            if(ind<=0){return new Feedbacker(false,">Wrong argument usage. Value should be higher than 0.");}
            else if(ind>=com.getCommandHistory().size()){return new Feedbacker(false,">Wrong argument usage. Value should not exceed the history length.");}
            var lore = com.getCommandHistory();
            int i = lore.size()-ind;
            var log = new File("log.txt");
            File f =new File("temp.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            ArrayList<String> temp = new ArrayList<>();
            do{
                bw.write(lore.get(i)+"\n");
                i++;
            }while(i<lore.size());
            bw.flush();
            bw.close();
            re.executeCommand(new String[]{"execute_script","temp.txt"});
            f.delete();
            return new Feedbacker(">Redone Successfully.");
        } catch (NullPointerException | NumberFormatException | IOException e){return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.");}
    }
}
