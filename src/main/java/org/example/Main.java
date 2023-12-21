package org.example;
import org.example.classes.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //creating instances and setting stats

        Human h1 = new Human("Pebody", ResearcherType.EXPEDITIONIST);
        Human h2 = new Human("Wilmart", ResearcherType.FOLK_RESEARCHER);
        h1.setStat(100, 60, 50, 15, 90);
        h2.setStat(100, 60, 50, 15, 90);
        Item shovel = new Item("Shovel");
        shovel.addToInventory(h1);
        GeologicalLayer gl = new GeologicalLayer("layer", "", PeriodAge.EOCENOS);
        NecronExtract ext1 = new NecronExtract("extract 1", 15, 15);
        NecronExtract ext2 = new NecronExtract("e2", 15, 15);

        // program

        while (true) {
            System.out.println();
            Scanner scan = new Scanner(System.in);
            System.out.println("wjat to do? (dig/search/attack/read)");
            String inq = scan.next();
            if (inq.equals("fight")) {
                System.out.println("who?");
                String who = scan.next();
                if (who.equals("h2")) {
                    h1.attack(h2);
                } else {
                    h2.attack(h1);
                }
            } else if (inq.equals("dig")) {
                h1.dig(gl);
                for (int i = 0; i < 4; i++) {
                    if (h1.inventory[i] != null) {
                        System.out.println(h1.inventory[i].itemName);
                    }
                }
            } else if (inq.equals("search")) {
                gl = h1.searchLayer();
                System.out.println("layer found\t" + gl.getAgePeriod());
            } else if (inq.equals("read")) {
                System.out.println("what?");
                scan.nextLine();
                String what = scan.nextLine();
                if (what.equals(ext1.itemName)) {
                    h1.readext(ext1);
                } else if (what.equals(ext2.itemName)) {
                    h1.readext(ext2);
                }
//                System.out.println("\tfinished");
            }
        }
    }
}