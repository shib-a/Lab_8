package org.example.classes;

abstract class Human{
    protected String name;
    public void read(String what){
    }
    public void talk(String aboutWhat){
    }
    public void write(String aboutWhat){
    }
    public String getName(){
        return name;
    }
    public Human(String name){
        this.name = name;
    }
}