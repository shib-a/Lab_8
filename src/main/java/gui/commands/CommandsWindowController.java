package gui.commands;

import client.ClientMain;
import client.commands.RuntimeEnv;
import common.Feedbacker;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CommandsWindowController {
    @FXML
    private Button updateButton;
    @FXML
    private TextField updateField;

    @FXML
    private void onUpdateButtonClick(){
        RuntimeEnv re = ClientMain.getRe();
        String arg = updateField.getText();
        Feedbacker fb = re.executeCommand(new String[]{"update",arg});
        if (fb==null){

            return;
        }
        if (fb.getIsSuccessful()){

        }
    }
}
