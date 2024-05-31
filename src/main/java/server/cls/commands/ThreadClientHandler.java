package server.cls.commands;

import server.CustomException;

import java.nio.channels.SocketChannel;

public class ThreadClientHandler implements Runnable{
    SocketChannel socketChannel;
    RuntimeEnv runtimeEnv;
    public ThreadClientHandler(SocketChannel sc,RuntimeEnv re){
        this.socketChannel = sc;
        this.runtimeEnv = re;
        re.setSc(sc);
    }
    @Override
    public void run(){
        try {
            runtimeEnv.mannedMode();
        } catch (RuntimeException | CustomException e) {
            System.out.println("client d/c");
        }
    }
}
