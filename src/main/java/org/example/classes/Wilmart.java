package org.example.classes;

public class Wilmart extends Researcher{
    public Wilmart(){
        super("Уилмарт",ResearcherType.FOLK_RESEARCHER);
    }
    @Override
    public void write(String aboutWhat){
        System.out.println(name + " писал о " + aboutWhat);
    }
}
