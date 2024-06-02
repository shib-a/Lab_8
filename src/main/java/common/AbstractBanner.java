package common;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class AbstractBanner {
    public String name;
    private ArrayList<Human> lootPool;
    public RollReq req;
    public AbstractBanner(String name, ArrayList<Human> lootPool, RollReq req){
        this.lootPool=lootPool;
        this.name=name;
        this.req = req;
    }

    public ArrayList<Human> getLootPool() {
        return lootPool;
    }
    public Human roll() {
        double roll = Math.floor(Math.random() * 100);
        //different rarities for different types of layers
        if (roll > 19) {
            ArrayList<Human> sc = getLootPool().stream().filter((a) -> a.getRarity() == Rarity.THREE_STAR).collect(Collectors.toCollection(ArrayList::new));
            Random r = new Random();
            return sc.get(r.nextInt(sc.size()));
        } else if (roll > 9) {
            ArrayList<Human> sc = getLootPool().stream().filter((a) -> a.getRarity() == Rarity.FOUR_STAR).collect(Collectors.toCollection(ArrayList::new));
            Random r = new Random();
            return sc.get(r.nextInt(sc.size()));
        } else {
            ArrayList<Human> sc = getLootPool().stream().filter((a) -> a.getRarity() == Rarity.FIVE_STAR).collect(Collectors.toCollection(ArrayList::new));
            Random r = new Random();
            return sc.get(r.nextInt(sc.size()));
        }
    }
    public Human rollWarrant(){
        ArrayList<Human> sc = getLootPool().stream().filter((a) -> a.getRarity() == Rarity.FIVE_STAR).collect(Collectors.toCollection(ArrayList::new));
        Random r = new Random();
        return sc.get(r.nextInt(sc.size()));
    }
}
