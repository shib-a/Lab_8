package org.example;
import org.example.classes.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Human h1 = new Human("Pebody", ResearcherType.EXPEDITIONIST);
        Human h2 = new Human("Wilmart", ResearcherType.FOLK_RESEARCHER);
        h1.setStat(100, 80, 50, 15, 90);
        h2.setStat(100, 80, 50, 15, 90);
        Item shovel = new Item("Shovel");
        shovel.addToInventory(h1);
        GeologicalLayer gl = new GeologicalLayer("layer", "sex", PeriodAge.EOCENOS);


        while (true){
            System.out.println();
            Scanner scan = new Scanner(System.in);
            System.out.println("wjat to do?");
            String inq = scan.next();
            if (inq.equals("fight")){
                System.out.println("who?");
                String who = scan.next();
                if (who.equals("h2")){
                    h1.attack(h2);
                } else {
                    h2.attack(h1);
                }
            } else {
                h1.dig(gl);
                for (int i = 0; i < 4; i++) {
                    if (h1.inventory[i] != null) {
                        System.out.println(h1.inventory[i].itemName);
                    }
                }
                System.out.println("\tfinished");
            }
    }
}
}