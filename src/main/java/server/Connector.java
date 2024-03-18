package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connector {
    Server s;
    public Connector(Server s){
        this.s=s;
    }
    public Socket getClientSocket() throws IOException {
        return s.getServerSocket().accept();
    }
}
