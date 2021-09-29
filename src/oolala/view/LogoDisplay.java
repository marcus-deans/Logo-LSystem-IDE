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
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import oolala.model.Instruction;
import oolala.model.Turtle;
import oolala.model.Logo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


/**
 * JavaFX View class
 */
public class LogoDisplay extends Application {

  public static final String TITLE = "Logo Turtle Game";
  public static final int FRAME_WIDTH = 733;
  public static final int FRAME_HEIGHT = 680;
  public static final Paint BACKGROUND = Color.WHITE;
  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

  //Top Layout
  public static final int GAME_TITLE_X = 10;
  public static final int GAME_TITLE_Y = 17;
  public static final int GAME_DROPDOWN_X = 100;
  public static final int GAME_DROPDOWN_Y = 0;
  public static final int SAVED_TITLE_X = 210;
  public static final int SAVED_TITLE_Y = 17;
  public static final int SAVED_DROPDOWN_X = 320;
  public static final int SAVED_DROPDOWN_Y = 0;
  public static final int HISTORY_TITLE_X = 445;
  public static final int HISTORY_TITLE_Y = 17;
  public static final int HISTORY_DROPDOWN_X = 495;
  public static final int HISTORY_DROPDOWN_Y = 0;
  public static final int MAX_DROPDOWN_WIDTH = 120;

  //Bottom Layout
  public static final int COMMAND_WIDTH = 600;
  public static final int COMMAND_HEIGHT = 130;
  public static final int COMMAND_X = 10;
  public static final int COMMAND_Y = 530;
  public static final int RUN_WIDTH = 100;
  public static final int RUN_HEIGHT = 30;
  public static final int RUN_X = 620;
  public static final int RUN_Y = 530;
  public static final int SAVE_WIDTH = 100;
  public static final int SAVE_HEIGHT = 30;
  public static final int SAVE_X = 620;
  public static final int SAVE_Y = 565;
  public static final int CLEAR_WIDTH = 100;
  public static final int CLEAR_HEIGHT = 30;
  public static final int CLEAR_X = 620;
  public static final int CLEAR_Y = 600;

  private Group root;
  private Group lineRoot;
  private Turtle myTurtle;
  private Logo myLogo;
  private TextArea commandLine;
  private String fileName;
  private ComboBox gameSetting;
  private ComboBox savedPrograms;
  private ComboBox historyPrograms;
  private double turtleHomeX;
  private double turtleHomeY;

