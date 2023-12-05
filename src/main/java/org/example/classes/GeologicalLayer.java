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
        if (age == PeriodAge.EOCENOS){
            this.lootPool = new Fossil[]{intFos, head, wing};
            setDigsLeft(3);

        }
    }

    public Fossil[] getLootPool() {
//        for (int i = 0; i<lootPool.length; i++){
//            if (lootPool[i]!=null){
//                System.out.println(lootPool[i].itemName);
//            }
//        }
        return lootPool;
    }
    public void setLessDigs(int left){
        if (getDigsLeft()>0){
            this.digsLeft = getDigsLeft()-left;
        } else {
            setIsDugOut(true);
        }

    }
    public void setDigsLeft(int digsLeft) {
        this.digsLeft = digsLeft;
    }

    public int getDigsLeft() {
        return digsLeft;
    }

    public void setIsDugOut(boolean dugOut) {
        isDugOut = dugOut;
    }

    public PeriodAge getAgePeriod(){
        return age;
    }
    @Override
    public void getExcavated(){
        System.out.println(type + " частично выкопали");
    }
}
