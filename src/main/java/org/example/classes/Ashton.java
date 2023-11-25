package org.example.classes;

public class Ashton extends Researcher {
    public Ashton(){
        super("Эштон",ResearcherType.FOLK_RESEARCHER);
    }
    public void draw(String what){
        System.out.println(name + " рисовал " + what);
    }
    public void draw(String what,String why){
        System.out.println(name + " рисовал " + what + " будучи вдохновленным "+ why);
    }
}
