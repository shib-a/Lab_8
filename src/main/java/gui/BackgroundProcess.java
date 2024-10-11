package gui;

import client.ClientMain;

public class BackgroundProcess implements Runnable{
    @Override
    public void run() {
        try {
            ClientMain.main(null);
        }catch (Exception e){e.printStackTrace();}
    }
}
