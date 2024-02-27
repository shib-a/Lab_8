package org.example.collection;
import org.apache.commons.csv.*;
import org.example.CommandLine;
import org.example.classes.*;
import org.example.exceptions.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class CollectionLoaderSaver {
    public String fileName;
    public CommandLine cl;
    public CollectionLoaderSaver(String fileName, CommandLine cl){this.fileName = fileName;this.cl = cl;}
    public String[] colToCsvArr(Collection<Human> col){
        String[] arr = new String[col.size()];
        try{
            int i = 0;
            for(Human h:col){
                arr[i]=h.toCsvStr();
                i++;
            }
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e){}
        return arr;
    }
    public void writeToFile(Collection<Human> col){
        BufferedOutputStream wr = null;
        String[] data = colToCsvArr(col);
        if(data==null){return;}
        try {
            FileOutputStream fls = new FileOutputStream(fileName);
            wr = new BufferedOutputStream(fls);
            try{
                for(String el:data) {
                    wr.write(el.getBytes());
                }
                wr.flush();
                System.out.println("Collection saved");
            } catch (NullPointerException | IOException e){return;}
        }catch (FileNotFoundException e){}
        finally {
            try {
                wr.close();
            } catch (IOException e){
                System.out.println("Failed to close file");
            }
        }
    }
    public Collection<Human> readFromFile(String fileName){
        BufferedInputStream is = null;
        ArrayList<Human> col = new ArrayList<>();
        if (!(fileName == null) && !fileName.isEmpty()){
            try{

            } catch (Exception e){}
        }
        return col;
    }
//    public Human fromCsvStr(String csvStr){
//        String[] splitStr = csvStr.split(",");
//        Integer id;
//        String name;
//        ToolKinds ft;
//        ResearcherType rt;
//        Boolean isal;
//        Double hp;
//        Double intel;
//        Double luck;
//        Double dmg;
//        Double san;
//        Integer dc;
//        Item[] inv = new Item[4];
//        try{
//            try{id = Integer.parseInt(splitStr[0]);} catch (NumberFormatException e){id = null;}
//            name = splitStr[1];
//            try{
//                if (splitStr[2].equals("null")){
//                    ft = null;
//                } else {ft = ToolKinds.valueOf(splitStr[2]);}
//            } catch (IllegalArgumentException e){ft = null;}
//            try{
//                if (splitStr[3].equals("null")){
//                    rt = null;
//                } else {rt = ResearcherType.valueOf(splitStr[3]);}
//            } catch (IllegalArgumentException e){rt = null;}
//            try{
//                if (!splitStr[4].equals(null)){isal = Boolean.valueOf(splitStr[4]);} else {isal = null;}
//            } catch (IllegalArgumentException e){isal = null;}
//            try{hp = Double.parseDouble(splitStr[5]);} catch (NumberFormatException | NullPointerException e){hp = null;}
//            try{intel = Double.parseDouble(splitStr[6]);} catch (NumberFormatException | NullPointerException e){ intel = null;}
//            try{luck = Double.parseDouble(splitStr[7]);} catch (NumberFormatException | NullPointerException e){luck = null;}
//            try{dmg = Double.parseDouble(splitStr[8]);} catch (NumberFormatException | NullPointerException e){dmg = null;}
//            try{san = Double.parseDouble(splitStr[9]);} catch (NumberFormatException | NullPointerException e){san = null;}
//            try{dc = Integer.parseInt(splitStr[10]);} catch (NumberFormatException | ArrayIndexOutOfBoundsException e){dc = null;}
//            return new Human(id,name,ft,rt,isal,hp,intel,luck,dmg,san,dc);
//        } catch (ArrayIndexOutOfBoundsException e){}
//        return null;
//    }
//    public void readFrom(String absolutePath) throws IOException, InvalidArgumentException, IllegalAccessException {
//        try {
//            File csvData = new File(absolutePath);
//            CSVParser parser = CSVParser.parse(csvData, StandardCharsets.UTF_8,CSVFormat.DEFAULT);
//            HumanCollection hc = new HumanCollection();
//            for (CSVRecord record: parser){
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
//                        try{double newhp = Double.parseDouble(vals.get(4));
//                        double newint = Double.parseDouble(vals.get(5));
//                        double newluck = Double.parseDouble(vals.get(6));
//                        double newdmg = Double.parseDouble(vals.get(7));
//                        double newsan = Double.parseDouble(vals.get(8));
//                        newhuman.setStat(newhp,newint,newluck,newdmg,newsan);
//                        hc.getHumanArrayList().add(newhuman);
//                        } catch (NumberFormatException e){
//                            System.out.println(new InvalidArgumentException().getMessage());
//                        }
//                } catch (IllegalArgumentException e){
//                    System.out.println(e.getMessage());
//                }
//            }
//            setHc(hc);
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//
//    }
    public void setFileName(String fn){
        this.fileName = fn;
    }

}
