package client;
import client.classes.CollectionManager;
import client.commands.*;
import client.collection.CollectionLoaderSaver;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class ClientMain {
//    public Main() throws IOException {
//    }

//    static class MarkedExporter {           // статический класс
////        public MarkedExporter() {
////        }
////
////        /**
////         * Additional task from lab4. Counts the amount of specifically marked fields via reflect api
////         *
////         * @param
////         * @throws IOException
////         * @throws IllegalAccessException
////         * @author shibiest
////         */
//        public void getIsMarked(Object[] arr) throws IOException, IllegalAccessException {        // метод-сериализатор
//            try {
//                FileWriter fw = new FileWriter("answer.txt",true);      // возможность дописывания в файл
//                PrintWriter writer = new PrintWriter(fw);
//                for (Object o : arr) {
//                    Field[] fs = o.getClass().getDeclaredFields();      // получаем все поля класса объекта
//                    for (Field f : fs) {
//                        f.setAccessible(true);          // делаем приватные поля доступными
//                    }
//                    ArrayList<Field> ansArr = new ArrayList<>();        // сюда будут записываться поля помеченные аннотацией
//                    Field[] fields = o.getClass().getDeclaredFields();
//                    for (Field f :fields) {
//                        Annotation[] annotations = f.getAnnotations();
//                        for (Annotation a : annotations) {
//                            if (a.equals(f.getAnnotation(ReadMarkedField.class))) {     // проверка на наличие аннота
//                                ansArr.add(f);
//                            }
//                        }
//                    }
//                    if (ansArr.size()!=0){          // теперь начинаем блок звписи в файл
//                        for(Field f: ansArr) {
//                            f.setAccessible(true);
//                            if (f.getType().isArray()){                 // рассматриваем случай если поле это список
//                                Object[] object=(Object[])f.get(o);
//                                int cnt = 0;
//                                for (Object item : object) {
//                                    if (item != null) {
//                                        if(f!= ansArr.get(ansArr.size()-1)) {
//                                            writer.write('"'+item.toString()+'"'+", ");           // записываем значения в соответствии с синтаксисом csv
//                                        } else {writer.write('"'+item.toString()+'"' +"\n");}
//                                    } else {cnt+=1;}
//                                }
//                                if (cnt== object.length){
//                                    if(f!= ansArr.get(ansArr.size()-1)) {
//                                        writer.write('"' + object.toString().substring(2) + '"'+ ", ");     // тоже запись
//                                    } else {
//                                        writer.write('"' + object.toString().substring(2) + '"' + "\n");
//                                    }
//                                }
//                            } else {
//                                if(f!= ansArr.get(ansArr.size()-1)) {
//                                    writer.write('"' + f.get(o).toString() + '"' + ", ");       // опять запись, но теперь не полей-списков
//                                } else {
//                                    writer.write('"' + f.get(o).toString() + '"' + "\n");
//                                }
//                            }
//                        }
//                    }
//                }
//                writer.close();  // В-С-Ё    UwU :3333
//                fw.close();
//            } catch (FileNotFoundException | UnsupportedEncodingException e){       // та самая реализация checked exceptions кототрые по хорошему нужно было сделать в оригинальной лабе
//                System.out.println("no such file!!!");
//            }
//        }
//    }
        public static void main(String[] args) throws IOException, InterruptedException {
//        Logger logger = Logger.getLogger(Main.class.getName());
            //creating instances and setting stats
//            var com = new CommandManager();
//            int port = 3829;
//            Selector selector = Selector.open();
//            SocketAddress adr = new InetSocketAddress("localhost",port);
            CommandManager com = new CommandManager();
            System.out.println(">Awaiting connection...");
            SocketChannel socketChannel;
            Scanner scan = new Scanner(System.in);
//            Connector connector = new Connector();
            while (true){
            try {
                while (true){
                    try{
                        System.out.println("enter the server port: ");
                        var str = scan.nextLine();
                        if (str.equals("exit")){return;}
                        Connector.port = Integer.parseInt(str);
                        Connector.adr =  new InetSocketAddress("localhost",Connector.port);
                        socketChannel = Connector.getSC();
                        socketChannel.connect(Connector.adr);
                        socketChannel.configureBlocking(false);
                        break;
                    } catch (NumberFormatException e){
                        System.out.println("Enter a proper integer value.");
                    } catch (IOException e){
                        System.out.println("Port unavailable. Try entering a different port.");
                    }
                }
//                    Connector.setSelector(Selector.open());
//                    SelectionKey key = socketChannel.register(Connector.getSelector(), SelectionKey.OP_READ | SelectionKey.OP_WRITE);
//                Socket s = new Socket("localhost", port);
                    CommandLine cl = new CommandLine(socketChannel);
                    var cls = new CollectionLoaderSaver("ans.txt", cl);
                    var cm = new CollectionManager(cls);
                    cm.initialaze();
                    cl.printLn("\n");
                    cl.printLn("Welcome back. Enter 'help' to see information on available commands");
                    var re = new RuntimeEnv(cl, com, socketChannel);
                    com.getCommandList().put("add", new Add(cl));
                    com.getCommandList().put("clear", new Clear());
                    com.getCommandList().put("info", new Info());
                    com.getCommandList().put("show", new Show());
                    com.getCommandList().put("exit", new Exit());
                    com.getCommandList().put("sort", new Sort());
                    com.getCommandList().put("update", new Update());
                    com.getCommandList().put("remove_by_id", new Remove());
                    com.getCommandList().put("filter_by_is_alive", new FilterByIsAlive());
//            com.getCommandList().put("save", new Save());
                    com.getCommandList().put("filter_by_less_than_number_of_dug_counter", new FilterLessDC());
                    com.getCommandList().put("count_by_researcher_type", new CountByResearcherType());
                    com.getCommandList().put("remove_lower", new RemoveLower());
                    com.getCommandList().put("insert", new Insert());
                    com.getCommandList().put("execute_script", new ExecuteScript());
                    com.getCommandList().put("get_history", new GetHistory());
                    com.getCommandList().put("help", new Help());

                    re.mannedMode();
                    break;

            }    catch (NullPointerException e){
                System.out.println("Unexpected error :"+ Arrays.toString(e.getStackTrace()));
            }    // fix error after closing server and inputting a command on client
        }
        }
}