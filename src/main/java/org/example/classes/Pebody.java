package org.example.classes;

import org.example.interfaces.*;

public class Pebody extends Researcher implements Understandable {
    public Pebody() {
        super("Пэбоди", ResearcherType.EXPEDITIONIST);
    }
    @Override
    public void understand(String who) {
        System.out.println(name + " понимает " + who);
    }
    public void watch(String what){
        System.out.println(name + " видел " + what);
    }
}
