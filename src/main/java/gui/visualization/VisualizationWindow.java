package gui.visualization;

import common.Human;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class VisualizationWindow {
    private Stage stage;
    private VisualizationWindowController controller;

    public VisualizationWindow() {
        try {
            stage = new Stage();
            URL fxmlLocation = VisualizationWindow.class.getResource("visualizationWindow.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
//            loader.setController(new VisualizationWindowController());
            Parent root = loader.load();

            controller = loader.getController();

            this.stage.setScene(new Scene(root, 1020, 1012));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void loadCollection(TreeSet<City> cities) {
//        controller.setCities(cities);
//    }
//
//    public void loadColorMap(Map<String, Color> colorMap, Map<Long, String> ownershipMap) {
//        controller.loadColorMap(colorMap, ownershipMap);
//    }

    public void show() {
        stage.show();
    }
}