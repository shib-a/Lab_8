package org.example.commands;

import org.example.classes.Human;
import org.example.collection.HumanCollection;

import java.util.Scanner;

public class CountByResearcherType extends AbstractCommand{
    public CountByResearcherType() {
        super(false, true);
    }

    @Override
    public void execute() {

    }

    @Override
    public void execWithCol(HumanCollection obj) {
        if (obj.getHumanArrayList().isEmpty()){
            System.out.println("Nothing to count. Your collection is empty.");
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the researcher type: FOLK_RESEARCHER or EXPEDITIONIST");
            String inp = sc.nextLine();
            if (inp.equals("FOLK_RESEARCHER"))
            int count = 0;
            for(int i = 0; i<obj.getHumanArrayList().size(); i++){
                if (obj.getHumanArrayList().get(i).getType()==)
            }
        }
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean checkIsValidArg() {
        return false;
    }
}
