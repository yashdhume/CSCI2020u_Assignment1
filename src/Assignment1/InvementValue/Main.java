package Assignment1.InvementValue;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.DecimalFormat;


public class Main extends Application {
    public void start(Stage primaryStage) {
        GridPane gp = new GridPane();

        Text amountText = new Text("Investment Amount");
        TextField amountField = new TextField();
        amountField.setPromptText("Amount");
        amountField.setAlignment(Pos.BASELINE_RIGHT);

        Text yearsText = new Text("Years");
        TextField yearsField = new TextField();
        yearsField.setAlignment(Pos.BASELINE_RIGHT);

        Text anualIntresttRateText = new Text("Annual Interest Rate");
        TextField anualIntrestRateField = new TextField();
        anualIntrestRateField.setAlignment(Pos.BASELINE_RIGHT);

        Text futureValText = new Text("Future Value");
        Label futureValField = new Label();
        futureValField.setAlignment(Pos.BASELINE_RIGHT);
        GridPane.setHalignment(futureValField, HPos.RIGHT);


        Button calculateBtn = new Button("Calculate");
        GridPane.setHalignment(calculateBtn, HPos.RIGHT);

        calculateBtn.setOnAction(e -> {
            DecimalFormat df = new DecimalFormat("#.00");
            double investmentAmount = Double.parseDouble(amountField.getText());
            double years = Double.parseDouble(yearsField.getText());
            double annualRate = Double.parseDouble(anualIntrestRateField.getText());
            double monthlyInterestRate = annualRate / 100 / 12;
            double futureValue = investmentAmount * Math.pow((1 + monthlyInterestRate), years * 12);
            futureValField.setText(String.valueOf(df.format(futureValue)));
        });

        gp.add(amountText, 0, 0);
        gp.add(amountField, 1, 0);

        gp.add(yearsText, 0, 1);
        gp.add(yearsField, 1, 1);

        gp.add(anualIntresttRateText, 0, 2);
        gp.add(anualIntrestRateField, 1, 2);

        gp.add(futureValText, 0, 3);
        gp.add(futureValField, 1, 3);

        gp.add(calculateBtn, 1, 4);

        gp.setPadding(new Insets(10));
        gp.setHgap(10);
        gp.setVgap(10);

        Scene scene = new Scene(gp);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
