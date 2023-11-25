package org.example.classes;

public class Author extends Researcher{
    public Author(String name){
        super(name, ResearcherType.EXPEDITIONIST);
    }
    @Override
    public void read(String what){
        System.out.println("Я читал " + what);
    }
    @Override
    public void write(String what){
        System.out.println("Я писал " + what);
    }
    @Override
    public void talk(String what){
        System.out.println("Я говорю о " + what);
    }
}
