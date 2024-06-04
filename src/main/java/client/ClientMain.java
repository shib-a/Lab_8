package client;
import client.classes.CollectionManager;
import client.commands.*;
import client.collection.CollectionLoaderSaver;
import common.Feedbacker;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import static java.lang.Thread.sleep;

public class ClientMain {
    public static RuntimeEnv re = null;
        public static void main(String[] args) throws IOException, InterruptedException {
            CommandManager com = new CommandManager();
            System.out.println(">Awaiting connection...");
            SocketChannel socketChannel;
            Scanner scan = new Scanner(System.in);
            Selector selector = Selector.open();
            while (true){
            try {
                while (true){
                    try{
                        System.out.println("enter the server port: ");
                        var str = scan.nextLine();
                        if (str.equals("exit")){System.exit(0);}
                        Connector.port = Integer.parseInt(str);
                        Connector.adr =  new InetSocketAddress("localhost",Connector.port);
                        socketChannel = Connector.getSC();
                        socketChannel.connect(Connector.adr);
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                        selector.select();
                        break;
                    } catch (NumberFormatException e){
                        System.out.println("Enter a proper integer value.");
                    } catch (IOException e){
                        System.out.println("Port unavailable. Try entering a different port.");
                    }
                }
                    CommandLine cl = new CommandLine(socketChannel);
                    var cls = new CollectionLoaderSaver("ans.txt", cl);
                    var cm = new CollectionManager(cls);
                    cm.initialaze();
                    cl.printLn("\n");
                    cl.printLn("Welcome back. Enter 'help' to see information on available commands");
                    re = new RuntimeEnv(cl, com, socketChannel);
                    com.getCommandList().put("add", new Add(cl));
                    com.getCommandList().put("clear", new Clear());
                    com.getCommandList().put("info", new Info());
                    com.getCommandList().put("show", new Show());
                    com.getCommandList().put("exit", new Exit());
                    com.getCommandList().put("update", new Update());
                    com.getCommandList().put("remove_by_id", new Remove());
                    com.getCommandList().put("filter_by_is_alive", new FilterByIsAlive());
                    com.getCommandList().put("filter_by_status", new FilterLessDC());
                    com.getCommandList().put("count_by_rarity", new CountByResearcherType());
                    com.getCommandList().put("remove_lower", new RemoveLower());
                    com.getCommandList().put("help", new Help());
                    com.getCommandList().put("login", new Login());
                    com.getCommandList().put("register", new Register());

                re.mannedMode(selector);

            }    catch (NullPointerException e){
                System.out.println("Unexpected error :"+ Arrays.toString(e.getStackTrace()) + e.getMessage() + e.getCause());
            }    // fix error after closing server and inputting a command on client
        }
        }

    public static RuntimeEnv getRe() {
        return re;
    }
}