package org.example.collection;
import org.apache.commons.csv.*;
import org.example.CommandLine;
import org.example.classes.*;
import org.example.exceptions.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.example.classes.Human.fromCsvStr;

public class CollectionLoaderSaver {
    private String fileName;
    private CommandLine cl;
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
                    wr.write("\n".getBytes());
                }
                wr.flush();
                System.out.println("Collection saved");
            } catch (NullPointerException | IOException e){return;}
        }catch (IOException e){}
    }

    public ArrayList<Human> readFromFile(String fileName){
        ArrayList<Human> col = new ArrayList<>();
        if (!(fileName == null) && !fileName.isEmpty()){
            try{
                FileInputStream f = new FileInputStream(fileName);
                BufferedInputStream is = new BufferedInputStream(f);
                CSVParser csvp = CSVParser.parse(is,StandardCharsets.UTF_8,CSVFormat.DEFAULT);
                for(CSVRecord line:csvp){
                    System.out.println(line.toString());
                    if (!(line.values().length>=11 && line.values().length<=19)) throw new ArrayIndexOutOfBoundsException();
                    try{
                        var st = line.values();
                        String str = "";
                        Human newHuman;
                        for(int i = 0; i<st.length;i++){
                            str+=line.values()[i]+",";
                        }
                        str = str.substring(0, str.lastIndexOf(","));
                        newHuman = fromCsvStr(str);
                        if (newHuman.validate()){col.add(newHuman);
                            System.out.println("added");} else {cl.printException("Invalid data in the collection file");};
                    } catch (NullPointerException | ArrayIndexOutOfBoundsException | NoSuchElementException e){
                        System.out.println("Deserialization error :"+e.getMessage());
                    }
                }
            } catch (IOException | ArrayIndexOutOfBoundsException e){
                if(e.getClass()==ArrayIndexOutOfBoundsException.class){
                    System.out.println("Something's wrong with the amount of values. Please check the values in the file");
                }
            }

        }
        return col;
    }
    public void setFileName(String fn){
        this.fileName = fn;
    }
    public String getFileName(){return fileName;}

}
