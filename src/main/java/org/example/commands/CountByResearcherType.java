package org.example.commands;

import org.example.exceptions.*;
import org.example.classes.ResearcherType;
import org.example.collection.HumanCollection;
import org.example.exceptions.InvalidArgumentException;

import java.util.Scanner;
//
//public class CountByResearcherType extends AbstractCommand{
//    public CountByResearcherType() {
//        super(false, true);
//    }
//
//    @Override
//    public  execute(){}
//
//    @Override
//    public void execWithCol(HumanCollection obj) {
//        if (obj.getHumanArrayList().isEmpty()){
//            System.out.println("Nothing to count. Your collection is empty.");
//        } else {
//            Scanner sc = new Scanner(System.in);
//            System.out.println("Enter the researcher type: FOLK_RESEARCHER or EXPEDITIONIST");
//            String inp = sc.nextLine();
//            int count = 0;
//            if (inp.equals("FOLK_RESEARCHER")){
//                for(int i = 0; i<obj.getHumanArrayList().size(); i++){
//                    if (obj.getHumanArrayList().get(i).getType()== ResearcherType.FOLK_RESEARCHER){
//                        count+=1;
//                    }
//                }
//                System.out.println(count);
//            } else if (inp.equals("EXPEDITIONIST")){
//                for(int i = 0; i<obj.getHumanArrayList().size(); i++){
//                    if (obj.getHumanArrayList().get(i).getType()== ResearcherType.FOLK_RESEARCHER){
//                        count+=1;
//                    }
//                }
//                System.out.println(count);
//            } else {
//                System.out.println("Incorrect argument.");
//            }
//
//        }
//    }
//
//    @Override
//    public String getName() {
//        return null;
//    }
//
//    @Override
//    public String getDescription() {
//        return null;
//    }
//
//    @Override
//    public boolean checkIsValidArg() {
//        return false;
//    }
//}
