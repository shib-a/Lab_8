package org.example.classes;

import java.util.Random;
import java.util.Scanner;

public class Human {
    private final String name;
    private boolean isAlive = true;
    private final ResearcherType type;
    public Item[] inventory = new Item[4];
    private double[] mas = new double[5];
    private int dugCounter = 0;
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

    public int getDugCounter(){
        return dugCounter;
    }
    public void setDugCounter(int amount){
        this.dugCounter = amount;
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



    public void read(Book book){
        changeStat(Stat.INTELLIGENCE,book.getIntelligenceEffect());
        if (book.getIntelligenceEffect()>0){
            System.out.println(this.name + ": " + "I can feel the knowledge coming inside of me!" + "\tRead: " + book.itemName + "\t Current intelligence: " + this.getStat(Stat.INTELLIGENCE));
        } else if (book.getIntelligenceEffect()<0){
            System.out.println(this.name + ": " + "I can feel my braincells dying. It was entertaining tho." + "\tRead: " + book.itemName + " \tCurrent intelligence: " + this.getStat(Stat.INTELLIGENCE));
        }
    }



    public void dig(GeologicalLayer layer){
        if (layer.getDigsLeft()>0) {
            Fossil recievedLoot = new Fossil(null, null);
            if (this.getDugCounter() < 10) {
                double roll = Math.random() * 100;
                if (((int) roll) % 6 == 0) {
                    for (Fossil loot : layer.getLootPool()) {
                        if (loot.getRarity() == 6) {
                            recievedLoot = loot;
                            this.setDugCounter(0);
                            break;
                        }
                    }
                } else {
                    recievedLoot = layer.getLootPool()[2];
                    this.setDugCounter(this.getDugCounter() + 1);
                }
            } else {
                recievedLoot = layer.getLootPool()[0];
                this.setDugCounter(0);
            }
            System.out.println(recievedLoot.itemName + " found. pick up?\t" + getDugCounter());
            Scanner scan = new Scanner(System.in);
            String cons = scan.next();
            if (cons.equals("yes")) {
                recievedLoot.addToInventory(this);
            }
            layer.setLessDigs(1);
        } else {
            System.out.println("Can't dig anymore: the layer has completely been dug out");
        }
    }
}