package gui;

import client.ClientMain;
import gui.collections.CollectionsWindow;
import gui.login.LoginWindow;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.stage.Stage;

import java.lang.module.InvalidModuleDescriptorException;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        startBackgroundProcess();
        LoginWindow loginWindow = new LoginWindow(stage);
        loginWindow.show();
    }

    public static void main(String[] args) {
        try {
            launch();
        } catch (InvalidModuleDescriptorException e){e.printStackTrace();}
    }

    public void startBackgroundProcess(){
        Task<Void> backgroundTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                ClientMain.main(null);
                return null;
            }
        };
        backgroundTask.messageProperty().addListener((obs, oldMessage, newMessage)->{});
        new Thread(backgroundTask).start();
    }
}