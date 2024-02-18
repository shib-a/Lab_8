package org.example.collection;
import org.apache.commons.csv.*;
import org.example.classes.Human;
import org.example.classes.ResearcherType;
import org.example.classes.ToolKinds;
import org.example.exceptions.InvalidAbsolutePathException;
import org.example.exceptions.InvalidArgumentException;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FileLoaderSaver {
    public FileLoaderSaver(){}
    public void readFromInto(String absolutePath, HumanCollection hc) throws IOException, InvalidArgumentException {
        File csvData = new File(absolutePath);
        CSVParser parser = CSVParser.parse(csvData, StandardCharsets.UTF_8,CSVFormat.DEFAULT);
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
                    System.out.println(pt);
                    ResearcherType rt = switch (vals.get(2)) {
                        case ("EXPEDITIONIST") -> ResearcherType.EXPEDITIONIST;
                        case ("FOLK_RESEARCHER") -> ResearcherType.FOLK_RESEARCHER;
                        default -> null;
                    };
                    System.out.println(rt);
                    Human newhuman = new Human(vals.get(0), pt, rt, true);
                    hc.humanArrayList.add(newhuman);
                } catch (InvalidArgumentException e){
                    System.out.println(e.getMessage());
                }
        }
    }
    public void writeToFrom(String absolutePath, HumanCollection hc){

    }
}
