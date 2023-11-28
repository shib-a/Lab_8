package org.example.classes;

public class Human {
    private final String name;
    private boolean isAlive = true;
    private final ResearcherType type;
    public Item[] inventory = new Item[4];
    private double[] mas = new double[5];
    public void read(String what){
    }
    public void talk(String aboutWhat){
    }
    public void write(String aboutWhat){
    }
    public String getName(){
        return name;
    }
    public Human(String name, ResearcherType type){
        this.name = name;
        this.type = type;
    }

    public void setStat(double hp, double intel, double lck, double dmg, double san){
        this.mas[Stat.HP.ordinal()] = hp;
        this.mas[Stat.INTELLIGENCE.ordinal()] = intel;
        this.mas[Stat.LUCK.ordinal()] = lck;
        this.mas[Stat.DAMAGE.ordinal()] = dmg;
        this.mas[Stat.SANITY.ordinal()] = san;
    }
    public Item[] getInventory(){
        return inventory;
    }

    public double getStat(Stat stat){
        return mas[stat.ordinal()];
    }
    public void setNewStat(Stat stat, double value){
        mas[stat.ordinal()] = value;
    }
    public void changeStat(Stat stat, double value){
        mas[stat.ordinal()] = mas[stat.ordinal()]+value;
    }
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
}