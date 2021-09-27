package oolala.view;

import java.util.Queue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import oolala.model.Instruction;
import oolala.model.Turtle;
import oolala.model.Logo;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;


/**
 * JavaFX View class
 */
public class LogoDisplay extends Application {

  public static final String TITLE = "Logo Turtle Game";
  public static final int FRAME_SIZE = 285;
  public static final Paint BACKGROUND = Color.WHITE;
  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

  private Scene scene;
  private Group root;
  private Group lineRoot;
  private Turtle myTurtle;
  private Logo myLogo;
  private TextArea commandLine;
  private String fileName;


  public void start(Stage stage) {
    scene = setupGame(FRAME_SIZE, FRAME_SIZE * 2, BACKGROUND);
    stage.setScene(scene);
    stage.setTitle(TITLE);
    stage.show();
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step()));
    animation.play();
  }

  private Scene setupGame(int width, int height, Paint background) {
    //Initialize the view classes
    myTurtle = new Turtle(0, 0);
    myLogo = new Logo();

    root = new Group(myTurtle.getMyTurtleView());

    //Initialize command line
    initializeCommandLine();

    //Set the scene
    Scene scene = new Scene(root, width, height, background);
    return scene;
  }


  private void initializeCommandLine() {
    commandLine = new TextArea();
    root.getChildren().add(commandLine);
    Button runCommands = new Button("Run");
    root.getChildren().add(runCommands);
    runCommands.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        myLogo.inputParser(commandLine.getText());
      }
    });
    Button saveCommands = new Button("Save");
    root.getChildren().add(saveCommands);
    saveCommands.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        myLogo.saveCommand(commandLine.getText(), getUserFileName());
      }
    });
  }

  private String getUserFileName(){
    TextInputDialog getUserInput = new TextInputDialog();
    getUserInput.setHeaderText("Enter a filename:");
    Button submit = new Button("Submit");
    EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e)
      {
        fileName = getUserInput.getEditor().getText();
      }
    };
    submit.setOnAction(event);
    root.getChildren().add(submit);
    return fileName;
  }


  //Create method that passes in queue of commands to Logo

  private void step() {
    Queue<Instruction> instructions = myLogo.getMyInstructions();
    if(!instructions.isEmpty()){
      Instruction currentInstruction = instructions.poll(); //pop a single instruction (FIFO i think?)
      myTurtle.execute(currentInstruction, root, lineRoot);
    }
  }


}
