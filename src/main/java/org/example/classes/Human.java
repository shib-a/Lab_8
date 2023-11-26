package org.example.classes;

public class Human {
    private String name;
    private ResearcherType type;
    public void read(String what){
    }
    public void talk(String aboutWhat){
    }
    public void write(String aboutWhat){
    }
    public String getName(){
        return name;
    }
    public double[] mas = new double[5];
    public Human(String name, ResearcherType type){
        this.name = name;
        this.type = type;
    }

//    public void setStats(double hp, double intel, double luck, double dmg, double san){
//        this.health = hp;
//        this.intelligence = intel;
//        this.luck = luck;
//        this.damage = dmg;
//        this.sanity = san;
//    }
//    private ArrayList<Double> stats = new ArrayList<>(0);
//
//    public void setStats(double hp, double intel, double luck, double dmg, double san ) {
//        stats.add(hp);
//        stats.add(intel);
//        stats.add(luck);
//        stats.add(dmg);
//        stats.add(san);
//    }
//    public ArrayList<Double> getStats(){
//        return stats;
//    }
    public void setStat(double hp, double intel, double lck, double dmg, double san){
        this.mas[Stat.HP.ordinal()] = hp;
        this.mas[Stat.INTELLIGENCE.ordinal()] = intel;
        this.mas[Stat.LUCK.ordinal()] = lck;
        this.mas[Stat.DAMAGE.ordinal()] = dmg;
        this.mas[Stat.SANITY.ordinal()] = san;
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
    }
    public void recieveDamage(double damage){
        changeStat(Stat.HP,-damage);
        if (mas[Stat.HP.ordinal()]<=0){
            System.out.println(name + " died");
            System.gc();
        }
        else{
            System.out.println("- "+ name + ": That shit hurt! HP left: " + mas[Stat.HP.ordinal()]);
        }
    }
}