package org.example.commands;

public abstract class AbstractCommand implements Commandable {
    private boolean needsArg;
    private Object arg;
    @Override
    public abstract void execute();
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
    public AbstractCommand(boolean needsArg){this.needsArg=needsArg;}


}
