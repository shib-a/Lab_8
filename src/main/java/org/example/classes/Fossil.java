package org.example.classes;

public class Fossil extends Item{
    private FossilType type;
    private double rarity;
    public Fossil(String name, FossilType type){
        super(name);
        this.type = type;
        if(type == FossilType.INTACT_FOSSIL){
            this.rarity = 6;
        } else if (type == FossilType.HEAD){
            this.rarity = 5;
        } else if (type == FossilType.WING){
            this.rarity = 4;
        } else if (type == FossilType.TENTACLE | type == FossilType.PIPES){
            this.rarity = 3;
        } else if (type == FossilType.STONES) {
            this.rarity = 2;
        } else{
            this.rarity = 1;
        }
    }
    public double getRarity() {
        return rarity;
    }
    public FossilType getType(){
        return type;
    }
}
