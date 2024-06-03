package server.multithread;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class WriteHandler {
    Selector selector;

    SelectionKey key;
    static ExecutorService ex = Executors.newSingleThreadExecutor();
    private Logger logger = Logger.getLogger("WriteHandler");
    public WriteHandler(SelectionKey key){this.key=key;}
    public void handle(){
        try {
            logger.info("Sending answer to client...");
            SocketChannel sc = (SocketChannel) key.channel();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            synchronized (bos) {
                ObjectOutputStream oos = new ObjectOutputStream(bos);
//            oos.writeObject(key.attachment());
//            oos.flush();
//            byte[] answer = bos.toByteArray();
//            ByteBuffer outputBuf = ByteBuffer.wrap(answer);
//            sc.write(outputBuf);
//            bos.close();
//            oos.close();
                cocncWrite(oos, key, bos, sc);
            }
            logger.info("Answer sent successfully");

        }catch (IOException  e){e.printStackTrace();}
    }

    public static void cocncWrite(ObjectOutputStream oos, SelectionKey key, ByteArrayOutputStream bos, SocketChannel sc){
        ex.execute(() -> {
                try {
                    oos.writeObject(key.attachment());
                    oos.flush();
                    byte[] answer = bos.toByteArray();
                    ByteBuffer outputBuf = ByteBuffer.wrap(answer);
                    sc.write(outputBuf);
                    key.interestOps(SelectionKey.OP_READ);
        } catch (IOException e){e.printStackTrace();}
        });
    }
//    @Override
//    public void run() {
//        try {
//
//            synchronized (sc) {
//                ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                ObjectOutputStream oos = new ObjectOutputStream(bos);
//                oos.writeObject(key.attachment());
//                oos.flush();
//                byte[] answer = bos.toByteArray();
//                ByteBuffer outputBuf = ByteBuffer.wrap(answer);
//                sc.write(outputBuf);
//                bos.close();
//                oos.close();
//                logger.info("Answer sent successfully");
//                key.interestOps(SelectionKey.OP_READ);
////                sc.register(selector, SelectionKey.OP_READ);
//            }
//        } catch (
//                IOException e) {
//            e.printStackTrace();
//        }
//    }
}
