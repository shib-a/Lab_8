package gui.collections;

import client.ClientMain;
import client.commands.RuntimeEnv;
import common.Feedbacker;
import common.Human;
import gui.AlertUtility;
import gui.commands.CommandsWindow;
import gui.visualization.VisualizationWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import static gui.AlertUtility.errorAlert;

public class CollectionWindowController {
    Logger logger = Logger.getLogger("cwc");
    @FXML
    private Button createButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button visualizeButton;
    @FXML
    private Button commandsButton;
    @FXML
    private TableView<Human> table;
    @FXML
    private TableColumn<Human,String> idColumn;
    @FXML
    private TableColumn<Human,String> nameColumn;
    @FXML
    private TableColumn<Human,String> statusColumn;
    @FXML
    private TableColumn<Human,String> colorColumn;
    @FXML
    private TableColumn<Human,String> isAliveColumn;
    @FXML
    private TableColumn<Human,String> statsColumn;
    @FXML
    private TableColumn<Human,String> ownerColumn;
    @FXML
    private TableColumn<Human,String> rarityColumn;
    @FXML
    private TableColumn<Human,String> coordXColumn;
    @FXML
    private TableColumn<Human,String> coordYColumn;
    private ObservableList<Human> data;
    @FXML
    private PasswordField passwordField;
    RuntimeEnv re = ClientMain.getRe();
    private final List<Locale> supportedLocales = Arrays.asList(
            new Locale("is"),
            new Locale("ru"),
            new Locale("da"),
            new Locale("es","GT")
    );
    private int currentLocaleIndex = 0;
    @FXML
    private void initialize(){
        data = FXCollections.observableArrayList();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        isAliveColumn.setCellValueFactory(new PropertyValueFactory<>("isAlive"));
        statsColumn.setCellValueFactory(new PropertyValueFactory<>("stats"));
        ownerColumn.setCellValueFactory(new PropertyValueFactory<>("owner"));
        rarityColumn.setCellValueFactory(new PropertyValueFactory<>("rarity"));
        coordXColumn.setCellValueFactory(new PropertyValueFactory<>("coordX"));
        coordYColumn.setCellValueFactory(new PropertyValueFactory<>("coordY"));
        table.setItems(data);
//        handle(re.executeCommand(new String[]{"show",""}).getMessage());
        logger.info(re.getUser().getName());
    }
    @FXML
    private void onCreateButtonClick(){
        logger.info("clicked");
        try {
//            RuntimeEnv re = ClientMain.getRe();
            logger.info(re.getUser().getName());
            Feedbacker fb = re.executeCommand(new String[]{"add",""});
            logger.info(fb.getMessage());
            if(fb!=null) {
                try {
                    Human h = Human.fromCsvStr(fb.getMessage());
                    if (fb.getIsSuccessful()) {
//                        logger.info(h.getName());
                        data.add(h);
                    } else {
//                        AlertUtility.infoAlert("Duplicate object pulled: "+ h.getName());
                        logger.info("already in col");}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else logger.info("fb is null");
        } catch (Exception e) {
//            errorAlert("Server is dead :(");
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
    @FXML
    private void onClearButtonClick(){
//        RuntimeEnv re = ClientMain.getRe();
//        try{
        re.executeCommand(new String[]{"clear",""});
//        wait(500);
//        Feedbacker fb = re.executeCommand(new String[]{"show",""});
//        data.removeAll();
//        handle(fb.getMessage());
//    }
//        catch (InterruptedException e){e.printStackTrace();}
    }
    public void handle(String str){
        String[] strings = str.split("\n");
        for(String el : strings){
            Human h = Human.fromCsvStr(el);
            data.add(h);
        }

    }


}
