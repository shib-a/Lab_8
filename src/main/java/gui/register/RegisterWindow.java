package gui.register;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class RegisterWindow {
    private int localeIndex;
    private Stage stage;
    private RegisterWindowController controller;

    public RegisterWindow(int localeIndex) {
        this.localeIndex = localeIndex;
        try {
            stage = new Stage();
            URL fxmlLocation = RegisterWindow.class.getResource("registerWindow.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();

            controller = loader.getController();
            setup();


            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void show() {
        stage.show();
    }

    private void setup() {
        stage.setResizable(false);
        controller.setLocale(localeIndex);
//        controller.setStage(stage);
    }
}

