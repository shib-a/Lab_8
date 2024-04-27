package server;

import server.cls.commands.*;
import common.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.logging.Logger;


public class ServerMain {
    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getLogger(ServerMain.class.getName());
        logger.info("Starting server");
        int port = 3829;
        Selector selector = Selector.open();
//        ServerSocket ss = new ServerSocket(port);
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().setReuseAddress(true);
        ssc.socket().bind(new InetSocketAddress("localhost", port));
        ssc.configureBlocking(false);
        while(true){
            try{
//                ssc = ServerSocketChannel.open();
                SocketChannel sc = ssc.accept();
                if (sc != null){
//                sc.register(selector, SelectionKey.OP_ACCEPT);
//                selector.select();
//        Socket s = ss.accept();
        logger.info("New connection acquired");
        CommandLine cl = new CommandLine(sc);
        var cls = new CollectionLoaderSaver("ans.txt",cl);
        var cm = new CollectionManager(cls);
        cm.initialaze();
//        System.out.println(cm.getCollection().get(2));
        var com = new CommandManager();
//        var re = new RuntimeEnv(cl,com,s);
                var re = new RuntimeEnv(cl,com,sc);
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
//        com.getCommandList().put("get_history", new GetHistory(cl,com));
        com.getCommandList().put("help", new Help(com));
        logger.info("Ready for IO");
        re.mannedMode();
        ssc.close();}
    }catch (IOException e){
//                System.err.println(">Client-side connection closed...");
                logger.severe("Client-side connection closed..." + e.getMessage() +  Arrays.toString(e.getStackTrace()));
            }
        }
    }
}
