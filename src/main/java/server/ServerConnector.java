package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class ServerConnector {
    static int port = 3829;
    static Selector selector;
    static SocketAddress adr = new InetSocketAddress("localhost",port);

    public ServerConnector() throws IOException {
    }

    public static SocketChannel getSC(){
        try{
            SocketChannel sc = SocketChannel.open();
//            sc.connect(adr);
//            sc.configureBlocking(false);
            selector = Selector.open();
//            SelectionKey key = sc.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            return sc;
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static Selector getSelector(){
        return selector;
    }

    public static SocketAddress getAdr() {
        return adr;
    }

    public static void setSelector(Selector selector) {
        ServerConnector.selector = selector;
    }
}
