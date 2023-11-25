package org.example.classes;

import org.example.interfaces.Speculatable;

public class Elders extends Animal implements Speculatable {
    public Elders(){
        super("Старцы",false);
    }
    public void create(String what){
        System.out.println(howIsNamed + " породили " + what);
    }
    @Override
    public void speculate(String aboutWhat){
        System.out.println("Считается, что " + howIsNamed + " " + aboutWhat);
    }
}
