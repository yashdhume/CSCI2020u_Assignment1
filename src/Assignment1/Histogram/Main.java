package Assignment1.Histogram;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Main extends Application {
    private final Pane histogram = new Pane();
    public int[] countLetter(TextField textField) {
        File file = new File(textField.getText());
        int[] letterCount = new int[26];
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                String line = scan.nextLine();
                char[] chars = line.toCharArray();
                for (char i : chars)
                    if (Character.isLetter(i))
                        letterCount[Character.toUpperCase(i) - 'A']++;
            }
        }catch(IOException except){ System.out.println("Error");}
        return letterCount;
    }
    public void displayHistogram(Pane histogram, int[] letterCounter) {
        int letterMax = 0;
        String alphabet ="";
        for (char i = 'A'; i <= 'Z'; i++) alphabet=alphabet.concat(i+" ");
        Text text = new Text(histogram.getWidth()*0.03+4,histogram.getHeight() * 0.8+5,alphabet);
        text.setFont(new Font(histogram.getWidth()*0.03));
        histogram.getChildren().clear();
        histogram.getChildren().add(text);
        for (int i : letterCounter)
            letterMax = Math.max(i, letterMax);
        for (int i = 0; i < letterCounter.length; i++) {
            double rectangleFlipper =  histogram.getHeight() * 0.7 - histogram.getHeight() * 0.7 * letterCounter[i] / (double)letterMax;
            Rectangle rectangle = new Rectangle( (histogram.getWidth()*0.03)* (i +1),
                    histogram.getHeight()/20 + rectangleFlipper,
                    histogram.getWidth()*0.03,
                    histogram.getHeight() * 0.7*letterCounter[i] / (double)letterMax);
            rectangle.setStroke(Color.BLACK);
            rectangle.setFill(Color.WHITE);
            histogram.getChildren().add(rectangle);
        }
    }

    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        HBox hbox = new HBox();
        Text txt = new Text("File Name: ");
        TextField textField = new TextField();
        Button button = new Button("View");
        button.setOnAction(e -> displayHistogram(histogram, countLetter(textField)));
        hbox.getChildren().addAll(txt, textField, button);
        pane.setCenter(histogram);
        pane.setBottom(hbox);
        Scene scene = new Scene(pane, 500, 300);
        primaryStage.setTitle("Histogram");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}