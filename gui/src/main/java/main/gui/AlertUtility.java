package main.gui;

import javafx.scene.control.Alert;

public class AlertUtility{
    public static void infoAlert(String info) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(info);
        alert.showAndWait();
    }

    public static void errorAlert(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(error);
        alert.showAndWait();
    }
}