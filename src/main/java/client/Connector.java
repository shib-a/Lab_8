package client;

import client.commands.CommandManager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class Connector {
    static int port;
    static Selector selector;
    static SocketAddress adr;

    public Connector() throws IOException {
    }

    public static SocketChannel getSC(){
        try{
            //            sc.configureBlocking(false);
//            selector = Selector.open();
//            SelectionKey key = sc.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            return SocketChannel.open();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static Selector getSelector(){
        return selector;
    }

    public static void setSelector(Selector selector) {
        Connector.selector = selector;
    }
}
