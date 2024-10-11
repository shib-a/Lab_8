package gui.info;
import common.Human;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

public class InfoWindow {
        private Stage stage;
        private Scene scene;
        private Logger logger = Logger.getLogger("iw");
        private InfoWindowController controller;

        public InfoWindow() {
            try {
                stage = new Stage();
                stage.setResizable(false);
                URL fxmlLocation = InfoWindow.class.getResource("infoWindow.fxml");
//                logger.info(fxmlLocation.getPath().toString());
                FXMLLoader loader = new FXMLLoader(fxmlLocation);
                Parent root = loader.load();
                controller = loader.getController();

                scene = new Scene(root, 300, 500);
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void setCat(Human cat) {
            controller.setCat(cat);
        }

        public void show() {
            stage.show();
        }

    public InfoWindowController getController() {
        return controller;
    }
}
