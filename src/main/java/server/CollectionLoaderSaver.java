package server;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import server.CommandLine;
import server.Human;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

import static server.Human.fromCsvStr;

/**
 * This class manages loading and saving the collection from and into a file
 */
public class CollectionLoaderSaver implements Serializable {
    private static final long serialVersionUID = 1L;
    private String fileName;
    private CommandLine cl;
    public CollectionLoaderSaver(String fileName, CommandLine cl){this.fileName = fileName;this.cl = cl;}

    /**
     * Turns a collection into an array of CSV-formatted strings
     * @param col
     * @return String[]
     */
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

    /**
     * Writes CSV-formatted strings with human field values into a file
     * @param col
     */
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
                System.out.println(">Collection saved.");
            } catch (NullPointerException | IOException e){return;}
        }catch (IOException e){}
    }
    public void writeToFile(ArrayList<String> col){
        BufferedOutputStream wr = null;
//        ArrayList<String> data = arrToCsvArr(col, s);
        if(col==null){return;}
        try {
            FileOutputStream fls = new FileOutputStream(fileName);
            wr = new BufferedOutputStream(fls);
            try{
                for(String el:col) {
                    wr.write(el.getBytes());
                    wr.write("\n".getBytes());
                }
                wr.flush();
                System.out.println(">Collection saved.");
            } catch (NullPointerException | IOException e){return;}
        }catch (IOException e){}
    }
    public ArrayList<String> arrToCsvArr(ArrayList<String> arr, int s){
        ArrayList<String> ans = new ArrayList<>();
        for(int i = 0; i < s-8; i+=8){
           ans.add(arr.get(i)+","+arr.get(i+1)+","+arr.get(i+2)+","+arr.get(i+3)+","+arr.get(i+4)+","+arr.get(i+5)+","+arr.get(i+6)+","+arr.get(i+7));
        }
        return ans;
    }
    /**
     * Parses a CSV file, turns extracted values to Human instances and adds them to a collection
     * @param fileName
     * @return ArrayList<Human></>
     */
    public ArrayList<Human> readFromFile(String fileName){
        ArrayList<Human> col = new ArrayList<>();
        if (!(fileName == null) && !fileName.isEmpty()){
            try{
                FileInputStream f = new FileInputStream(fileName);
                BufferedInputStream is = new BufferedInputStream(f);
                CSVParser csvp = CSVParser.parse(is,StandardCharsets.UTF_8,CSVFormat.DEFAULT);
                for(CSVRecord line:csvp){
//                    if (!(line.values().length>=11 && line.values().length<=19)) throw new ArrayIndexOutOfBoundsException();
                    try{
                        var st = line.values();
                        String str = "";
                        Human newHuman;
                        for(int i = 0; i<st.length;i++){
                            str+=line.values()[i]+",";
                        }
                        str = str.substring(0, str.lastIndexOf(","));
                        newHuman = fromCsvStr(str);
                        if (newHuman.validate()){col.add(newHuman);} else {cl.printException(">Invalid data in the collection file.");};
                    } catch (NullPointerException | ArrayIndexOutOfBoundsException | NoSuchElementException e){
                        System.out.println(">Deserialization error: "+e.getMessage());
                    }
                }
            } catch (IOException | ArrayIndexOutOfBoundsException e){
                if(e.getClass()==ArrayIndexOutOfBoundsException.class){
                    System.out.println(">Something's wrong with the amount of values. Please check the values in the file.");
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
