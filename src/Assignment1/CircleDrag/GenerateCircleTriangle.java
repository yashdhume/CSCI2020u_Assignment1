package Assignment1.CircleDrag;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.Random;

public class GenerateCircleTriangle {
    private static Circle circle;
    private static Circle[] points = new Circle[3];
    private static Line[] lines = new Line[3];
    private static Text[] txts = new Text[3];
    private final static int radius = 150;

    public static double computey2y1(Line line) {
        double ay, ax, a;

        ay = line.getEndY() - line.getStartY();
        ay = ay * ay;
        ax = line.getEndX() - line.getStartX();
        ax = ax * ax;
        a = Math.sqrt(ay + ax);

        return a;
    }
    public static void genCircle(Pane pane){
        circle = new Circle(250, 250, radius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        pane.getChildren().add(circle);
    }
    public static double computeAngles(Line line1, Line line2, Line line3) {
        double a, b, c;
        a = computey2y1(line1);
        b = computey2y1(line2);
        c = computey2y1(line3);
        return Math.toDegrees(Math.acos((a * a - b * b - c * c) / (-2 * b * c)));
    }

    public static Circle setTrianglePoints(){
        Random rnd = new Random();
        Circle point = new Circle(10 );
        point.setFill(Color.RED);
        point.setStroke(Color.BLACK);

        double randomAngle = rnd.nextDouble()*10;

        double x = circle.getCenterX() + radius * Math.cos(randomAngle);
        double y = circle.getCenterY() + radius * Math.sin(randomAngle);
        point.setCenterX(x);
        point.setCenterY(y);
        point.toFront();
        return point;
    }
    public static void changeTextPoints(){
        double angle;
        angle = computeAngles(lines[1], lines[2], lines[0]);
        txts[0].setText(String.valueOf((int)angle));
        angle = computeAngles(lines[2], lines[1], lines[0]);
        txts[1].setText(String.valueOf((int)angle));
        angle = computeAngles(lines[0], lines[2], lines[1]);
        txts[2].setText(String.valueOf((int)angle));
    }
    public static void pointMouse(Circle point){
        point.setOnMouseDragged(e -> {
            double angle = (Math.atan2(e.getY() - circle.getCenterY(), e.getX() - circle.getCenterX()));
            Circle circleCenter = (Circle) e.getSource();
            circleCenter.setCenterY(radius * Math.sin(angle)+circle.getCenterY());
            circleCenter.setCenterX(radius * Math.cos(angle)+circle.getCenterX());
            changeTextPoints();
        });
    }

    public static void genTriangle(Pane pane){
        for (int i = 0; i < 3; i++) {
            Circle point =setTrianglePoints();
            pointMouse(point);
            points[i] = point;
            pane.getChildren().add(points[i]);
        }
    }
    public static void genLines(Pane pane){
        for (int i = 0; i < 3; i++) {
            int j;
            if(i==2) j =0;
            else j=i+1;
            Line line = new Line();
            line.startXProperty().bind(points[i].centerXProperty());
            line.startYProperty().bind(points[i].centerYProperty());
            line.endXProperty().bind(points[j].centerXProperty());
            line.endYProperty().bind(points[j].centerYProperty());
            lines[i] = line;
            pane.getChildren().add(lines[i]);

        }
    }
    public  static void genAngleText(Pane pane){
        for (int i = 0; i < 3; i++) {
            Text text = new Text();
            text.xProperty().bind(points[i].centerXProperty());
            text.yProperty().bind(points[i].centerYProperty().subtract(10));
            txts[i] = text;
            pane.getChildren().add(txts[i]);
        }

    }
}
