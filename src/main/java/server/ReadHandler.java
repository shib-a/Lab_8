package server;

import common.CommandObject;
import common.Feedbacker;
import server.cls.commands.RuntimeEnv;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class ReadHandler {
    static RuntimeEnv re;
    SelectionKey key;
    static ExecutorService ex = Executors.newCachedThreadPool();
    private static Logger logger = Logger.getLogger("ReadHandler");
    public ReadHandler(RuntimeEnv re, SelectionKey key){this.re =re; this.key=key;};
    public void handle(){
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer buf = ByteBuffer.allocate(1024 * 1024);
        try {
                int bytesRead = sc.read(buf);
                if (bytesRead == -1) {
                    logger.info("Client side disconnected...");
                    sc.close();
                    key.cancel();
                    return;
                }
//            sc.read(buf);
        ByteArrayInputStream bis = new ByteArrayInputStream(buf.array());
                synchronized (bis) {
                    ObjectInputStream ois = new ObjectInputStream(bis);
                    concRead(sc, ois, bis, key, buf);
                }
//        try {
//            int bytesRead = sc.read(buf);
//            if(bytesRead == -1){
//                logger.info("Client side disconnected...");
//                sc.close();
//                key.cancel();
//                return;
//            }
//                ByteArrayInputStream bis = new ByteArrayInputStream(buf.array());
//                ObjectInputStream ois = new ObjectInputStream(bis);
//                logger.info("Receiving data from client... ");
//                ois.defaultReadObject();
//                CommandObject co = (CommandObject) ois.readObject();
//                logger.info("Data received");
//                System.out.println(co.getArgument());
//                Feedbacker result = re.executeCommand(co);
//                ois.close();
//                bis.close();
//            key.attach(result);

//            sc.register(selector,SelectionKey.OP_WRITE);
            } catch (IOException e) {
                try {
                    e.printStackTrace();
                    System.out.println(e.getMessage() + e.getCause());
                    sc.close();
                    key.cancel();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    public void concRead(SocketChannel sc, ObjectInputStream ois, ByteArrayInputStream bis, SelectionKey key, ByteBuffer buf){
        ex.execute(() -> {try {
            logger.info("Receiving data from client... ");
//            ois.defaultReadObject();
            CommandObject co = (CommandObject) ois.readObject();
            logger.info("Data received");
            System.out.println(co.getArgument());
            Feedbacker result = re.executeCommand(co);
            ois.close();
            bis.close();
            key.attach(result);
            key.interestOps(SelectionKey.OP_WRITE);
    }catch (IOException | ClassNotFoundException e){e.printStackTrace();}}
        );
    }
}
