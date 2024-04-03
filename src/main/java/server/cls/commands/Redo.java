package server.cls.commands;

import org.example.CommandLine;

/**
 * Class for the "redo" command
 */
public class Redo extends AbstractCommand {
    private CommandLine cl;
    private CommandManager com;
    private RuntimeEnv re;
    public Redo(CommandLine cl, CommandManager com, RuntimeEnv re) {
        super("redo (integer_value/empty_space)", "Redo an entered amount of previous commands or the whole log if the amount is not entered.");
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
            System.out.println(i);
            do{
                if (!lore.get(lore.size()-i).split(" ",2)[0].equals("redo")){re.executeCommand(lore.get(lore.size()-i).split(" ",2));
                    i++;}  // надо сделать какое то взаимодействие с экз скрипт иначе add и прочее не работает
            }while(i<lore.size());
            return new Feedbacker(">Redone Successfully.");
        } catch (NullPointerException | NumberFormatException e){return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.");}
    }
}
