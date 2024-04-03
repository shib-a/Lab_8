package server;

import org.example.CommandLine;
import org.example.classes.CollectionManager;
import org.example.collection.CollectionLoaderSaver;
import org.example.commands.*;
import org.example.commands.redo.RedoFile;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(3829);
        Socket s = ss.accept();
        CommandLine cl = new CommandLine();
        var cls = new CollectionLoaderSaver("ans.txt",cl);
        var cm = new CollectionManager(cls);
        cm.initialaze();
        var com = new CommandManager();
        var re = new RuntimeEnv(cl,com,s);
        com.getCommandList().put("add", new Add(cl,cm));
        com.getCommandList().put("clear", new Clear(cl,cm));
        com.getCommandList().put("info", new Info(cl,cm));
        com.getCommandList().put("show", new Show(cl,cm));
        com.getCommandList().put("exit", new Exit(cl,cm));
        com.getCommandList().put("sort", new Sort(cl,cm));
        com.getCommandList().put("update", new Update(cl,cm));
        com.getCommandList().put("remove_by_id", new Remove(cl,cm));
        com.getCommandList().put("filter_by_is_alive", new FilterByIsAlive(cl,cm));
        com.getCommandList().put("save", new Save(cl,cm));
        com.getCommandList().put("filter_by_less_than_number_of_dug_counter", new FilterLessDC(cl,cm));
        com.getCommandList().put("count_by_researcher_type", new CountByResearcherType(cl,cm));
        com.getCommandList().put("remove_lower", new RemoveLower(cl,cm));
        com.getCommandList().put("insert", new Insert(cl,cm));
        com.getCommandList().put("execute_script", new ExecuteScript(cl,cm));
        com.getCommandList().put("get_history", new GetHistory(cl,com));
        com.getCommandList().put("help", new Help(com));
        com.getCommandList().put("redo",new Redo(cl,com,re));
        com.getCommandList().put("redo_f",new RedoFile(cl,com,re));
        while (true){
            try{
                CommandObject command;
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                command = (CommandObject) ois.readObject();
                System.out.println(command);
                var recCom = com.getCommandList().get(command.getInputCommand().getName());
                recCom.execute(command.getArgument());
                OutputStream os = s.getOutputStream();
            } catch (ClassNotFoundException  e){
                System.out.println("oops!");
            }
        }
    }
}
