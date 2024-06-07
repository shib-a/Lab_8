package gui.info;
import common.Human;
import common.UserData;
import gui.AlertUtility;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public class InfoWindowController {
        @FXML
        private Label actionLabel;
        @FXML
        private Label idLabel;
        @FXML
        private Label nameLabel;
        @FXML
        private Label statusLabel;
        @FXML
        private Label colorLabel;
        @FXML
        private Label isAliveLabel;
        @FXML
        private Label statsLabel;
        @FXML
        private Label ownerLabel;
        @FXML
        private Label rarityLabel;
        @FXML
        private Label coordXLabel;
        @FXML
        private Label coordYLabel;
        private String actionText;
        private Map<Long, String> ownershipMap;
        private Human selectedCat;
        @FXML
        public void initialize() {
                actionLabel.setText(actionText);
        }

        public void populateFields(Human cat) {
                if (cat != null) {
                    idLabel.setText(String.valueOf(cat.getId()));
                    nameLabel.setText(cat.getName());
                    statsLabel.setText(cat.getStatus().name());
                    colorLabel.setText(cat.getColor().toString());
                    isAliveLabel.setText(String.valueOf(cat.getIsAlive()));
                    statsLabel.setText(cat.getStats());
                    ownerLabel.setText(cat.getOwner());
                    rarityLabel.setText(cat.getRarity().name());
                    coordXLabel.setText(cat.getCoordX().toString());
                    coordYLabel.setText(cat.getCoordY().toString());
                }
        }

        private void updateUI() {
                actionLabel.setText(actionText);
        }

        public void setActionText(String actionText) {
                this.actionText = actionText;
                updateUI();
        }

        public void setCat(Human cat) {
                this.selectedCat = cat;
                populateFields(selectedCat);
        }
}
