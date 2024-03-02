package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.classes.*;
import org.example.collection.*;
import org.example.exceptions.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CommandLine {
    public String line = "Lab5>";
    public static Scanner fileReader = null;
    public static Scanner defReader = new Scanner(System.in);
    public void print(Object o){
        System.out.print(o);
    }
    public void printLn(Object o){
        System.out.println(o);
    }
    public void printException(Object o){
        System.err.println("Unexpected exception" + o);
    }
    public String readln() throws NoSuchElementException, IllegalStateException {
        return (fileReader!=null?fileReader:defReader).nextLine();
    }

    public boolean canReadln() throws IllegalStateException {
        return (fileReader!=null?fileReader:defReader).hasNextLine();
    }

    public void printF(Object o,Object o2) {
        System.out.printf(o.toString());
        System.out.printf("\t|\t");
        System.out.printf(o2.toString()+"\n");
    }

    public void printLine() {
        print(line);
    }

    public String getLine() {
        return line;
    }
    public void selectFileScanner(Scanner scanner) {
        this.fileReader = scanner;
    }

    public void selectConsoleScanner() {
        this.fileReader = null;
    }

//    public void readFrom(String absolutePath) throws IOException, InvalidArgumentException, IllegalAccessException {
//        try {
//            File csvData = new File(absolutePath);
//            CSVParser parser = CSVParser.parse(csvData, StandardCharsets.UTF_8, CSVFormat.DEFAULT);
//            HumanCollection hc = new HumanCollection();
//            for (CSVRecord record : parser) {
//                List<String> vals = record.toList();
//                try {
//                    ToolKinds pt = switch (vals.get(1).strip()) {
//                        case ("GUN") -> ToolKinds.GUN;
//                        case ("DRILL") -> ToolKinds.DRILL;
//                        case ("SHOVEL") -> ToolKinds.SHOVEL;
//                        case ("JACKHAMMER") -> ToolKinds.JACKHAMMER;
//                        default -> null;
//                    };
//                    ResearcherType rt = switch (vals.get(2)) {
//                        case ("EXPEDITIONIST") -> ResearcherType.EXPEDITIONIST;
//                        case ("FOLK_RESEARCHER") -> ResearcherType.FOLK_RESEARCHER;
//                        default -> null;
//                    };
//                    Human newhuman = new Human(vals.get(0), pt, rt, true);
//                    try {
//                        double newhp = Double.parseDouble(vals.get(4));
//                        double newint = Double.parseDouble(vals.get(5));
//                        double newluck = Double.parseDouble(vals.get(6));
//                        double newdmg = Double.parseDouble(vals.get(7));
//                        double newsan = Double.parseDouble(vals.get(8));
//                        newhuman.setStat(newhp, newint, newluck, newdmg, newsan);
//                        hc.getHumanArrayList().add(newhuman);
//                    } catch (NumberFormatException e) {
//                        System.out.println(new InvalidArgumentException().getMessage());
//                    }
//                } catch (InvalidArgumentException e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//            setHc(hc);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
