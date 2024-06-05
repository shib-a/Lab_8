package gui.login;

import client.ClientMain;
import client.commands.RuntimeEnv;
import gui.UTF8Control;
import gui.collections.CollectionsWindow;
import gui.register.RegisterWindow;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class LoginWindowController {
    private ResourceBundle currentBundle;
    Logger logger = Logger.getLogger("lwc");

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
    private final List<Locale> supportedLocales = Arrays.asList(
            new Locale("is"),
            new Locale("ru"),
            new Locale("da"),
            new Locale("es","GT")
    );
    private int currentLocaleIndex = 0;

    @FXML
    public void initialize() {
        currentBundle = ResourceBundle.getBundle("MessagesBundle", supportedLocales.get(currentLocaleIndex));
        updateUI();
    }

    /**
     * Update LoginWindow UI
     */
    private void updateUI() {
        accountLabel.setText(currentBundle.getString("accountLabel"));
        welcomeLabel.setText(currentBundle.getString("welcomeLabel"));
        detailsLabel.setText(currentBundle.getString("detailsLabel"));
        signInButton.setText(currentBundle.getString("signInButton"));
        signUpLabel.setText(currentBundle.getString("signUpLabel"));
        usernameLabel.setText(currentBundle.getString("usernameLabel"));
        passwordLabel.setText(currentBundle.getString("passwordLabel"));
        passwordField.setPromptText(currentBundle.getString("passwordField"));
        usernameField.setPromptText(currentBundle.getString("usernameField"));
    }

    @FXML
    protected void onGeoIconClick() {
        currentLocaleIndex = (currentLocaleIndex + 1) % supportedLocales.size();
        currentBundle = ResourceBundle.getBundle("MessagesBundle", supportedLocales.get(currentLocaleIndex));
        updateUI();
    }

    @FXML
    protected void onSignInButtonClick() throws IOException {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Привет");
//        alert.setHeaderText(null);
//        alert.setContentText("Привет!");
//
//        alert.showAndWait();
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        RuntimeEnv re = ClientMain.getRe();
        logger.info(re.getUser().toString());
        logger.info(username +" "+ password);
        if(re.executeCommand(new String[]{"login", username+" "+password}).getIsSuccessful()){
            logger.info(re.getUser().toString());
            Stage stage = (Stage) signInButton.getScene().getWindow();
            stage.close();
            CollectionsWindow collectionsWindow = new CollectionsWindow(currentLocaleIndex);
            collectionsWindow.show();
        }
        logger.info(re.getUser().getName());
//        Stage stage = (Stage) signInButton.getScene().getWindow();
//        stage.close();

    }

    @FXML
    protected void onSignUpLabelClick() {
        RegisterWindow registerWindow = new RegisterWindow(currentLocaleIndex);
        registerWindow.show();

    }
}