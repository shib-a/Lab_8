module main.gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens main.gui to javafx.fxml;
    opens main.gui.login to javafx.fxml;
    exports main.gui;

    exports main.gui.login;
}