package org.example.classes;

public class NecronExtract extends Book{
    double sanityEffect;
    public NecronExtract(String name, double intEff, double sanityEffect){
        super(name,intEff);
        this.sanityEffect = sanityEffect;
    }

    public double getSanityEffect() {
        return sanityEffect;
    }
}
