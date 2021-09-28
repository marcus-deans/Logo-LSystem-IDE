package oolala.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import oolala.model.Instruction;
import oolala.model.Turtle;
import oolala.model.Logo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * JavaFX View class
 */
public class LogoDisplay extends Application {

  public static final String TITLE = "Logo Turtle Game";
  public static final int FRAME_WIDTH = 800;
  public static final int FRAME_HEIGHT = 700;
  public static final Paint BACKGROUND = Color.WHITE;
  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

  //Top Layout
  public static final int GAME_TITLE_X = 5;
  public static final int GAME_TITLE_Y = 17;
  public static final int GAME_DROPDOWN_X = 100;
  public static final int GAME_DROPDOWN_Y = 0;
  public static final int SAVED_TITLE_X = 210;
  public static final int SAVED_TITLE_Y = 17;
  public static final int SAVED_DROPDOWN_X = 320;
  public static final int SAVED_DROPDOWN_Y = 0;
  public static final int HISTORY_TITLE_X = 400;
  public static final int HISTORY_TITLE_Y = 17;
  public static final int HISTORY_DROPDOWN_X = 450;
  public static final int HISTORY_DROPDOWN_Y = 0;

  //Bottom Layout
  public static final int COMMAND_WIDTH = 600;
  public static final int COMMAND_HEIGHT = 130;
  public static final int COMMAND_X = 10;
  public static final int COMMAND_Y = 530;
  public static final int RUN_WIDTH = 100;
  public static final int RUN_HEIGHT = 60;
  public static final int RUN_X = 620;
  public static final int RUN_Y = 530;
  public static final int SAVE_WIDTH = 100;
  public static final int SAVE_HEIGHT = 60;
  public static final int SAVE_X = 620;
  public static final int SAVE_Y = 600;


  private Group root;
  private Group lineRoot;
  //    private Turtle myTurtle;
//    private Logo myLogo;
  private TextArea commandLine;
  private String fileName;
  private ComboBox gameSetting;
  private ComboBox savedPrograms;
  private ComboBox historyPrograms;


  //Games
  private List<String> gameTypes = new ArrayList<>(Arrays.asList("Logo", "L-System", "Darwin"));

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
    //Initialize the view classes
//        myTurtle = new Turtle(0, 0, root);
//        myLogo = new Logo();

    root = new Group(); //root = new Group(myTurtle.getMyTurtleView());
    initializeGameSetting();
    initializeSavedPrograms();
    initializeHistory();
    initializeCommandLine(); //initialize the command line
    initializeRunButton(); //initialize the program run button
    initializeSaveButton(); //initializes the program save button

    //Set the scene
    Scene scene = new Scene(root, width, height, background);
    return scene;
  }

  private void initializeGameSetting() {
    Text gameSettingTitle = new Text("Game Setting: ");
    gameSettingTitle.setLayoutX(GAME_TITLE_X);
    gameSettingTitle.setLayoutY(GAME_TITLE_Y);
    root.getChildren().add(gameSettingTitle);
    gameSetting = new ComboBox<>(FXCollections.observableList(gameTypes));
    gameSetting.setLayoutX(GAME_DROPDOWN_X);
    gameSetting.setLayoutY(GAME_DROPDOWN_Y);
    gameSetting.setOnAction((event) -> {
      //TODO: run a different program based on user selection
    });
    root.getChildren().add(gameSetting);
  }

  private void initializeSavedPrograms(){
    Text savedProgramTitle = new Text("Saved Programs: ");
    savedProgramTitle.setLayoutX(SAVED_TITLE_X);
    savedProgramTitle.setLayoutY(SAVED_TITLE_Y);
    root.getChildren().add(savedProgramTitle);
    savedPrograms = new ComboBox();
    savedPrograms.setLayoutX(SAVED_DROPDOWN_X);
    savedPrograms.setLayoutY(SAVED_DROPDOWN_Y);
    //TODO: populate filenames from "example" folder
    root.getChildren().add(savedPrograms);
  }

  private void initializeHistory(){
    Text history = new Text("History: ");
    history.setLayoutX(HISTORY_TITLE_X);
    history.setLayoutY(HISTORY_TITLE_Y);
    root.getChildren().add(history);
    historyPrograms = new ComboBox();
    //historyPrograms = new ComboBox<>(FXCollections.observableList(myLogo.getHistory()));
    historyPrograms.setLayoutX(HISTORY_DROPDOWN_X);
    historyPrograms.setLayoutY(HISTORY_DROPDOWN_Y);
    root.getChildren().add(historyPrograms);
  }

  private void initializeCommandLine() {
    commandLine = new TextArea();
    commandLine.setPrefWidth(COMMAND_WIDTH);
    commandLine.setPrefHeight(COMMAND_HEIGHT);
    commandLine.setLayoutX(COMMAND_X);
    commandLine.setLayoutY(COMMAND_Y);
    root.getChildren().add(commandLine);
  }

  private void initializeRunButton() {
    Button runCommands = new Button("Run");
    runCommands.setPrefWidth(RUN_WIDTH);
    runCommands.setPrefHeight(RUN_HEIGHT);
    runCommands.setLayoutX(RUN_X);
    runCommands.setLayoutY(RUN_Y);
    root.getChildren().add(runCommands);
    runCommands.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        System.out.println("Run command: " + commandLine.getText());
        //myLogo.inputParser(commandLine.getText());
        //myLogo.saveHistory(commandLine.getText());
      }
    });
  }

  private void initializeSaveButton() {
    Button saveCommands = new Button("Save");
    saveCommands.setPrefWidth(SAVE_WIDTH);
    saveCommands.setPrefHeight(SAVE_HEIGHT);
    saveCommands.setLayoutX(SAVE_X);
    saveCommands.setLayoutY(SAVE_Y);
    root.getChildren().add(saveCommands);
    saveCommands.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        String filename = getUserFileName();
        System.out.println(filename);
        System.out.println("Run command: "+ commandLine.getText());
        //myLogo.saveCommand(commandLine.getText(), getUserFileName());
      }
    });
  }

  private String getUserFileName(){
    TextInputDialog getUserInput = new TextInputDialog();
    getUserInput.setHeaderText("Enter a filename:");
    fileName = getUserInput.showAndWait().toString();
    if(validateStringFilenameUsingIO(fileName)){
      return fileName;
    }else{
      //TODO: throw error - invalid filename
      return "";
    }
  }

  private boolean validateStringFilenameUsingIO(String filename){
    File file = new File(filename);
    boolean created = false;
    try {
      created = file.createNewFile();
      return created;
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (created) {
        file.delete();
      }
    }
    return false;
  }

  //Create method that passes in queue of commands to Logo

  private void step() {
//        Queue<Instruction> instructions = myLogo.getMyInstructions();
//        if(!instructions.isEmpty()){
//            Instruction currentInstruction = instructions.poll(); //pop a single instruction (FIFO i think?)
//            myTurtle.execute(currentInstruction, root, lineRoot);
//        }
  }
}
