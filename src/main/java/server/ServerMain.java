package server;

import server.cls.commands.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;


public class ServerMain {
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getLogger(ServerMain.class.getName());
//        logger.info("Starting server");
        Scanner scan = new Scanner(System.in);
        int port;
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().setReuseAddress(true);
        ssc.configureBlocking(false);
        while (true){
            try{
                System.out.println("enter the port: ");
                port = Integer.parseInt(scan.nextLine());
                ssc.socket().bind(new InetSocketAddress("localhost", port));
                logger.info("Server started on port "+port);
                break;
            } catch (NumberFormatException e){
                System.out.println("Enter a proper integer value.");
            } catch (IOException e){
                System.out.println("Port unavailable. Try entering a different port.");
            }
        }
        DataConnector.initialize_db();
//        Selector selector = Selector.open();
//        ServerSocket ss = new ServerSocket(port);

        while(true){
            try{
//            while (true){
////                ssc = ServerSocketChannel.open();
//                SocketChannel sc = ssc.accept();
//                if (sc != null) {
//                    sc.configureBlocking(false);
//                    ServerConnector.setSelector(Selector.open());
//                    SelectionKey sk = sc.register(ServerConnector.getSelector(), SelectionKey.OP_READ | SelectionKey.OP_WRITE);
//                }
//                sc.register(selector, SelectionKey.OP_ACCEPT);
//                selector.select();
//        Socket s = ss.accept();

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
        com.getCommandList().put("count_by_researcher_type (EXPEDITIONIST/FOLK_RESEARCHER)", new CountByResearcherType(cl,cm));
        com.getCommandList().put("remove_lower (double_value)", new RemoveLower(cl,cm));
        com.getCommandList().put("insert (positive_integer_value)", new Insert(cl,cm,re));
        com.getCommandList().put("execute_script (file_name)", new ExecuteScript(cl,cm));
        com.getCommandList().put("help", new Help(com));
        logger.info("Ready for IO");

        InteraciveServerConsole intServCons = new InteraciveServerConsole(cl,com);
        executorService.execute(intServCons);

        while (true){
            SocketChannel clientSocket = ssc.accept();

            if (clientSocket != null) {
//                logger.info("New connection acquired" + ServerConnector.selector.keys().toString());
                logger.info("New connection acquired");
                clientSocket.configureBlocking(false);
                ServerConnector.setSelector(Selector.open());
                SelectionKey sk = clientSocket.register(ServerConnector.getSelector(), SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                ThreadClientHandler tch = new ThreadClientHandler(clientSocket,re);
                try{
                    executorService.execute(tch);
                } catch (RuntimeException e){logger.info("Clent disconnected");}
            }

        }
    } catch (IOException e){
//                System.err.println(">Client-side connection closed...");
                logger.severe("Client-side connection closed..." + e.getMessage() +  Arrays.toString(e.getStackTrace()));
//                ServerConnector.getSelector().keys().clear();
            } catch (RuntimeException e){logger.info("Client-side connection closed..." + e.getMessage() + Arrays.toString(e.getStackTrace()));
            }
        }
    }
}
