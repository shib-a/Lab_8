package org.example.classes;

import org.example.interfaces.*;

public class Creature extends Animal implements Resembleable, Speculatable {
    public Creature(String name){
        super(name,false);
    }
    @Override
    public void resemble(String what){
        System.out.println(howIsNamed + " напоминает " + what);
    }
    @Override
    public void speculate(String aboutWhat){
        System.out.println("Считается, что " + howIsNamed + " " + aboutWhat);
    }
}
