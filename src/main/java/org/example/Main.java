package org.example;
import org.example.classes.*;
import org.example.interfaces.*;

public class Main {
    public static void main(String[] args){
        Human h1 = new Human("Pebody",ResearcherType.EXPEDITIONIST);
        Human h2 = new Human("Wilmart",ResearcherType.FOLK_RESEARCHER);
        h1.setStat(100,80,50,15,90);
        h2.setStat(100,80,50,15,90);
        h1.getStat(Stat.HP);
        h1.attack(h2);
        System.out.println("\tfinished");
    }
}