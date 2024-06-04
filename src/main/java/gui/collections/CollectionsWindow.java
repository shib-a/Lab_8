package gui.collections;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class CollectionsWindow {

    private Stage stage;
//    private CollectionsWindowController controller;
    private int localeIndex;

    public CollectionsWindow() {
        this.localeIndex = localeIndex;
        System.out.println(getClass().getResource("/gui/collections/collectionsWindow.fxml"));
        try {
            stage = new Stage();
//            URL fxmlLocation = CollectionsWindow.class.getResource("collectionsWindow.fxml");
            URL fxmlLocation = getClass().getResource("/gui/collections/collectionsWindow.fxml");
//
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();

//            controller = loader.getController();
            setup();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setup() {
        stage.setResizable(false);
//        controller.setLocale(localeIndex);
//        controller.setStage(stage);
    }

    public void show() {
        stage.show();
    }
}
