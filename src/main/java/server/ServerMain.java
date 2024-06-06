package server;

import common.*;
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
        com.getCommandList().put("count_by_researcher_type (EXPEDITIONIST/FOLK_RESEARCHER)", new CountByRarity(cl,cm));
        com.getCommandList().put("remove_lower (double_value)", new RemoveLower(cl,cm));
//        com.getCommandList().put("insert (positive_integer_value)", new Insert(cl,cm,re));
        com.getCommandList().put("execute_script (file_name)", new ExecuteScript(cl,cm));
        com.getCommandList().put("help", new Help(com));
        com.getCommandList().put("login {user} {password}", new Login());
        com.getCommandList().put("register {user} {password}", new Register());

        logger.info("Ready for IO");
        Human h = new Human("cock", Status.SCIENTIST, Color.EXPEDITIONIST, true,1,1,1,1,1, Rarity.THREE_STAR);
        Human h1 = new Human("balls", Status.SCIENTIST, Color.EXPEDITIONIST, true,1,1,1,1,0, Rarity.FOUR_STAR);
        Human h2 = new Human("cum", Status.SCIENTIST, Color.EXPEDITIONIST, true,1,1,1,1,1, Rarity.FIVE_STAR);

        ArrayList<Human> lp = new ArrayList<>();
        lp.add(h);
        lp.add(h1);
        lp.add(h2);
        StandardBanner st = new StandardBanner("cock banner", lp, RollReq.STANDARD);
        re.setBanner(st);
//                System.out.println(re.getBannerList().toString());
//                System.out.println(re.getBannerList().get("one").toString());
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