  //Games
  private List<String> gameTypes = new ArrayList<>(Arrays.asList("Logo", "L-System", "Darwin"));
  //Turtles
  private ArrayList<Turtle> allTurtles = new ArrayList<>();

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
    myLogo = new Logo();
    spawnTurtle();
    initializeGameSetting(); //game type dropdown
    initializeSavedPrograms(); //saved programs dropdown
    initializeHistory(); //program history dropdown
    initializeCommandLine(); //initialize the command line
    initializeRunButton(); //initialize the program run button
    initializeSaveButton(); //initializes the program save button
    initializeClearScreen();
    initializeBoundaries(); // sets up program boundaries for where the turtle will move
    //Set the scene
    Scene scene = new Scene(root, width, height, background);
    return scene;
  }

  private void spawnTurtle() {
    myTurtle = new Turtle(0, 0);
    turtleHomeX = FRAME_WIDTH/2 - myTurtle.getMyTurtleView().getFitWidth()/2;
    turtleHomeY = (FRAME_HEIGHT-26-COMMAND_HEIGHT+15)/2 - myTurtle.getMyTurtleView().getFitHeight()/2;
    root = new Group(myTurtle.getMyTurtleView());
    allTurtles.add(myTurtle);
    myTurtle.getMyTurtleView().setX(turtleHomeX);
    myTurtle.getMyTurtleView().setY(turtleHomeY);
  }

  private void initializeBoundaries() {
    Line topLine = new Line(10,26, FRAME_WIDTH-10,26);
    Line leftLine = new Line(10, 26, 10, COMMAND_Y-15);
    Line rightLine = new Line(FRAME_WIDTH-10, 26, FRAME_WIDTH-10, COMMAND_Y-15);
    Line bottomLine = new Line(10,COMMAND_Y-15,FRAME_WIDTH-10,COMMAND_Y-15);
    root.getChildren().add(topLine);
    root.getChildren().add(leftLine);
    root.getChildren().add(rightLine);
    root.getChildren().add(bottomLine);
  }

  private void initializeGameSetting() {
    Text gameSettingTitle = new Text("Game Setting: ");
    gameSettingTitle.setLayoutX(GAME_TITLE_X);
    gameSettingTitle.setLayoutY(GAME_TITLE_Y);
    root.getChildren().add(gameSettingTitle);
    gameSetting = new ComboBox<>(FXCollections.observableList(gameTypes));
    gameSetting.setLayoutX(GAME_DROPDOWN_X);
    gameSetting.setLayoutY(GAME_DROPDOWN_Y);
    gameSetting.setMaxWidth(MAX_DROPDOWN_WIDTH);
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
    savedPrograms.setMaxWidth(MAX_DROPDOWN_WIDTH);
    populateFileNames();
    savedPrograms.setOnAction((event) -> {
      getContentFromFilename();
    });
    root.getChildren().add(savedPrograms);
  }

  private void updateSavedDropdown() {
    savedPrograms.getItems().clear();
    populateFileNames();
  }

  private void getContentFromFilename() {
    String filename = savedPrograms.getSelectionModel().getSelectedItem().toString();
    File[] files = new File("/Users/naylaboorady/Downloads/oolala_team01/data/examples/logo").listFiles();
    for(File file : files){
      if(file.isFile() && file.getName().equals(filename)){
        try {
          Scanner scanner = new Scanner(file);
          String input;
          StringBuffer contents = new StringBuffer();
          while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            if(!Arrays.asList(input.split("")).contains("#")){
              contents.append(input+" ");
            }
          }
          commandLine.setText(contents.toString());
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private void populateFileNames() {
    File[] files = new File("/Users/naylaboorady/Downloads/oolala_team01/data/examples/logo").listFiles();
    for (File file : files) {
      if (file.isFile()) {
        savedPrograms.getItems().add(file.getName());
      }
    }
  }

  private void initializeHistory(){
    Text history = new Text("History: ");
    history.setLayoutX(HISTORY_TITLE_X);
    history.setLayoutY(HISTORY_TITLE_Y);
    root.getChildren().add(history);
    historyPrograms = new ComboBox();
    historyPrograms.setOnAction((event) -> {
      commandLine.setText(historyPrograms.getSelectionModel().getSelectedItem().toString());
    });
    historyPrograms.setLayoutX(HISTORY_DROPDOWN_X);
    historyPrograms.setLayoutY(HISTORY_DROPDOWN_Y);
    historyPrograms.setMaxWidth(MAX_DROPDOWN_WIDTH);
    root.getChildren().add(historyPrograms);
  }

  private void updateHistoryDropdown() { //TODO: make sure history is specific to current game model
    historyPrograms.getItems().clear();
    for(String element: myLogo.getHistory()){
      historyPrograms.getItems().add(element);
    }
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
        myLogo.inputParser(commandLine.getText());
        validateCommandStream();
        myLogo.saveHistory(commandLine.getText());
        updateHistoryDropdown();
      }
    });
  }

  private void validateCommandStream() {
    Boolean valid = myLogo.getValidCommand();
    if(!valid){
      System.out.println("Invalid command!");
      Popup popup = new Popup();
      popup.setAutoFix(true);
      popup.setAutoHide(true);
      popup.setHideOnEscape(true);
      Label label = new Label("Invalid Command!");
      label.setOnMouseReleased(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
          popup.hide();
        }
      });
      popup.getContent().add(label);
      myLogo.setValidCommand(true);
    }else{
      System.out.println("Valid command!");
    }
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
        myLogo.saveCommand(commandLine.getText(), filename);
        updateSavedDropdown();
      }
    });
  }

  private void initializeClearScreen() {
    Button clearScreen = new Button("Clear");
    clearScreen.setPrefWidth(CLEAR_WIDTH);
    clearScreen.setPrefHeight(CLEAR_HEIGHT);
    clearScreen.setLayoutX(CLEAR_X);
    clearScreen.setLayoutY(CLEAR_Y);
    root.getChildren().add(clearScreen);
    clearScreen.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        //TODO: call turtle.home method, delete lines, delete created turtles
        commandLine.clear();
        historyPrograms.getItems().clear();
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
    //If an instruction has been sent to myLogo, run it
    Queue<Instruction> instructions = myLogo.getMyInstructions();
    if(!instructions.isEmpty()){
      Instruction currentInstruction = instructions.poll(); //pop a single instruction (FIFO i think?)
      myTurtle.execute(currentInstruction, root, lineRoot);
    }
  }
}
