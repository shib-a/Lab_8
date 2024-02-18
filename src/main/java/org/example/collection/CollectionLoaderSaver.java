package org.example.collection;
import org.apache.commons.csv.*;
import org.example.classes.*;
import org.example.exceptions.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CollectionLoaderSaver {
    HumanCollection hc;
    public CollectionLoaderSaver(){}
    public void readFrom(String absolutePath) throws IOException, InvalidArgumentException, IllegalAccessException {
        try {
            File csvData = new File(absolutePath);
            CSVParser parser = CSVParser.parse(csvData, StandardCharsets.UTF_8,CSVFormat.DEFAULT);
            HumanCollection hc = new HumanCollection();
            for (CSVRecord record: parser){
                List<String> vals = record.toList();
                try {
                    ToolKinds pt = switch (vals.get(1).strip()) {
                        case ("GUN") -> ToolKinds.GUN;
                        case ("DRILL") -> ToolKinds.DRILL;
                        case ("SHOVEL") -> ToolKinds.SHOVEL;
                        case ("JACKHAMMER") -> ToolKinds.JACKHAMMER;
                        default -> null;
                    };
                    ResearcherType rt = switch (vals.get(2)) {
                        case ("EXPEDITIONIST") -> ResearcherType.EXPEDITIONIST;
                        case ("FOLK_RESEARCHER") -> ResearcherType.FOLK_RESEARCHER;
                        default -> null;
                    };
                    Human newhuman = new Human(vals.get(0), pt, rt, true);
                        try{double newhp = Double.parseDouble(vals.get(4));
                        double newint = Double.parseDouble(vals.get(5));
                        double newluck = Double.parseDouble(vals.get(6));
                        double newdmg = Double.parseDouble(vals.get(7));
                        double newsan = Double.parseDouble(vals.get(8));
                        newhuman.setStat(newhp,newint,newluck,newdmg,newsan);
                        hc.getHumanArrayList().add(newhuman);
                        } catch (NumberFormatException e){
                            System.out.println(new InvalidArgumentException().getMessage());
                        }
                } catch (InvalidArgumentException e){
                    System.out.println(e.getMessage());
                }
            }
            setHc(hc);
        } catch (IOException e){
            e.printStackTrace();
        }

    }
    public void writeTo(String absolutePath, HumanCollection hc){

    }

    public HumanCollection getHc() {
        return hc;
    }

    public void setHc(HumanCollection hc){
        this.hc=hc;
    }

    public void clear(){
        hc.getHumanArrayList().clear();
    }
}
