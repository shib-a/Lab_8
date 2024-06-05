module main.gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.apache.commons.csv;
    requires java.logging;
    requires java.sql;
    requires java.base;

    opens common to javafx.base;
    opens gui to javafx.fxml;
    opens gui.login to javafx.fxml;
    opens gui.collections to javafx.fxml;
    opens gui.commands to javafx.fxml;
    opens gui.register to javafx.fxml;
    opens gui.visualization to javafx.fxml;

    exports gui.commands;
    exports gui;
    exports gui.collections;
    exports gui.login;
    exports gui.register;
    exports gui.visualization;
}