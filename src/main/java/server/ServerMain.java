package server;

import common.*;
import javafx.scene.paint.Color;
import server.cls.commands.*;
import server.managers.CollectionLoaderSaver;
import server.managers.CollectionManager;
import server.managers.CommandManager;
import server.managers.DataConnector;
import server.multithread.IOHandler;
import server.multithread.InteraciveServerConsole;
import server.multithread.ReadHandler;
import server.multithread.WriteHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;


public class ServerMain {
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getLogger(ServerMain.class.getName());
        Scanner scan = new Scanner(System.in);
        int port;
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().setReuseAddress(true);
        ssc.configureBlocking(false);
        Selector selector = Selector.open();
        while (true){
            try{
                System.out.println("enter the port: ");
                port = Integer.parseInt(scan.nextLine());
//                port = 6969;
                ssc.socket().bind(new InetSocketAddress("localhost", port));
                ssc.register(selector, SelectionKey.OP_ACCEPT);
                logger.info("Server started on port " + port);
                break;
            } catch (NumberFormatException e){
                System.out.println("Enter a proper integer value.");
            } catch (IOException e){
                System.out.println("Port unavailable. Try entering a different port.");
            }
        }
        DataConnector.initialize_db();

        while(true){
            try{

        CommandLine cl = new CommandLine();
        var cls = new CollectionLoaderSaver("ans.txt",cl);
        var cm = new CollectionManager(cls);
        cm.initialaze();
        var com = new CommandManager();
        var re = new RuntimeEnv(cl,com);
        com.getCommandList().put("add", new Add(cl,cm,re));
        com.getCommandList().put("clear", new Clear(cl,cm));
        com.getCommandList().put("info", new Info(cl,cm));
        com.getCommandList().put("show", new Show(cl,cm));
        com.getCommandList().put("exit", new Exit(cl,cm));
        com.getCommandList().put("sort", new Sort(cl,cm));
        com.getCommandList().put("update (id)", new Update(cl,cm,re));
        com.getCommandList().put("remove_by_id (id)", new Remove(cl,cm));
        com.getCommandList().put("filter_by_is_alive (true/false)", new FilterByIsAlive(cl,cm));
        com.getCommandList().put("save", new Save(cl,cm)); //remove and bind to exit
        com.getCommandList().put("filter_by_less_than_number_of_dug_counter (integer_value)", new FilterLessDC(cl,cm));
        com.getCommandList().put("count_by_rarity {THREE_STAR/FOUR_STAR/FIVE_STAR}", new CountByRarity(cl,cm));
        com.getCommandList().put("remove_lower (double_value)", new RemoveLower(cl,cm));
//        com.getCommandList().put("insert (positive_integer_value)", new Insert(cl,cm,re));
        com.getCommandList().put("execute_script (file_name)", new ExecuteScript(cl,cm));
        com.getCommandList().put("help", new Help(com));
        com.getCommandList().put("login {user} {password}", new Login());
        com.getCommandList().put("register {user} {password}", new Register());

        logger.info("Ready for IO");
        Human h0 = new Human("Chixia", Status.WARRIOR, Color.ORANGERED, true,275,200,100,250,1, Rarity.THREE_STAR);
        Human h1 = new Human("Ningguang", Status.RULER, Color.LIGHTGOLDENRODYELLOW, true,250,270,80,190,100, Rarity.THREE_STAR);
        Human h2 = new Human("Yaoyao", Status.FARMER, Color.YELLOWGREEN, true,210,160,200,210,100, Rarity.THREE_STAR);
        Human h3 = new Human("Verina", Status.FARMER, Color.LIMEGREEN, true,230,160,220,200,100, Rarity.THREE_STAR);
        Human h4 = new Human("Rosaria", Status.SIMPLETON, Color.DEEPPINK, true,250,160,400,190,100, Rarity.THREE_STAR);
        Human h5 = new Human("Arlecchino", Status.WARRIOR, Color.DIMGREY, true,300,260,40,320,100, Rarity.THREE_STAR);
        Human h6 = new Human("Xiangling", Status.SIMPLETON, Color.DARKORANGE, true,280,190, 50,330,100, Rarity.THREE_STAR);
        Human h7 = new Human("Furina", Status.RULER, Color.ROYALBLUE, true,290,190,40,300,99, Rarity.THREE_STAR);
        Human h8 = new Human("Yangyang", Status.WARRIOR, Color.DARKBLUE, true,300,220,80,230,100, Rarity.THREE_STAR);
        Human h9 = new Human("Tartaglia", Status.WARRIOR, Color.LIGHTSLATEGREY, true,280,240,30,290,-1, Rarity.THREE_STAR);

        Human h10 = new Human("Yae Miko", Status.RULER, Color.HOTPINK, true,340,280,90,180,100, Rarity.FOUR_STAR);
        Human h11 = new Human("Zhongli", Status.SIMPLETON, Color.BROWN, true,510,280,10,310,100, Rarity.FOUR_STAR);
        Human h12 = new Human("Jiyan", Status.WARRIOR, Color.DARKSEAGREEN, true,410,280,30,290,100, Rarity.FOUR_STAR);
        Human h13 = new Human("Jinshi", Status.RULER, Color.LIGHTCYAN, true,290,290,50,280,100, Rarity.FOUR_STAR);
        Human h14 = new Human("Keqing", Status.WARRIOR, Color.MEDIUMPURPLE, true,280,210,80,340,100, Rarity.FOUR_STAR);

        Human h15 = new Human("Yinlin", Status.WARRIOR, Color.PURPLE, true,300,300,80,200,100, Rarity.FIVE_STAR);
        Human h16 = new Human("Neuvilette", Status.RULER, Color.DEEPSKYBLUE, true,400,300,30,330,100, Rarity.FIVE_STAR);
        Human h17 = new Human("Bennett", Status.SIMPLETON, Color.LIGHTGRAY, true,360,190,-300,195,50, Rarity.FIVE_STAR);

        ArrayList<Human> lp = new ArrayList<>();
        lp.add(h0);
        lp.add(h1);
        lp.add(h2);
        lp.add(h3);
        lp.add(h4);
        lp.add(h5);
        lp.add(h6);
        lp.add(h7);
        lp.add(h8);
        lp.add(h9);
        lp.add(h10);
        lp.add(h11);
        lp.add(h12);
        lp.add(h13);
        lp.add(h14);
        lp.add(h15);
        lp.add(h16);
        lp.add(h17);
        StandardBanner st = new StandardBanner("standard banner", lp, RollReq.STANDARD);
        re.setBanner(st);
        InteraciveServerConsole intServCons = new InteraciveServerConsole(cl,com);
        executorService.execute(intServCons);



        IOHandler handler = new IOHandler(re);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        while (true){
            int readyChannels = selector.select(1000);
            if (readyChannels==0){continue;}
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if(!key.isValid()){continue;}
                try {
                    if (key.isValid() && key.isAcceptable()){
                        handler.handleConnection(ssc, selector);

                    }
                    if (key.isValid() && key.isReadable()){
                        ReadHandler rh = new ReadHandler(re, key);
                        rh.handle();
                        logger.info("reading handled");
                    }
                    if (key.isValid() && key.isWritable()){
                        logger.info("about to handle writing");
                        WriteHandler wh = new WriteHandler(key);
                        wh.handle();
                    }
                } catch (IOException e) {
                    System.out.println(Arrays.toString(e.getStackTrace()) + e.getMessage());
                }
            }

        }
    } catch (IOException e){
                logger.severe("Client-side connection closed..." + e.getMessage() +  Arrays.toString(e.getStackTrace()));
            } catch (RuntimeException e){logger.info("Client-side connection closed..." + e.getMessage() + Arrays.toString(e.getStackTrace()));
            }
        }
    }
}
