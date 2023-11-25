package org.example.classes;

import org.example.interfaces.*;

public class GeologicalLayer extends GeologicalFormation implements Excavatable, Speculatable {
    String agePeriod;
    public GeologicalLayer(String name, String durability, String period){
        super(name,durability);
        this.agePeriod = period;
    }
    public String getAgePeriod(){
        return agePeriod;
    }
    @Override
    public void getExcavated(){
        System.out.println(type + " частично выкопали");
    }
    @Override
    public void speculate(String aboutWhat){
        System.out.println("Считается, что " + type + " " + aboutWhat);
    }
}
