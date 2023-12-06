package org.example.classes;
import org.example.ReadMarkedField;
import org.example.interfaces.*;

public class GeologicalLayer extends GeologicalFormation implements Excavatable{
    @ReadMarkedField
    private int digsLeft;
    private boolean isDigable;
    private boolean isDugOut = false;
    @ReadMarkedField
    private PeriodAge age;
    private Fossil[] lootPool;
    public GeologicalLayer(String name, StoneDurability durability, PeriodAge age){
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
            setDigsLeft(2);
        } else if (age == PeriodAge.CRETATIOUS){
            this.lootPool = new Fossil[]{intFos, head, wing, tentacle, stones};
            setDigsLeft(3);
        } else if (age == PeriodAge.MIOCENOS){
            this.lootPool = new Fossil[]{tentacle,pipes,stones};
            setDigsLeft(4);
        } else if (age == PeriodAge.JURASSIC){
            this.lootPool = new Fossil[]{pipes,stones,trash};
            setDigsLeft(3);
        } else if (age == PeriodAge.UNSPECIFIED){
            this.lootPool = new Fossil[]{intFos,stones,trash};
            setDigsLeft(3);
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
    public boolean getIsDigable(){
        return isDigable;
    }
    @Override
    public void getExcavated(){
        System.out.println(type + " частично выкопали");
    }

    // local class

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

}
