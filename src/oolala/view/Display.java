package oolala.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * JavaFX View class
 */
public abstract class Display extends Application {

  public static final int FRAME_WIDTH = 733;
  public static final int FRAME_HEIGHT = 680;
  public static final Paint BACKGROUND = Color.WHITE;
  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

  public void start(Stage stage) {
    //Variables
    Scene scene = setupGame(FRAME_WIDTH, FRAME_HEIGHT, BACKGROUND);
    stage.setScene(scene);
    stage.setTitle(TITLE);
    stage.show();
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step()));
    animation.play();
  }

  private Scene setupGame(int width, int height, Paint background) {
    //Set the scene
    Scene scene = new Scene(root, width, height, background);

    //https://docs.oracle.com/javafx/2/get_started/css.htm
    scene.getStylesheets().add
        (Display.class.getResource("LogoDisplay.css").toExternalForm());
    //https://tomsondev.bestsolution.at/2013/08/07/using-less-in-javafx/
//    LessCSSLoader ls = new LessCSSLoader();
//    scene.getStylesheets().add(ls.loadLess(getClass().getResource("logo.less")).toExternalForm());
    return scene;
  }

}