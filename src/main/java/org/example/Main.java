package org.example;
import org.example.classes.*;
public class Main {
    public static void main(String[] args){
        Human h1 = new Human("Pebody",ResearcherType.EXPEDITIONIST);
        Human h2 = new Human("Wilmart",ResearcherType.FOLK_RESEARCHER);
        h1.setStat(100,80,50,15,90);
        h2.setStat(100,80,50,15,90);
        h1.getStat(Stat.HP);
        h1.attack(h2);
        h1.attack(h2);
        h1.attack(h2);
        Item shovel = new Item("Shovel");
        shovel.addToInventory(h1);
        shovel.addToInventory(h1);
        shovel.addToInventory(h1);
        shovel.addToInventory(h1);
        shovel.addToInventory(h1);
        for (int i = 0; i<4; i++){
            if (h1.inventory[i]!=null){
                System.out.println(h1.inventory[i].itemName);
            }

        }
        System.out.println("\tfinished");
    }
}