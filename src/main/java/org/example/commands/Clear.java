package org.example.commands;

import org.example.collection.CollectionLoaderSaver;
import org.example.collection.HumanCollection;

public class Clear extends AbstractCommand {
    public Clear() {
        super(false,true);
    }

    @Override
    public void execute() {}
    @Override
    public void execWithCol(HumanCollection obj) {
        obj.getHumanArrayList().clear();
        System.out.println("Collection cleared.");
    }



    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean checkIsValidArg() {
        return false;
    }
}
