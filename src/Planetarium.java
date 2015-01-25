import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.shape.Path;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

/**
* This class models the solar system with correct radii, orbit proportions and  speed rates.
* @author Austin Herring
* @version 1.0
*/
public class Planetarium extends Application {

    private int state = 0;
    private double speed = 28.937;
    private double a = .0004;
    private Timeline timeline;
    private Label timerLabel = new Label();
    private DoubleProperty timeSeconds = new SimpleDoubleProperty();
    private Duration time = Duration.ZERO;


    @Override
    public void start(Stage stage) {
        stage.setTitle("Solar System");
        Group group = new Group();
        final Rectangle[] r = {new Rectangle(0, 0, 1300, 700)};
        final Circle[] sun = {new Circle(1, Color.YELLOW)};
        sun[0].relocate(600, 350);
        group.getChildren().addAll(r[0], sun[0]);

        // This establishes a label that will be bound to a time line
        // incrementing at a rate rounded to 3 decimals places
        timerLabel.textProperty().bind(timeSeconds.asString());
        timerLabel.setFont(new Font("Cambria", 25));
        timerLabel.setTextFill(Color.WHITE);
        timeline = new Timeline(
                new KeyFrame(Duration.millis(100), (t) -> {
                    Duration duration = ((KeyFrame) t.getSource()).getTime();
                    time = time.add(duration);
                    double x = Double.parseDouble(String.format("%.3f", time.toSeconds() * speed));
                    timeSeconds.set(x);
                })
        );

        for (Planet p : Planet.values()) {
            Circle circ = p.circle(p.radius());
            Path pt = p.path(p.distanceX(), p.distanceY());

            PathTransition pathTransition = new PathTransition();
            pathTransition.setInterpolator(Interpolator.LINEAR);
            pathTransition.setDuration(Duration.millis(p.period() * a));
            pathTransition.setNode(circ);
            pathTransition.setPath(pt);
            pathTransition.setCycleCount(Integer.MAX_VALUE);

            pathTransition.play();
            group.getChildren().addAll(pt, circ);
        }

        // Zoom button created and set to enlarge the orbits and planet
        // radii of the inner four, keeping correct proportion, while
        // excluding the outer 4 planets
        Button zI = new Button("Zoom In");
        zI.setOnAction(event -> {
            int i = 0;
            if (state == 0) {
                group.getChildren().clear();
                r[0] = new Rectangle(0, 0, 1300, 700);
                sun[0] = new Circle(1, Color.YELLOW);
                sun[0].relocate(600, 350);
                group.getChildren().addAll(r[0], sun[0]);
                for (Planet p : Planet.values()) {
                    if (i < 4) {
                        Circle circ = p.circle(p.radius() * 18);
                        Path pt = p.path(p.distanceX() * 18, p.distanceY() * 18);

                        PathTransition pathTransition = new PathTransition();
                        pathTransition.setInterpolator(Interpolator.LINEAR);
                        pathTransition.setDuration(Duration.millis(p.period() * a));
                        pathTransition.setNode(circ);
                        pathTransition.setPath(pt);
                        pathTransition.setCycleCount(Integer.MAX_VALUE);

                        pathTransition.play();
                        group.getChildren().addAll(pt, circ);
                    }
                    i++;
                }
                i = 0;
                state = 1;
                time = Duration.ZERO;
            }
        });

        // Zoom button created and set to decrease the orbits and planet
        // radii of the inner four, keeping correct proportion, while including
        // the outer 4 planets
        Button zO = new Button("Zoom out");
        zO.setOnAction(event -> {
            if (state == 1) {
                group.getChildren().clear();
                r[0] = new Rectangle(0, 0, 1300, 700);
                sun[0] = new Circle(1, Color.YELLOW);
                sun[0].relocate(600, 350);
                group.getChildren().addAll(r[0], sun[0]);
                for (Planet p : Planet.values()) {
                        Circle circ = p.circle(p.radius());
                        Path pt = p.path(p.distanceX(), p.distanceY());

                        PathTransition pathTransition = new PathTransition();
                        pathTransition.setInterpolator(Interpolator.LINEAR);
                        pathTransition.setDuration(Duration.millis(p.period() * a));
                        pathTransition.setNode(circ);
                        pathTransition.setPath(pt);
                        pathTransition.setCycleCount(Integer.MAX_VALUE);

                        pathTransition.play();
                        group.getChildren().addAll(pt, circ);
                }
                state = 0;
                time = Duration.ZERO;
            }
        });

        // Label created
        Label label = new Label("       Time Lapse Controls:");
        label.setFont(new Font("Cambria", 25));
        label.setTextFill(Color.WHITE);

        // Button created to re-plot the planets and orbits
        // following the new period properties, "28.937 Days per Second"
        Button r1 = new Button("28.937 Days per Second");
        r1.setOnAction(event -> {
            int i = 0;
            if (speed == 385.802) {
                a = .0004;
                group.getChildren().clear();
                r[0] = new Rectangle(0, 0, 1300, 700);
                sun[0] = new Circle(1, Color.YELLOW);
                sun[0].relocate(600, 350);
                group.getChildren().addAll(r[0], sun[0]);
                int ratio;
                if (state == 1) {
                    ratio = 18;
                    for (Planet p : Planet.values()) {
                        if (i < 4) {
                            Circle circ = p.circle(p.radius() * ratio);
                            Path pt = p.path(p.distanceX() * ratio, p.distanceY() * ratio);
                            PathTransition pathTransition = new PathTransition();
                            pathTransition.setInterpolator(Interpolator.LINEAR);
                            pathTransition.setDuration(Duration.millis(p.period() * a));
                            pathTransition.setNode(circ);
                            pathTransition.setPath(pt);
                            pathTransition.setCycleCount(Integer.MAX_VALUE);

                            pathTransition.play();
                            group.getChildren().addAll(pt, circ);
                        }
                        i++;
                    }
                    i = 0;
                } else {
                    ratio = 1;
                    for (Planet p : Planet.values()) {
                        Circle circ = p.circle(p.radius() * ratio);
                        Path pt = p.path(p.distanceX() * ratio, p.distanceY() * ratio);
                        PathTransition pathTransition = new PathTransition();
                        pathTransition.setInterpolator(Interpolator.LINEAR);
                        pathTransition.setDuration(Duration.millis(p.period() * a));
                        pathTransition.setNode(circ);
                        pathTransition.setPath(pt);
                        pathTransition.setCycleCount(Integer.MAX_VALUE);

                        pathTransition.play();
                        group.getChildren().addAll(pt, circ);
                    }
                }
                time = Duration.ZERO;
            }
            speed = 28.937;
            timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), (t) -> {
                        Duration duration = ((KeyFrame) t.getSource()).getTime();
                        time = time.add(duration);
                        double x = Double.parseDouble(String.format("%.3f", time.toSeconds() * speed));
                        timeSeconds.set(x);
                    })
            );
        });

        // Button created to re-plot the planets and orbits
        // following the new period properties, "385.802 Days per Second"
        Button r2 = new Button("385.802 Days per Second");
        r2.setOnAction(event -> {
            int i = 0;
            if (speed == 28.937) {
                a = .00003;
                group.getChildren().clear();
                r[0] = new Rectangle(0, 0, 1300, 700);
                sun[0] = new Circle(1, Color.YELLOW);
                sun[0].relocate(600, 350);
                group.getChildren().addAll(r[0], sun[0]);
                int ratio;
                if (state == 1) {
                    ratio = 18;
                    for (Planet p : Planet.values()) {
                        if (i < 4) {
                            Circle circ = p.circle(p.radius() * ratio);
                            Path pt = p.path(p.distanceX() * ratio, p.distanceY() * ratio);
                            PathTransition pathTransition = new PathTransition();
                            pathTransition.setInterpolator(Interpolator.LINEAR);
                            pathTransition.setDuration(Duration.millis(p.period() * a));
                            pathTransition.setNode(circ);
                            pathTransition.setPath(pt);
                            pathTransition.setCycleCount(Integer.MAX_VALUE);

                            pathTransition.play();
                            group.getChildren().addAll(pt, circ);
                        }
                        i++;
                    }
                    i = 0;
                } else {
                    ratio = 1;
                    for (Planet p : Planet.values()) {
                            Circle circ = p.circle(p.radius() * ratio);
                            Path pt = p.path(p.distanceX() * ratio, p.distanceY() * ratio);
                            PathTransition pathTransition = new PathTransition();
                            pathTransition.setInterpolator(Interpolator.LINEAR);
                            pathTransition.setDuration(Duration.millis(p.period() * a));
                            pathTransition.setNode(circ);
                            pathTransition.setPath(pt);
                            pathTransition.setCycleCount(Integer.MAX_VALUE);

                            pathTransition.play();
                            group.getChildren().addAll(pt, circ);
                    }
                }
                time = Duration.ZERO;
            }
            speed = 385.802;
            timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), (t) -> {
                        Duration duration = ((KeyFrame) t.getSource()).getTime();
                        time = time.add(duration);
                        double x = Double.parseDouble(String.format("%.3f", time.toSeconds() * speed));
                        timeSeconds.set(x);
                    })
            );
        });

        // Sets up UI and runs the program
        Label label1 = new Label("Days gone by:");
        label1.setFont(new Font("Cambria", 25));
        label1.setTextFill(Color.WHITE);
        HBox root = new HBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setStyle("-fx-background-color: #000000;");
        group.setStyle("-fx-background-color: #000000;");
        root.getChildren().addAll(zI, zO, label, r1, r2, label1, timerLabel);
        VBox everything = new VBox();
        everything.getChildren().addAll(root, group);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        stage.setScene(new Scene(everything));
        stage.show();
    }
}
