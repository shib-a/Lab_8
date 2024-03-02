package org.example.commands;

import org.example.CommandLine;
import org.example.classes.CollectionManager;
import org.example.classes.Human;

import java.io.FileNotFoundException;

public class Save extends AbstractCommand{
    private CommandLine cl;
    private CollectionManager cm;
    public Save(CommandLine cl, CollectionManager cm) {
        super("save", "Saves collection by writing it into a file ");
        this.cl = cl;
        this.cm=cm;
    }

    @Override
    public Feedbacker execute(String[] arg) {
        if (!arg[1].isEmpty()) return new Feedbacker(false, "Wrong argument usage. see 'help' for reference");
        cm.saveToFile();
        return new Feedbacker("Collection saved");
    }

}

