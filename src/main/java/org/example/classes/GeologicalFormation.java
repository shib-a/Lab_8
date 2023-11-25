package org.example.classes;
abstract class GeologicalFormation{
    String type;
    String durability;
    public GeologicalFormation(String type, String durability){
        this.durability = durability;
        this.type = type;
    }
    public String getGeoType(){
        return type;
    }
    public String getDurability(){
        return durability;
    }
}
