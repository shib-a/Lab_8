package gui.visualization;

import client.ClientMain;
import client.commands.RuntimeEnv;
import common.Feedbacker;
import common.Human;
import gui.info.InfoWindow;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;


public class VisualizationWindowController {
    @FXML
    private Canvas canvas;
//    private ObservableList<Human> cats;
    private Map<String, Color> colorMap = new HashMap<>();
    private Map<Integer, Color> ownershipMap;
    private StackPane root = new StackPane();
    private ArrayList<Human> cats;
    private Logger logger = Logger.getLogger("vwc");

    public VisualizationWindowController() {
        RuntimeEnv re = ClientMain.getRe();
        Feedbacker fb = re.executeCommand(new String[]{"show",""});
        ArrayList<Human> cats = new ArrayList<>();
        Map<Integer, Color> map = new HashMap<>();
        String[] hs = fb.getMessage().split("\n");
        for (var el: hs){
            Human h = Human.fromCsvStr(el);
            cats.add(h);
            map.put(h.getId(), h.getColor());
        }
        this.cats = cats;
        this.ownershipMap=map;
//        cats.stream().forEach(e -> logger.info(e.getName()));
    }
    @FXML
    public void initialize() {
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> catClicked(event.getX(), event.getY()));
        drawMesh();
        drawCats();
    }
    private void drawMesh() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.web("#797979"));
        gc.setLineWidth(1);
        double cellSize = 20.0;
        double width = canvas.getWidth(), height = canvas.getHeight();

        for (double x = cellSize; x < width; x += cellSize) {
            gc.strokeLine(x, 0, x, height);
        }
        for (double y = cellSize; y < height; y += cellSize) {
            gc.strokeLine(0, height - y, width, height - y);
        }
    }
    public void setCollection(ArrayList<Human> col){this.cats=col;}

    private void catClicked(double x, double y) {
        Human clickedCat = null;
        for (Human cat : cats) {
            double canvasX = (double) cat.getCoordinates().getX() / 1000 * canvas.getWidth();
            double canvasY = (1 - cat.getCoordinates().getY() / 1000) * canvas.getHeight();
            double size = 10 * 2;
            double distance = Math.sqrt(Math.pow(x - canvasX, 2) + Math.pow(y - canvasY, 2));

            if (distance <= size / 2) {
                clickedCat = cat;
                break;
            }
        }
        if (clickedCat!=null){
            InfoWindow infoWindow = new InfoWindow();
            infoWindow.show();
            infoWindow.getController().setCat(clickedCat);
        }
    }

    private void drawCats() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        for (Human human : cats) {
            Color color = ownershipMap.get(human.getId());
            double size = 60;


            double canvasX = (double) human.getCoordinates().getX() / 1000 * canvas.getWidth();
            double canvasY = (1 - human.getCoordinates().getY() / 1000) * canvas.getHeight();

            gc.setStroke(color);
            gc.setLineWidth(2); // Adjust this to make your circle's border thicker or thinner
//            Image сatIcon = getCatIcon();

            ImageView imageView = new ImageView(getCatIcon());
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(size);
            imageView.setFitHeight(size);
            SnapshotParameters sp = new SnapshotParameters();
            sp.setFill(Color.TRANSPARENT);
            Image scaledCatIcon = imageView.snapshot(sp, null);

            PixelReader pr = scaledCatIcon.getPixelReader();
            WritableImage coloredIcon = new WritableImage((int) size, (int) size);
            PixelWriter pw = coloredIcon.getPixelWriter();
            for (int y = 0; y < scaledCatIcon.getHeight(); y++) {
                for (int x = 0; x < scaledCatIcon.getWidth(); x++) {
                    Color pixelColor = pr.getColor(x, y);
                    if (pixelColor.getOpacity() > 0) {
                        pw.setColor(x, y, color);
                    } else {
                        pw.setColor(x, y, Color.TRANSPARENT);
                    }
                }
            }

//            Image image = new Image("your_image_path.png"); // Укажите путь к вашему изображению
//
//             Создание представления изображения
//            ImageView imageView = new ImageView(image);

            // Создание таймлайна для мигания
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(0.5), e -> imageView.setImage(null)),
                    new KeyFrame(Duration.seconds(1), e -> imageView.setImage(getCatIcon()))
            );
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
//
//            // Размещение изображения на макете
//            StackPane root = new StackPane();
            root.getChildren().add(imageView);

//            // Создание сцены и добавление на неё макета
//            Scene scene = new Scene(root, 400, 400, Color.WHITE);


            gc.drawImage(coloredIcon, canvasX - size / 2, canvasY - size / 2);
//            gc.drawImage(coloredIcon, 0, 0);
//            Button ct = new Button();
//            ct.setGraphic(new ImageView(getCityIcon()));
//            ct.setLayoutX(0);
//            ct.setLayoutY(0);
//            VisualizationWindow.getStage().getScene().getCh
//            Duration duration = Duration.millis(2500);
//            //Create new translate transition
//            TranslateTransition transition = new TranslateTransition(duration, ct);
//            //Move in X axis by +200
//            transition.setByX(200);
//            //Move in Y axis by +100
//            transition.setByY(100);
//            transition.play();
        }
    }
    private Image getCatIcon() {
        return new Image(Objects.requireNonNull(VisualizationWindowController.class.getResource("cat.png")).toExternalForm());
    }

}
