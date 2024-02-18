package org.example;
import org.example.classes.*;
import org.example.collection.CollectionLoaderSaver;
import org.example.collection.HumanCollection;
import org.example.commands.RuntimeEnv;
import org.example.exceptions.InvalidArgumentException;

import java.io.*;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.lang.annotation.*;
public class Main {
    static class MarkedExporter{           // статический класс
        public MarkedExporter(){
        }
        public void getIsMarked(Object[] arr) throws IOException, IllegalAccessException {        // метод-сериализатор
            try {
                FileWriter fw = new FileWriter("answer.txt",true);      // возможность дописывания в файл
                PrintWriter writer = new PrintWriter(fw);
                for (Object o : arr) {
                    Field[] fs = o.getClass().getDeclaredFields();      // получаем все поля класса объекта
                    for (Field f : fs) {
                        f.setAccessible(true);          // делаем приватные поля доступными
                    }
                    ArrayList<Field> ansArr = new ArrayList<>();        // сюда будут записываться поля помеченные аннотацией
                    Field[] fields = o.getClass().getDeclaredFields();
                    for (Field f :fields) {
                        Annotation[] annotations = f.getAnnotations();
                        for (Annotation a : annotations) {
                            if (a.equals(f.getAnnotation(ReadMarkedField.class))) {     // проверка на наличие аннота
                                ansArr.add(f);
                            }
                        }
                    }
                    if (ansArr.size()!=0){          // теперь начинаем блок звписи в файл
                        for(Field f: ansArr) {
                            f.setAccessible(true);
                            if (f.getType().isArray()){                 // рассматриваем случай если поле это список
                                Object[] object=(Object[])f.get(o);
                                int cnt = 0;
                                for (Object item : object) {
                                    if (item != null) {
                                        if(f!= ansArr.get(ansArr.size()-1)) {
                                            writer.write('"'+item.toString()+'"'+", ");           // записываем значения в соответствии с синтаксисом csv
                                        } else {writer.write('"'+item.toString()+'"' +"\n");}
                                    } else {cnt+=1;}
                                }
                                if (cnt== object.length){
                                    if(f!= ansArr.get(ansArr.size()-1)) {
                                        writer.write('"' + object.toString().substring(2) + '"'+ ", ");     // тоже запись
                                    } else {
                                        writer.write('"' + object.toString().substring(2) + '"' + "\n");
                                    }
                                }
                            } else {
                                if(f!= ansArr.get(ansArr.size()-1)) {
                                    writer.write('"' + f.get(o).toString() + '"' + ", ");       // опять запись, но теперь не полей-списков
                                } else {
                                    writer.write('"' + f.get(o).toString() + '"' + "\n");
                                }
                            }
                        }
                    }
                }
                writer.close();  // В-С-Ё    UwU :3333
                fw.close();
            } catch (FileNotFoundException | UnsupportedEncodingException e){       // та самая реализация checked exceptions кототрые по хорошему нужно было сделать в оригинальной лабе
                System.out.println("no such file!!!");
            }
        }
    }

    public static void main(String[] args) throws IOException, IllegalAccessException, InvalidArgumentException {

        //creating instances and setting stats

        Human h1 = new Human("Pebody", ToolKinds.GUN, ResearcherType.EXPEDITIONIST,true);
        Human h2 = new Human("Wilmart", ToolKinds.SHOVEL, ResearcherType.FOLK_RESEARCHER,true);
        h1.setStat(100, 60, 50, 15, 90);
        h2.setStat(100, 60, 50, 15, 90);
        Tool shovel = new Tool("Shovel",ToolKinds.SHOVEL);
        Tool jackhammer = new Tool("Jackhammer", ToolKinds.JACKHAMMER);
        Tool drill = new Tool("Drill",ToolKinds.DRILL);
//        shovel.addToInventory(h1);
//        drill.addToInventory(h1);
//        jackhammer.addToInventory(h1);
        GeologicalLayer gl = new GeologicalLayer("layer", StoneDurability.SOFT, PeriodAge.JURASSIC);
//        NecronExtract ext1 = new NecronExtract("extract 1", 15, 15);
//        NecronExtract ext2 = new NecronExtract("e2", 15, 15);
//        MarkedExporter me = new MarkedExporter();
//        shovel.addToInventory(h1);
//        jackhammer.addToInventory(h2);
//        drill.addToInventory(h2);
//        h1.dig(gl);
        CollectionLoaderSaver fls = new CollectionLoaderSaver();
        fls.readFrom("E:\\IdeaProjects\\lab_3\\test.txt");
//        me.getIsMarked(new GeologicalLayer[]{gl});      // собственно используем наш метод
//        me.getIsMarked(new Human[]{h1,h2});
        RuntimeEnv re = new RuntimeEnv();
        re.startRuntime();







        // oh shit
//         program
//
//        while (true) {
//            System.out.println();
//            Scanner scan = new Scanner(System.in);
//            System.out.println("wjat to do? (dig/search/attack/read)");
//            String inq = scan.next();
//            if (inq.equals("fight")) {
//                System.out.println("who?");
//                String who = scan.next();
//                if (who.equals("h2")) {
//                    h1.attack(h2);
//                } else {
//                    h2.attack(h1);
//                }
//            } else if (inq.equals("dig")) {
//                h1.dig(gl);
//                for (int i = 0; i < 4; i++) {
//                    if (h1.inventory[i] != null) {
//                        System.out.println(h1.inventory[i].itemName);
//                    }
//                }
//            } else if (inq.equals("search")) {
//                gl = h1.searchLayer();
//                System.out.println("layer found\t" + gl.getAgePeriod());
//            } else if (inq.equals("read")) {
//                System.out.println("what?");
//                scan.nextLine();
//                String what = scan.nextLine();
//                if (what.equals(ext1.itemName)) {
//                    h1.readext(ext1);
//                } else if (what.equals(ext2.itemName)) {
//                    h1.readext(ext2);
//                }
////                System.out.println("\tfinished");
//            }
//        }
    }
}