package server.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerExecutor {
    public static ExecutorService es = Executors.newCachedThreadPool();
    void m(){
    }
}
