package common;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class AbstractBanner {
    public String name;
    private ArrayList<Human> lootPool;
    public RollReq req;
    private Logger logger = Logger.getLogger(";iughfreaw;jokuhwerfago;uiyhaswrfP");
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
        logger.info(String.valueOf(roll));
        //different rarities for different types of layers
        if (roll > 19) {
            ArrayList<Human> sc = getLootPool().stream().filter((a) -> a.getRarity() == Rarity.THREE_STAR).collect(Collectors.toCollection(ArrayList::new));
            logger.info(String.valueOf(sc));
            Random r = new Random();
            return sc.get(r.nextInt(sc.size()));
        } else if (roll > 9) {
            ArrayList<Human> sc = getLootPool().stream().filter((a) -> a.getRarity() == Rarity.FOUR_STAR).collect(Collectors.toCollection(ArrayList::new));
            logger.info(String.valueOf(sc));
            Random r = new Random();
            return sc.get(r.nextInt(sc.size()));
        } else {
            ArrayList<Human> sc = getLootPool().stream().filter((a) -> a.getRarity() == Rarity.FIVE_STAR).collect(Collectors.toCollection(ArrayList::new));
            logger.info(String.valueOf(sc));
            Random r = new Random();
            return sc.get(r.nextInt(sc.size()));
        }
    }
    public Human rollWarrant(){
        ArrayList<Human> sc = getLootPool().stream().filter((a) -> a.getRarity() == Rarity.FIVE_STAR).collect(Collectors.toCollection(ArrayList::new));
        Random r = new Random();
        return sc.get(r.nextInt(sc.size()));
    }

    @Override
    public String toString() {
        return "AbstractBanner{" +
                "name='" + name + '\'' +
                ", lootPool=" + lootPool +
                ", req=" + req +
                '}';
    }
}
