package gui.collections;

import client.ClientMain;
import client.commands.RuntimeEnv;
import gui.commands.CommandsWindow;
import gui.visualization.VisualizationWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

public class CollectionWindowController {
    Logger logger = Logger.getLogger("cwc");
    @FXML
    private Button signInButton;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    private final List<Locale> supportedLocales = Arrays.asList(
            new Locale("is"),
            new Locale("ru"),
            new Locale("da"),
            new Locale("es","GT")
    );
    private int currentLocaleIndex = 0;
    @FXML
    private void onSignInButtonClick(){
        logger.info("clicked");
        try {

//            AuthRequestSender rqSender = new AuthRequestSender();
//            AuthResponse response = rqSender.sendAuthData(username, password, ServerConnectionHandler.getCurrentConnection());
//
//            if (response.isAuth()) {
//                Client.getInstance(username, password);
//                Stage stage = (Stage) signInButton.getScene().getWindow();
//                stage.close();


//                CollectionsWindow collectionsWindow = new CollectionsWindow(currentLocaleIndex);
//                collectionsWindow.show();
//            } else {
//                AlertUtility.errorAlert("There is no user with this name, or password is incorrect");
//            }
        } catch (Exception e) {
            //errorAlert("Server is dead :(");
        }
    }
    @FXML
    private void onCommandsButtonClick(){
        CommandsWindow commandsWindow = new CommandsWindow();
        commandsWindow.show();
    }

    @FXML
    private void onVisualisationButtonClick(){
        VisualizationWindow visualizationWindow = new VisualizationWindow();
        visualizationWindow.show();
    }



}
