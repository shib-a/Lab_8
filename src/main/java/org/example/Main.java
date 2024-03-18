package org.example;
import org.example.classes.*;
import org.example.collection.CollectionLoaderSaver;
import org.example.commands.*;
import org.example.commands.redo.RedoFile;
import org.example.interfaces.ReadMarkedField;

import java.io.*;
import java.lang.reflect.*;
import java.net.Socket;
import java.util.ArrayList;
import java.lang.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public Main() throws IOException {
    }

    static class MarkedExporter{           // статический класс
        public MarkedExporter(){
        }
        /**
         *Additional task from lab4. Counts the amount of specifically marked fields via reflect api
         * @param arr
         * @throws IOException
         * @throws IllegalAccessException
         * @author shibiest
         */
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

    public static void main(String[] args) throws IOException {

        //creating instances and setting stats
//        CommandLine cl = new CommandLine();
//        var cls = new CollectionLoaderSaver("ans.txt",cl);
//        var cm = new CollectionManager(cls);
//        cm.initialaze();
//        cl.printLn("\n");
//        cl.printLn("Welcome back. Enter 'help' to see information on available commands");
//        var com = new CommandManager();
//        var re = new RuntimeEnv(cl,com);
//        com.getCommandList().put("add", new Add(cl,cm));
//        com.getCommandList().put("clear", new Clear(cl,cm));
//        com.getCommandList().put("info", new Info(cl,cm));
//        com.getCommandList().put("show", new Show(cl,cm));
//        com.getCommandList().put("exit", new Exit(cl,cm));
//        com.getCommandList().put("sort", new Sort(cl,cm));
//        com.getCommandList().put("update", new Update(cl,cm));
//        com.getCommandList().put("remove_by_id", new Remove(cl,cm));
//        com.getCommandList().put("filter_by_is_alive", new FilterByIsAlive(cl,cm));
//        com.getCommandList().put("save", new Save(cl,cm));
//        com.getCommandList().put("filter_by_less_than_number_of_dug_counter", new FilterLessDC(cl,cm));
//        com.getCommandList().put("count_by_researcher_type", new CountByResearcherType(cl,cm));
//        com.getCommandList().put("remove_lower", new RemoveLower(cl,cm));
//        com.getCommandList().put("insert", new Insert(cl,cm));
//        com.getCommandList().put("execute_script", new ExecuteScript(cl,cm));
//        com.getCommandList().put("get_history", new GetHistory(cl,com));
//        com.getCommandList().put("help", new Help(cl,com));
//        com.getCommandList().put("redo",new Redo(cl,com,re));
//        com.getCommandList().put("redo_f",new RedoFile(cl,com,re));
////        re.mannedMode();
//        class Product{
//            String name;
//            Double price;
//            public double getPrice() {return price;}
//            public Product(String name, double price){
//                this.name=name;
//                this.price = price;
//            }
//
//            public String getName() {
//                return name;
//            }
//        }
//        List<Product> ls = new ArrayList<>();
//        ls.add(new Product("ass",300));
//        ls.add(new Product("pussy",500));
//        Optional<String> cn = ls.stream().map(Product::getName).reduce((a, b) -> a+b);
//        System.out.println(cn.toString());

    Socket socket = new Socket("localhost", 6969);
    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    String userInput;

        while ((userInput = stdIn.readLine()) != null) {
        out.println(userInput);
        System.out.println("Server: " + in.readLine());
    }

        out.close();
        in.close();
        stdIn.close();
        socket.close();
    }
}