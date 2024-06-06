package gui.collections;

import client.ClientMain;
import client.commands.RuntimeEnv;
import common.Feedbacker;
import common.Human;
import gui.AlertUtility;
import gui.visualization.VisualizationWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
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
    private Button refreshButton;
    @FXML
    private Label filterByLabel;
    @FXML
    private Label catsLabel;
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
    private ResourceBundle currentBundle;
    @FXML
    private Button updateButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button removeLowerButton;
    @FXML
    private Button countRarityButton;
    @FXML
    private Label comsLabel;
    @FXML
    private ToolBar commandsToolBar;

    private Stage stage;

    @FXML
    private TextField updateField;
    @FXML
    private TextField removeField;
    @FXML
    private Label catNumberLabel;
    @FXML
    private Text usernameText;
    private ObservableList<Human> data;
    @FXML
    private ComboBox<String> comboBox;
    RuntimeEnv re = ClientMain.getRe();
    private final List<Locale> supportedLocales = Arrays.asList(
            new Locale("ru"),
            new Locale("is"),
            new Locale("da"),
            new Locale("es","GT")
    );
    private int currentLocaleIndex = 0;

    public void setLocale(int index) {
        this.currentLocaleIndex = index;
        currentBundle = ResourceBundle.getBundle("MessagesBundle", supportedLocales.get(currentLocaleIndex));
        updateUI();
    }

//    @FXML
//    protected void onGeoIconClick() {
//        currentLocaleIndex = (currentLocaleIndex + 1) % supportedLocales.size();
//        currentBundle = ResourceBundle.getBundle("MessagesBundle", supportedLocales.get(currentLocaleIndex));
//        updateUI();
//    }

    /**
     * Update LoginWindow UI
     */
    private void updateUI() {
        idColumn.setText(currentBundle.getString("id"));
        nameColumn.setText(currentBundle.getString("name"));
        coordXColumn.setText(currentBundle.getString("coordX"));
        coordYColumn.setText(currentBundle.getString("coordY"));
        statusColumn.setText(currentBundle.getString("status"));
        colorColumn.setText(currentBundle.getString("color"));
        isAliveColumn.setText(currentBundle.getString("isAlive"));
        statsColumn.setText(currentBundle.getString("stats"));
        ownerColumn.setText(currentBundle.getString("owner"));
        rarityColumn.setText(currentBundle.getString("rarity"));

        catsLabel.setText(currentBundle.getString("catsLabel"));
        filterByLabel.setText(currentBundle.getString("filterByLabel"));
        ObservableList<String> localizedItems = FXCollections.observableArrayList(
                currentBundle.getString("id"),
                currentBundle.getString("name"),
                currentBundle.getString("coordX"),
                currentBundle.getString("coordY"),
                currentBundle.getString("status"),
                currentBundle.getString("color"),
                currentBundle.getString("isAlive"),
                currentBundle.getString("stats"),
                currentBundle.getString("owner"),
                currentBundle.getString("rarity")
        );
        comboBox.getItems().setAll(localizedItems);
        commandsButton.setText(currentBundle.getString("commandsButton"));
        clearButton.setText(currentBundle.getString("clearButton"));
        refreshButton.setText(currentBundle.getString("refreshButton"));
        createButton.setText(currentBundle.getString("createButton"));
        visualizeButton.setText(currentBundle.getString("visualizeButton"));

        updateButton.setText(currentBundle.getString("updateButton"));
        removeLowerButton.setText(currentBundle.getString("removeLowerButton"));
        removeButton.setText(currentBundle.getString("removeButton"));
        countRarityButton.setText(currentBundle.getString("countRarityButton"));
        updateField.setPromptText(currentBundle.getString("id"));
        removeField.setPromptText(currentBundle.getString("id"));

    }

    @FXML
    private void initialize(){
        comboBox.getItems().addAll("id", "name", "status", "color", "isAlive", "stats", "owner", "rarity", "coord X", "coord Y");
        commandsToolBar.setVisible(false);

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
        handle(re.executeCommand(new String[]{"show",""}).getMessage());
        logger.info(re.getUser().getName());


        idColumn.setPrefWidth(50);
        nameColumn.setPrefWidth(80);
        statusColumn.setPrefWidth(105);
        colorColumn.setPrefWidth(95);
        isAliveColumn.setPrefWidth(70);
        statsColumn.setPrefWidth(100);
        ownerColumn.setPrefWidth(90);
        rarityColumn.setPrefWidth(80);
        coordXColumn.setPrefWidth(80);
        coordYColumn.setPrefWidth(80);
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
                        catNumberLabel.setText(String.valueOf(Integer.parseInt(catNumberLabel.getText())+1));
                    } else {
                        AlertUtility.infoAlert("Duplicate object pulled: "+ h.getName());
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
    private void onRefreshButtonClick(){
        Feedbacker fb = re.executeCommand(new String[]{"show",""});
        var temp = data;
        data.removeAll(temp);
        handle(fb.getMessage());
    }
    @FXML
    protected void onCommandsButtonClick() {
        commandsToolBar.setVisible(!commandsToolBar.isVisible());

    }

    @FXML
    private void onVisualisationButtonClick(){
        VisualizationWindow visualizationWindow = new VisualizationWindow();
        visualizationWindow.show();
    }
    @FXML
    private void onClearButtonClick(){
        re.executeCommand(new String[]{"clear",""});
        Feedbacker fb = re.executeCommand(new String[]{"show",""});
        var temp = data;
        data.removeAll(temp);
        handle(fb.getMessage());
    }
    public void handle(String str){
        String[] strings = str.split("\n");
        int count = 0;
        for(String el : strings){
            Human h = Human.fromCsvStr(el);
            data.add(h);
            count++;
        }
        catNumberLabel.setText(String.valueOf(count));
    }

    @FXML
    protected void onGeoIconOneClick() {
        currentLocaleIndex = (currentLocaleIndex + 1) % supportedLocales.size();
        currentBundle = ResourceBundle.getBundle("MessagesBundle", supportedLocales.get(currentLocaleIndex));
        updateUI();
    }

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
