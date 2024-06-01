package client.classes.unused;

import common.Item;

public class Book extends Item {
    private double intelligenceEffect;
    public Book(String name, double intelligenceEffect){
        super(name);
        this.intelligenceEffect = intelligenceEffect;
    }
    public double getIntelligenceEffect() {
        return intelligenceEffect;
    }
}
