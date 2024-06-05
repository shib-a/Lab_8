package gui.register;

import gui.collections.CollectionsWindow;
import javafx.fxml.FXML;

public class RegisterWindowController {
    @FXML
    protected void onSignIiLabelClick() {
        //тут должно быть добавление юзера в табличку
        CollectionsWindow collectionsWindow = new CollectionsWindow();
        collectionsWindow.show();

    }
}
