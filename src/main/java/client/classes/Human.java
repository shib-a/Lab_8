package client.classes;
import common.*;
import client.classes.unused.Book;
import client.classes.unused.NecronExtract;
import client.exceptions.EmptyInventoryException;
import client.interfaces.ReadMarkedField;

import java.util.Arrays;
import java.util.Scanner;

/**
 * The core class
 */
public class Human implements Comparable<Human> {
    private int id;
    @ReadMarkedField
    public final String name;
    protected final Status preferredTool;
    public Rarity rarity;
    @ReadMarkedField
    protected final Color type;
    public boolean isAlive;
    @ReadMarkedField
    public Item[] inventory = new Item[4];
    private double[] mas = new double[5];
    @ReadMarkedField
    private int dugCounter = 0;
    public Human(String name, Status preferredTool, Color type, boolean isAlive){
        this.name = name;
        this.preferredTool = preferredTool;
        this.type = type;
        this.isAlive = isAlive;
    }
    public Human(int id, String name, Status preferredTool, Color type, boolean isAlive, double hp, double intel, double lck, double dmg, double san, int dc){
        this.id=id;
        this.name = name;
        this.preferredTool = preferredTool;
        this.type = type;
        this.isAlive = isAlive;
        this.mas[Stat.HP.ordinal()] = hp;
        this.mas[Stat.INTELLIGENCE.ordinal()] = intel;
        this.mas[Stat.LUCK.ordinal()] = lck;
        this.mas[Stat.DAMAGE.ordinal()] = dmg;
        this.mas[Stat.SANITY.ordinal()] = san;
        this.dugCounter = dc;
        if(this.getStat(Stat.HP)<=0){isAlive=false;} else{isAlive=true;}
    }
    public Human(int id, String name, Status preferredTool, Color type, boolean isAlive, double hp, double intel, double lck, double dmg, double san, int dc, Item[] inv){
        this.id=id;
        this.name = name;
        this.preferredTool = preferredTool;
        this.type = type;
        this.isAlive = isAlive;
        this.mas[Stat.HP.ordinal()] = hp;
        this.mas[Stat.INTELLIGENCE.ordinal()] = intel;
        this.mas[Stat.LUCK.ordinal()] = lck;
        this.mas[Stat.DAMAGE.ordinal()] = dmg;
        this.mas[Stat.SANITY.ordinal()] = san;
        this.dugCounter = dc;
        for(Item el: inv){addToInventory(el);}
    }

    //setters

    public void setStat(double hp, double intel, double lck, double dmg, double san){
        this.mas[Stat.HP.ordinal()] = hp;
        this.mas[Stat.INTELLIGENCE.ordinal()] = intel;
        this.mas[Stat.LUCK.ordinal()] = lck;
        this.mas[Stat.DAMAGE.ordinal()] = dmg;
        this.mas[Stat.SANITY.ordinal()] = san;
    }
    public void setDugCounter(int amount){
        this.dugCounter = amount;
    }
    public void setNewStat(Stat stat, double value){
        mas[stat.ordinal()] = value;
    }
    public void changeStat(Stat stat, double value){
        mas[stat.ordinal()] = mas[stat.ordinal()]+value;
    }

    //getters

