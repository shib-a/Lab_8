package org.example.classes;

public class Fossil extends Item{
    private FossilType type;
    private String rarity;
    public Fossil(String name, FossilType type){
        super(name);
        this.type = type;
        if(type == FossilType.INTACT_FOSSIL){
            this.rarity = "Legendary";
        } else if (type == FossilType.HEAD){
            this.rarity = "Mythic";
        } else if (type == FossilType.WING){
            this.rarity = "Epic";
        } else if (type == FossilType.TENTACLE | type == FossilType.PIPES){
            this.rarity = "Ultra Rare";
        } else if (type == FossilType.STONES) {
            this.rarity = "Rare";
        } else  {
            this.rarity = "Common";
        }
    }
    public String getRarity() {
        return rarity;
    }
    public FossilType getType(){
        return type;
    }
}
