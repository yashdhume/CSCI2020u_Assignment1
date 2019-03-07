package Assignment1.CircleDrag;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class Main extends Application {

    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        GenerateCircleTriangle.genCircle(pane);
        GenerateCircleTriangle.genTriangle(pane);
        GenerateCircleTriangle.genLines(pane);
        GenerateCircleTriangle.genAngleText(pane);
        pane.setPrefSize(500, 500);
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }

}