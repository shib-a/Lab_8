package org.example.classes;

import org.example.interfaces.*;

public class GeologicalLayer extends GeologicalFormation implements Excavatable{
    private int digsLeft;
    private boolean isDugOut = false;
    private PeriodAge age;
    private Fossil[] lootPool;
    public GeologicalLayer(String name, String durability, PeriodAge age){
        super(name,durability);
        this.age = age;
        Fossil intFos = new Fossil("intact fossil",FossilType.INTACT_FOSSIL);
        Fossil head = new Fossil("head",FossilType.HEAD);
        Fossil wing = new Fossil("wing",FossilType.WING);
        Fossil tentacle = new Fossil("tentacle",FossilType.TENTACLE);
        Fossil pipes = new Fossil("pipes",FossilType.PIPES);
        Fossil stones = new Fossil("rock",FossilType.STONES);
        Fossil trash = new Fossil("trash",FossilType.TRASH);
        if (age == PeriodAge.EOCENOS){
            this.lootPool = new Fossil[]{intFos, head, wing,stones};
            setDigsLeft(3);
        } else if (age == PeriodAge.CRETATIOUS){
            this.lootPool = new Fossil[]{intFos, head, wing, tentacle, stones};
            setDigsLeft(4);
        } else if (age == PeriodAge.MIOCENOS){
            this.lootPool = new Fossil[]{tentacle,pipes,stones};
            setDigsLeft(4);
        } else if (age == PeriodAge.JURASSIC){
            this.lootPool = new Fossil[]{pipes,stones,trash};
        } else if (age == PeriodAge.UNSPECIFIED){
            this.lootPool = new Fossil[]{intFos,stones,trash};
        }
    }

                        //setters

    public void setPeriodAge(PeriodAge period){
        this.age = period;
    }

    public void setLessDigs(int left){
        if (getDigsLeft()>0){
            this.digsLeft = getDigsLeft()-left;
        } else {
            setIsDugOut(true);
        }
    }
    public void setIsDugOut(boolean dugOut) {
        isDugOut = dugOut;
    }
    public void setDigsLeft(int digsLeft) {
        this.digsLeft = digsLeft;
    }

                        //getters

    public int getDigsLeft() {
        return digsLeft;
    }

    public Fossil[] getLootPool() {
//        for (int i = 0; i<lootPool.length; i++){
//            if (lootPool[i]!=null){
//                System.out.println(lootPool[i].itemName);
//            }
//        }
        return lootPool;
    }

    public PeriodAge getAgePeriod(){
        return age;
    }
    @Override
    public void getExcavated(){
        System.out.println(type + " частично выкопали");
    }
}
