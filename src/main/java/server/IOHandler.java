package server;

import common.CommandObject;
import common.Feedbacker;
import server.cls.commands.RuntimeEnv;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

public class IOHandler {
    RuntimeEnv re;
    public IOHandler(RuntimeEnv re){this.re=re;}
    private static Logger logger = Logger.getLogger("IOHandler");
    public void handle(SelectionKey key, Selector selector) throws IOException, ClassNotFoundException {
        logger.info("handleage");
        SocketChannel sc = (SocketChannel) key.channel();
            ByteBuffer buf = ByteBuffer.allocate(1024*1024);
            sc.read(buf);
            ByteArrayInputStream bis = new ByteArrayInputStream(buf.array());
            ObjectInputStream ois = new ObjectInputStream(bis);
            logger.info("Receiving data from client... ");
            CommandObject co = (CommandObject) ois.readObject();
            logger.info("Data received");
            System.out.println(co.getArgument());
//            return co;
            Feedbacker result = re.executeCommand(co);
            ois.close();
            bis.close();
            while(!key.isWritable()){}
            logger.info("Sending answer to client..." + result.getMessage());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(result);
            oos.flush();
            byte[] answer = bos.toByteArray();
            ByteBuffer outputBuf = ByteBuffer.wrap(answer);
            sc.write(outputBuf);
            bos.close();
            oos.close();
            sc.close();
            sc.register(selector, SelectionKey.OP_READ);
            logger.info("Answer sent successfully");
    }
//    public CommandObject recieve() throws IOException, ClassNotFoundException {

//        addToLog(co.getCommand()+" "+co.getArgument());

//    }
    public void handleRead(Selector selector, SelectionKey key){
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer buf = ByteBuffer.allocate(1024 * 1024);
        try {
            int bytesRead = sc.read(buf);
            if(bytesRead == -1){
                logger.info("Client side disconnected...");
                sc.close();
                key.cancel();
                return;
            }
            ByteArrayInputStream bis = new ByteArrayInputStream(buf.array());
            ObjectInputStream ois = new ObjectInputStream(bis);
            logger.info("Receiving data from client... ");
            CommandObject co = (CommandObject) ois.readObject();
            logger.info("Data received");
            System.out.println(co.getArgument());
            Feedbacker result = re.executeCommand(co);
            ois.close();
            bis.close();
            key.attach(result);
            key.interestOps(SelectionKey.OP_WRITE);
//            sc.register(selector,SelectionKey.OP_WRITE);
        }catch (IOException e){
            e.printStackTrace();
//                sc.close();
            key.cancel();
        } catch (ClassNotFoundException e){e.printStackTrace();}
    }
    public void handleWrite(Selector selector, SelectionKey key){
        try {
            logger.info("Sending answer to client...");
            SocketChannel sc = (SocketChannel) key.channel();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(key.attachment());
            oos.flush();
            byte[] answer = bos.toByteArray();
            ByteBuffer outputBuf = ByteBuffer.wrap(answer);
            sc.write(outputBuf);
            bos.close();
            oos.close();
            logger.info("Answer sent successfully");
            key.interestOps(SelectionKey.OP_READ);
            sc.register(selector, SelectionKey.OP_READ);
        }catch (IOException e){
            System.out.println(e);
        }
    }
    public void handleConnection(ServerSocketChannel ssc, Selector selector) throws IOException{
      SocketChannel sc = ssc.accept();
      sc.configureBlocking(false);
      sc.register(selector,SelectionKey.OP_READ);
      logger.info("New connection acquired");
    }
}