    public int getDugCounter(){
        return dugCounter;
    }
    public String[] getInventory() throws EmptyInventoryException {
        String[] mn = new String[4];
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            if (inventory[i]!=null){
                for (int j = 0; j < 4; j++) {
                    if (mn[i]==null){
                        mn[i] = inventory[i].itemName;
                    }
                }
            }
            cnt+=1;
        }
        if (cnt==4 && mn[0]==null){
            throw new EmptyInventoryException("nothing to return: empty inventory");
        }
        return mn;
    }
    public double getStat(Stat stat){
        return mas[stat.ordinal()];
    }
    public String getName(){
        return name;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public Color getType() {
        return type;
    }
    //other methods

    public void attack(Human this, Human defender){
        System.out.println("- "+ this.name + ": Get a taste of that!");
        defender.recieveDamage(this.mas[Stat.DAMAGE.ordinal()]);
        if (defender.mas[Stat.HP.ordinal()] <=0){
            System.out.println(defender.name + " died");
            defender.isAlive = false;
            defender = null;
            System.gc();
        }
    }

    public void recieveDamage(double damage){
        changeStat(Stat.HP,-damage);
        if (mas[Stat.HP.ordinal()]<=0){
            System.out.println(name + " died");
        }
        else{
            System.out.println("- "+ name + ": That shit hurt! HP left: " + mas[Stat.HP.ordinal()]);
        }
    }

//    public void read(Book book){
//        changeStat(Stat.INTELLIGENCE,book.getIntelligenceEffect());
//        if (book.getIntelligenceEffect()>0){
//            System.out.println(this.name + ": " + "I can feel the knowledge coming inside of me!" + "\tRead: " + book.itemName + "\t Current intelligence: " + this.getStat(Stat.INTELLIGENCE));
//        } else if (book.getIntelligenceEffect()<0){
//            System.out.println(this.name + ": " + "I can feel my braincells dying. It was entertaining tho." + "\tRead: " + book.itemName + " \tCurrent intelligence: " + this.getStat(Stat.INTELLIGENCE));
//        }
//    }
//    public void readext(NecronExtract extract){
//        changeStat(Stat.INTELLIGENCE,extract.getIntelligenceEffect());
//        changeStat(Stat.LUCK,extract.getSanityEffect()/2);
//        if (getType()== Color.FOLK_RESEARCHER) {
//            changeStat(Stat.SANITY,-(extract.getSanityEffect()/2));
//        } else {
//            changeStat(Stat.SANITY,-(extract.getSanityEffect()));
//        }
//        System.out.print(this.name + ": " + "That was... Interesting." + "\tRead: " + extract.itemName + "\t Current intelligence/Sanity/Luck: " + this.getStat(Stat.INTELLIGENCE) + " ");
//        System.out.print(getStat(Stat.SANITY) + " ");
//        System.out.println(getStat(Stat.LUCK));
//    }


    public void drop(Item item){
        int count=0;
        for (int i=0; i< inventory.length;i++){
            if (item==inventory[i]){
                inventory[i] = null;
                System.out.println("Dropped " + item.itemName);
            } else {
                count++;
            }
        }
        if (count==inventory.length-1){
            System.out.println("No such item in inventory: " + item.itemName);
        }
    }

    @Override
    public int compareTo(Human o) {
        return (int) (this.getStat(Stat.DAMAGE)-o.getStat(Stat.DAMAGE));
    }

    /**
     * Determines whether a Human instance was correctly created
     * @return boolean
     */
    public boolean validate(){
        if (id<=0){return false;}
        if (name==null || name.isEmpty() || name.isBlank()) return false;
        if (preferredTool == null){return false;}
        if (this.getType()==null) {return false;}
        return true;
    }

    public int getId(){
        return id;
    }
    public void addToInventory(Item it){
        int count = 0;
        for(int i = 0; i < 4; i++){
            if (inventory[i] == null){
                inventory[i] = it;
                break;
            }else{
                count++;
            }
        }
        if (count==4){
            System.out.println("Unable to take " + it +": Inventory is full!");
        }
    }

    @Override
    public String toString() {
        return "Human{" + "id=" + getId()+
                ", name='" + name + '\'' +
                ", isAlive=" + isAlive +
                ", preferredTool=" + preferredTool +
                ", type=" + type +
                ", inventory=" + Arrays.toString(inventory) +
                ", stats=" + Arrays.toString(mas) +
                ", dugCounter=" + dugCounter +
                '}';
    }

    /**
     * Turns all Human fields into a CSV-formatted string
     * @return
     */
//    public String toCsvStr(){
//        String csvStr = getId()+","+getName().toString()+","+preferredTool.toString()+","+type.toString()+","+isAlive+","+getStat(Stat.HP)+","+getStat(Stat.INTELLIGENCE)+","+getStat(Stat.LUCK)+","+getStat(Stat.DAMAGE)+","+getStat(Stat.SANITY)+","+dugCounter;
//        for(Item el: inventory){
//            if(el.getClass().toString().contains("Tool")){
//                csvStr+=","+el.itemName+((Status) el).kind;
//            } else if (el.getClass().toString().contains("Book")) {
//                csvStr+=","+el.itemName+((Book) el).getIntelligenceEffect();
//            } else if (el!=null) {
//                csvStr+=","+el.itemName;
//            } else {csvStr+=","+null;}
//        }
//        return csvStr;
//    }

    /**
     * Turns a CSV-formatted string into a Human instance
     * @param csvStr
     * @return Human
     */
    public static Human fromCsvStr(String csvStr){
        String[] splitStr = csvStr.split(",");
        Integer id;
        String name;
        Status ft;
        Color rt;
        Boolean isal;
        Double hp;
        Double intel;
        Double luck;
        Double dmg;
        Double san;
        Integer dc;
        Item[] inv = new Item[4];
        try{
            try{id = Integer.parseInt(splitStr[0]);} catch (NumberFormatException e){id = null;}
            name = splitStr[1];
            try{
                if (splitStr[2].equals("null")){
                    ft = null;
                } else {ft = Status.valueOf(splitStr[2]);}
            } catch (IllegalArgumentException e){ft = null;}
            try{
                if (splitStr[3].equals("null")){
                    rt = null;
                } else {rt = Color.valueOf(splitStr[3]);}
            } catch (IllegalArgumentException e){rt = null;}
            try{
                if (!splitStr[4].equals(null)){isal = Boolean.valueOf(splitStr[4]);} else {isal = null;}
            } catch (IllegalArgumentException e){isal = null;}
            try{hp = Double.parseDouble(splitStr[5].replace("[",""));} catch (NumberFormatException | NullPointerException e){hp = null;}
            try{intel = Double.parseDouble(splitStr[6]);} catch (NumberFormatException | NullPointerException e){ intel = null;}
            try{luck = Double.parseDouble(splitStr[7]);} catch (NumberFormatException | NullPointerException e){luck = null;}
            try{dmg = Double.parseDouble(splitStr[8]);} catch (NumberFormatException | NullPointerException e){dmg = null;}
            try{san = Double.parseDouble(splitStr[9]);} catch (NumberFormatException | NullPointerException e){san = null;}
            try{dc = Integer.parseInt(splitStr[10].replace("]",""));} catch (NumberFormatException | ArrayIndexOutOfBoundsException e){dc = null;}
//            if (splitStr.length>11){
//                try{
//                    int i = 0;
//                    for(int k=11;k<splitStr.length-1;k++) {
//                        if (splitStr[k] != "null") {
//                            try {
//                                if (k == splitStr.length - 2) {
//                                    if (Arrays.toString(Status.values()).contains(splitStr[k + 1])) {
//                                        try {
//                                            inv[i] = new Status(splitStr[k], Status.valueOf(splitStr[k + 1]));
//                                            k++;
//                                        } catch (IllegalArgumentException e) {
//                                            System.out.println(">No such type.");
//                                        }
//                                    } else {
//                                        inv[i] = new Item(splitStr[k]);
//                                        inv[i + 1] = new Item(splitStr[k + 1]);
//                                    }
//                                    i++;
//                                }
//                                if (Arrays.toString(Status.values()).contains(splitStr[k + 1])) {
//                                    try {
//                                        inv[i] = new Tool(splitStr[k], Status.valueOf(splitStr[k + 1]));
//                                        k++;
//                                    } catch (IllegalArgumentException e) {
//                                        System.out.println(">No such type.");
//                                    }
//                                } else {
//                                    inv[i] = new Item(splitStr[k]);
//                                }
//                            }catch (ArrayIndexOutOfBoundsException e){}
//                        } else if(k== splitStr.length-2) continue;
//                        i++;
//                    }
//                    return new Human(id,name,ft,rt,isal,hp,intel,luck,dmg,san,dc,inv);
//                }  catch (ArrayIndexOutOfBoundsException e){
//                    System.out.println(">Too many arguments! "+ Arrays.toString(e.getStackTrace()));
//                }
//            } else{
                return new Human(id,name,ft,rt,isal,hp,intel,luck,dmg,san,dc);
        } catch (ArrayIndexOutOfBoundsException e){}
        return null;
    }

}