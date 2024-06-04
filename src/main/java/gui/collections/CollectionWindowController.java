package gui.collections;

import client.ClientMain;
import client.commands.RuntimeEnv;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.logging.Logger;

public class CollectionWindowController {
    Logger logger = Logger.getLogger("cwc");
    @FXML
    private Button signInButton;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
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
}
