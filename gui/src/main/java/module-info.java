module main.gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens main.gui to javafx.fxml;
    opens main.gui.login to javafx.fxml;
    opens main.gui.collections to javafx.fxml;
    opens main.gui.commands to javafx.fxml;
    opens main.gui.register to javafx.fxml;
    exports main.gui.commands;
    exports main.gui.register;
    exports main.gui;
    exports main.gui.collections;
    exports main.gui.login;
}