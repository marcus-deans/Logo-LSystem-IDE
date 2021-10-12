package oolala.view.displays;

import java.io.File;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import oolala.model.games.LSystem;
import oolala.model.instructions.LogoInstruction;

public class LSystemDisplay extends LogoDisplay {

  private static final int BORDER_HEIGHT = OFFSET_Y_TOP - (COMMAND_Y - OFFSET_Y);
  public static final int FRAME_WIDTH = 900;
  public static final int BUFFER = 300;
  private Text levelText;
  private ComboBox levelsDropdown;
  private int numLevels;
  private int turnAngle;
  private int stepLength;

  //Right Options Setup
  public static final int LEVEL_TEXT_X = 740;
  public static final int LEVEL_TEXT_Y = 100;
  public static final int LEVEL_DROPDOWN_X = 800;
  public static final int LEVEL_DROPDOWN_Y = 86;
  public static final int ANGLE_TEXT_X = 0;
  public static final int ANGLE_TEXT_Y = 0;
  public static final int ANGLE_DROPDOWN_X = 0;
  public static final int ANGLE_DROPDOWN_Y = 0;
  public static final int LENGTH_TEXT_X = 0;
  public static final int LENGTH_TEXT_Y = 0;
  public static final int LENGTH_DROPDOWN_X = 0;
  public static final int LENGTH_DROPDOWN_Y = 0;

  private final int LSYSTEM_OFFSET_X = 300;
  private final int LSYSTEM_OFFSET_Y = 100;

  @Override //Overrides in order to change the frame width
  public void start(Stage stage) {
    //Variables
    scene = setupGame(FRAME_WIDTH, FRAME_HEIGHT, BACKGROUND);
    stage.setScene(scene);
    stage.setTitle(TITLE);
    stage.show();
    myAnimation = new Timeline();
    myAnimation.setCycleCount(Timeline.INDEFINITE);
    myAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step()));
    myAnimation.play();
  }

  @Override
  protected Scene setupGame(int width, int height, Paint background) {
    //Initialize the view classes
    myGameProcessor = new LSystem();
    performInitialSetup();
    spawnTurtle(0);
    initializeLevelDropdown();
    initializeAngleDropdown();
    initializeLengthDropdown();
    //Set the scene
    Scene scene = new Scene(root, width, height, background);
    scene.getStylesheets().add(LogoDisplay.class.getResource("Display.css").toExternalForm());
    return scene;
  }

  private void initializeLevelDropdown() {
    levelsTitle();
    levelsDropdown = new ComboBox();
    levelsDropdown.setLayoutX(LEVEL_DROPDOWN_X);
    levelsDropdown.setLayoutY(LEVEL_DROPDOWN_Y);
    levelsDropdown.setMaxWidth(MAX_DROPDOWN_WIDTH);
    for(int i=0; i<6; i++){
      levelsDropdown.getItems().add(i);
    }
    levelsDropdown.setOnAction((event) -> {
        numLevels = Integer.valueOf(levelsDropdown.getSelectionModel().getSelectedItem().toString());
    });
    root.getChildren().add(levelsDropdown);
  }

  private void levelsTitle() {
    levelText = new Text("Levels: ");
    levelText.setLayoutX(LEVEL_TEXT_X);
    levelText.setLayoutY(LEVEL_TEXT_Y);
    root.getChildren().add(levelText);
  }

  private void initializeAngleDropdown() {
  }
  private void initializeLengthDropdown() {
  }

  @Override
  protected void spawnTurtle(int id){
    super.spawnTurtle(id); //replace with actual code if doesn't work
    relocateAndHideTurtle();
  }

  void relocateAndHideTurtle() {
    //set Turtle starting coordinates to top left corner

    //TODO: set this value to the user-specified start of the drawing
    myModelTurtle.setNewX(LSYSTEM_OFFSET_X);
    myModelTurtle.setNewY(LSYSTEM_OFFSET_Y);
//    myModelTurtle.setNewY(OFFSET_Y_TOP + ((Math.abs(BORDER_HEIGHT) - BUFFER)));

//    myModelTurtle.setTurtleCoordinates(new Coordinates(OFFSET_X,OFFSET_Y_TOP+(BUFFER/2), OFFSET_X, OFFSET_Y_TOP+(BUFFER/2)));
    myModelTurtle.updateCoordinates();
    myViewTurtle.update(myModelTurtle);

    //hide turtle by issuing hide turtle command
    executeInstruction(new LogoInstruction("ht"), myTurtleLinkage, root);
    executeInstruction(new LogoInstruction("rt", 90), myTurtleLinkage, root);
  }

  //Create method that passes in queue of commands to Logo
  @Override
  protected void step() {
    checkForInstructionsAndExecute();
  }

  @Override
  protected void handleInputParsing(String text) {
    myGameProcessor.inputParser(numLevels, turnAngle, stepLength, text);
  }

  private void checkForInstructionsAndExecute() {
    //If an instruction has been sent to myGameProcessor, run it
    ArrayList<ArrayList<LogoInstruction>> instructions = myGameProcessor.getConvertedInstructionLevels();
    numLevels = instructions.size();
    int level = 1;

    if (!instructions.isEmpty()) {
      ArrayList<LogoInstruction> currentLevelInstructions = instructions.get(0);
      while (!currentLevelInstructions.isEmpty()) {
        LogoInstruction currentInstruction = currentLevelInstructions.get(
            0); //pop a single instruction, FIFO
        executeInstruction(currentInstruction, myTurtleLinkage, root);
        //TODO: create map (possibly global) ->
        drawTurtleLine();
        myModelTurtle.updateCoordinates();
        currentLevelInstructions.remove(0);
      }
      updateTurtleCoordinatesAndPositioning(level);
      instructions.remove(0);
      level++;
    }
  }

  @Override
  protected void clearSpecificGameDropdowns() {
    //TODO: choose which dropdowns to clear
  }

  private void updateTurtleCoordinatesAndPositioning(int level) {
    myModelTurtle.setNewX(LSYSTEM_OFFSET_X);
//    myModelTurtle.setNewY(LSYSTEM_OFFSET_Y*level);
    myModelTurtle.setNewY(
        LSYSTEM_OFFSET_Y + ((Math.abs(BORDER_HEIGHT) - BUFFER) / numLevels) * level);
//    myModelTurtle.setTurtleCoordinates(new Coordinates(OFFSET_X,OFFSET_Y_TOP+(BUFFER/2), OFFSET_X, OFFSET_Y_TOP+(BUFFER/2)));
    myModelTurtle.updateCoordinates();
    myViewTurtle.update(myModelTurtle);
  }

  @Override
  protected File[] getFilesFromPath() {
    return new File("data/examples/lsystem").listFiles();
  }
}
