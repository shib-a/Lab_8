package common;
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
    protected final Status status;
    @ReadMarkedField
    protected final Color color;
    public boolean isAlive;
    @ReadMarkedField
    public Item[] inventory = new Item[]{null, null, null, null};
    private double[] mas = new double[5];
    private String owner;
    public Rarity rarity;
    public Coordinates coordinates;
    @ReadMarkedField
    private int dugCounter = 0;
    public Human(String name, Status status, Color color, boolean isAlive){
        this.name = name;
        this.status = status;
        this.color = color;
        this.isAlive = isAlive;
    }
    public Human(int id, String name, Status status, Color color, boolean isAlive, double hp, double intel, double lck, double dmg, double san, Coordinates cords, String owner){
        this.id=id;
        this.name = name;
        this.status = status;
        this.color = color;
        this.isAlive = isAlive;
        this.mas[Stat.HP.ordinal()] = hp;
        this.mas[Stat.INTELLIGENCE.ordinal()] = intel;
        this.mas[Stat.LUCK.ordinal()] = lck;
        this.mas[Stat.DAMAGE.ordinal()] = dmg;
        this.mas[Stat.SANITY.ordinal()] = san;
        this.coordinates = cords;
        this.owner = owner;
        if(this.getStat(Stat.HP)<=0){isAlive=false;} else{isAlive=true;}
    }
    public Human(String name, Status status, Color color, boolean isAlive, double hp, double intel, double lck, double dmg, double san, Rarity rarity){
        this.name = name;
        this.status = status;
        this.color = color;
        this.isAlive = isAlive;
        this.mas[Stat.HP.ordinal()] = hp;
        this.mas[Stat.INTELLIGENCE.ordinal()] = intel;
        this.mas[Stat.LUCK.ordinal()] = lck;
        this.mas[Stat.DAMAGE.ordinal()] = dmg;
        this.mas[Stat.SANITY.ordinal()] = san;
        this.rarity=rarity;
        if(this.getStat(Stat.HP)<=0){isAlive=false;} else{isAlive=true;}
    }
    public Human(int id, String name, Status status, Color color, boolean isAlive, double hp, double intel, double lck, double dmg, double san, int dc, String owner, Item[] inv){
        this.id=id;
        this.name = name;
        this.status = status;
        this.color = color;
        this.isAlive = isAlive;
        this.mas[Stat.HP.ordinal()] = hp;
        this.mas[Stat.INTELLIGENCE.ordinal()] = intel;
        this.mas[Stat.LUCK.ordinal()] = lck;
        this.mas[Stat.DAMAGE.ordinal()] = dmg;
        this.mas[Stat.SANITY.ordinal()] = san;
        this.dugCounter = dc;
        this.owner = owner;
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

    public void setId(int id) {
        this.id = id;
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

    public Rarity getRarity() {
        return rarity;
    }

    public double getStat(Stat stat){
        return mas[stat.ordinal()];
    }
    public String getName(){
        return name;
    }
    public Color getColor() {
        return color;
    }
    public boolean getIsAlive(){
        return isAlive;
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
//        if (getType()==ResearcherType.FOLK_RESEARCHER) {
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
        if (status == null){return false;}
        if (this.getColor()==null) {return false;}
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
                ", preferredTool=" + status +
                ", type=" + color +
                ", inventory=" + Arrays.toString(inventory) +
                ", stats=" + Arrays.toString(mas) +
                ", dugCounter=" + dugCounter +
                ", rarity=" + rarity+
                ", owner=" + owner +
                '}';
    }

    /**
     * Turns all Human fields into a CSV-formatted string
     * @return
     */
    public String toCsvStr(){
        String csvStr = getId()+","+getName().toString()+","+ status.toString()+","+ color.toString()+","+isAlive+","+getStat(Stat.HP)+","+getStat(Stat.INTELLIGENCE)+","+getStat(Stat.LUCK)+","+getStat(Stat.DAMAGE)+","+getStat(Stat.SANITY)+","+dugCounter;
//        for(Item el: inventory){
//            if (el==null){csvStr+=","+null;}
//            else if(el.getClass().toString().contains("Tool")){
//                csvStr+=","+el.itemName+((Tool) el).kind;
//            } else if (el.getClass().toString().contains("Book")) {
////                csvStr+=","+el.itemName+((Book) el).getIntelligenceEffect();
//            } else if (el!=null) {
//                csvStr+=","+el.itemName;
//            } else {csvStr+=","+null;}
//        }
        return csvStr;
    }

    /**
     * Turns a CSV-formatted string into a Human instance
     * @param csvStr
     * @return Human
     */
    public static Human fromCsvStr(String csvStr){
        String[] splitStr = csvStr.split(",");
        Integer id;
        String name;
        Status status;
        Color color;
        Boolean isal;
        Double hp;
        Double intel;
        Double luck;
        Double dmg;
        Double san;
        Coordinates cords;
        String owner;
        Item[] inv = new Item[4];
        try{
            try{id = Integer.parseInt(splitStr[0]);} catch (NumberFormatException e){id = null;}
            name = splitStr[1];
            try{
                if (splitStr[2].equals("null")){
                    status = null;
                } else {status = Status.valueOf(splitStr[2]);}
            } catch (IllegalArgumentException e){status = null;}
            try{
                if (splitStr[3].equals("null")){
                    color = null;
                } else {color = Color.valueOf(splitStr[3]);}
            } catch (IllegalArgumentException e){color = null;}
            try{
                if (!splitStr[4].equals(null)){isal = Boolean.valueOf(splitStr[4]);} else {isal = null;}
            } catch (IllegalArgumentException e){isal = null;}
            try{hp = Double.parseDouble(splitStr[5].replace("[",""));} catch (NumberFormatException | NullPointerException e){hp = null;}
            try{intel = Double.parseDouble(splitStr[6]);} catch (NumberFormatException | NullPointerException e){ intel = null;}
            try{luck = Double.parseDouble(splitStr[7]);} catch (NumberFormatException | NullPointerException e){luck = null;}
            try{dmg = Double.parseDouble(splitStr[8]);} catch (NumberFormatException | NullPointerException e){dmg = null;}
            try{san = Double.parseDouble(splitStr[9]);} catch (NumberFormatException | NullPointerException e){san = null;}
            try{cords = new Coordinates(Double.parseDouble(splitStr[10]), Double.parseDouble(splitStr[11]));} catch (NumberFormatException | ArrayIndexOutOfBoundsException e){cords=null;}
            try{owner = splitStr[11];}catch (ArrayIndexOutOfBoundsException e){owner = null;}
            return new Human(id,name,status,color,isal,hp,intel,luck,dmg,san,cords,owner);
        } catch (ArrayIndexOutOfBoundsException e){}
        return null;
    }


    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }
    public String statsToString(){
        return getStat(Stat.HP)+","+getStat(Stat.INTELLIGENCE)+","+getStat(Stat.LUCK)+","+getStat(Stat.DAMAGE)+","+getStat(Stat.SANITY);
    }

    public Status getStatus() {
        return status;
    }
}