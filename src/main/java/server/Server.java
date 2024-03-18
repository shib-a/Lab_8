package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
//    public static void main(String[] args){
//        try{
//            ServerSocket servSocket = new ServerSocket(6969);
//            var cs = servSocket.accept();
//            System.out.println("connected");
//            PrintWriter out = new PrintWriter(cs.getOutputStream(), true);
//            BufferedReader in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
//
//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                System.out.println("Client: " + inputLine);
//                out.println("Server received: " + inputLine);
//            }
//
//            out.close();
//            in.close();
//            cs.close();
//            servSocket.close();
//        } catch (IOException e){}
//    }
    int port = 6969;
    private ServerSocket serverSocket;
    public Server(int port){
        try{
            this.serverSocket= new ServerSocket(port);
        } catch (IOException e){
            System.out.println(">Invalid port. Set default to 6969");
        }
    }
    public ServerSocket getServerSocket(){return serverSocket;}
}
