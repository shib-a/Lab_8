package org.example.commands;

import org.example.collection.HumanCollection;

public abstract class AbstractCommand implements Commandable {
    private boolean needsArg;
    private boolean needsCol;
    private Object arg;
    @Override
    public abstract void execute();
    public abstract void execWithCol(HumanCollection obj);
    @Override
    public abstract String getName();
    @Override
    public abstract String getDescription();
    @Override
    public abstract boolean checkIsValidArg();
    public boolean isNeedsArg(){
        return needsArg;
    }
    public Object getArg(){return arg;}
    public void setArg(Object arg){this.arg=arg;}
//    public AbstractCommand(boolean needsArg){this.needsArg=needsArg;}
    public AbstractCommand(boolean needsArg, boolean needsCol){this.needsArg=needsArg; this.needsCol=needsCol;}
    public boolean isNeedsCol() {
        return needsCol;
    }
}
