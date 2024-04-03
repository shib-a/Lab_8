package org.example.classes.unused;
import org.example.classes.StoneDurability;
import org.example.interfaces.ReadMarkedField;
public abstract class GeologicalFormation{
    @ReadMarkedField
    String type;
    @ReadMarkedField
    StoneDurability durability;
    public GeologicalFormation(String type, StoneDurability durability){
        this.durability = durability;
        this.type = type;
    }
    public String getGeoType(){
        return type;
    }
    public StoneDurability getDurability(){
        return durability;
    }
}
