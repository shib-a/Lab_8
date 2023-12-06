package org.example.classes;

public class LimeStone extends  GeologicalFormation{
    public LimeStone(String type,StoneDurability durability){
        super(type,durability);
    }
    public void keepIntact(String what){
        System.out.println(what+ " сохранилось благодаря близости " + type);
    }
}
