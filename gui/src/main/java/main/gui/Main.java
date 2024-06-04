package main.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.gui.collections.CollectionsWindow;
import main.gui.commands.CommandsWindow;
import main.gui.login.LoginWindow;
import main.gui.register.RegisterWindow;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
//        CollectionsWindow collectionsWindow = new CollectionsWindow();
//        collectionsWindow.show();
//        CommandsWindow commandsWindow = new CommandsWindow();
//        commandsWindow.show();
//        LoginWindow loginWindow = new LoginWindow(stage);
//        loginWindow.show();
        RegisterWindow registerWindow = new RegisterWindow();
        registerWindow.show();
    }

    public static void main(String[] args) {
        launch();
    }
}