module main.gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.apache.commons.csv;
    requires java.logging;
    requires jdk.compiler;
    requires java.sql;

    opens gui to javafx.fxml;
    opens gui.login to javafx.fxml;
    opens gui.collections to javafx.fxml;
    exports gui;
    exports gui.collections;
    exports gui.login;
}