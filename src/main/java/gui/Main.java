package gui;

import javafx.application.Application;
import javafx.stage.Stage;
import gui.collections.CollectionsWindow;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        CollectionsWindow collectionsWindow = new CollectionsWindow();
        collectionsWindow.show();
//        LoginWindow loginWindow = new LoginWindow(stage);
//        loginWindow.show();
    }

    public static void main(String[] args) {
        launch();
    }
}