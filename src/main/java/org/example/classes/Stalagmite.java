package org.example.classes;

import org.example.interfaces.*;

public class Stalagmite extends  GeologicalFormation implements Breakable {
    public Stalagmite(String type, String durability){
        super(type,durability);
    }
    @Override
    public void breakSelf(){
        System.out.println(type + " Откололись ");
    }
    public void hang(String where){
        System.out.println(type + " нависают "+ where );
    }
}
