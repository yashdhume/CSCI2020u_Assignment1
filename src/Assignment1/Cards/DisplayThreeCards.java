package Assignment1.Cards;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DisplayThreeCards extends Application {
    List<Image> cards= new ArrayList<>();
    private void loadImages(){
        for (int i = 1; i <55 ; i++) {
            String path = "/Assignment1/Cards/CardsImages/" +String.valueOf(i)+ ".png";
            cards.add(new Image(path));
        }
    }
    public void start(Stage primaryStage){

        loadImages();
        Collections.shuffle(cards);
        ImageView card1 = new ImageView(cards.get(0));
        ImageView card2 = new ImageView(cards.get(1));
        ImageView card3 = new ImageView(cards.get(2));

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(card1, card2, card3);

        Scene scene = new Scene(hbox, card3.getX(), card3.getY());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main (String [] args){ Application.launch(args); }

}
