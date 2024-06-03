package main.gui.login;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.gui.UTF8Control;


import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginWindowController {
    private ResourceBundle currentBundle;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInButton;

    @FXML
    private Label signUpLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label detailsLabel;

    @FXML
    private Label accountLabel;
//    private final List<Locale> supportedLocales = Arrays.asList(
//            new Locale("is"),
//            new Locale("ru"),
//            new Locale("da"),
//            new Locale("es","GT")
//    );
//    private int currentLocaleIndex = 0;
//
//    @FXML
//    public void initialize() {
//        currentBundle = ResourceBundle.getBundle("MessagesBundle", supportedLocales.get(currentLocaleIndex), new UTF8Control());
//        updateUI();
//    }
//
//    /**
//     * Update LoginWindow UI
//     */
//    private void updateUI() {
//        accountLabel.setText(currentBundle.getString("accountLabel"));
//        welcomeLabel.setText(currentBundle.getString("welcomeLabel"));
//        detailsLabel.setText(currentBundle.getString("detailsLabel"));
//        signInButton.setText(currentBundle.getString("signInButton"));
//        signUpLabel.setText(currentBundle.getString("signUpLabel"));
//        usernameLabel.setText(currentBundle.getString("usernameLabel"));
//        passwordLabel.setText(currentBundle.getString("passwordLabel"));
//    }

    @FXML
    protected void onGeoIconClick() {
//        currentLocaleIndex = (currentLocaleIndex + 1) % supportedLocales.size();
//        currentBundle = ResourceBundle.getBundle("MessagesBundle", supportedLocales.get(currentLocaleIndex), new UTF8Control());
//        updateUI();
    }

    @FXML
    protected void onSignInButtonClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Привет");
        alert.setHeaderText(null);
        alert.setContentText("Привет!");

        alert.showAndWait();
    }

    @FXML
    protected void onSignUpLabelClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Привет");
        alert.setHeaderText(null);
        alert.setContentText("Привет!");

        alert.showAndWait();

    }
}