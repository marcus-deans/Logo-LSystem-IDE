package oolala.view.displays;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import oolala.model.Coordinates;
import oolala.model.ModelTurtle;
import oolala.model.commands.Commands;
import oolala.model.instructions.Instruction;
import oolala.model.processors.InstructionProcessor;
import oolala.model.processors.Logo;
import oolala.view.TurtleLinkage;
import oolala.view.ViewTurtle;


/**
 * JavaFX View class
 */
public class LogoDisplay extends Display {

  //  public static final String TITLE = R.string.program_name;
  public static final String TITLE = "LogoDisplay";
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
  public static final int SAVED_TITLE_X = 160; //210
  public static final int SAVED_TITLE_Y = 17;
  public static final int SAVED_DROPDOWN_X = 260; //320
  public static final int SAVED_DROPDOWN_Y = 0;
  public static final int HISTORY_TITLE_X = 325; //445
  public static final int HISTORY_TITLE_Y = 17;
  public static final int HISTORY_DROPDOWN_X = 375; //495
  public static final int HISTORY_DROPDOWN_Y = 0;
  public static final int LANGUAGES_TITLE_X = 440;
  public static final int LANGUAGES_TITLE_Y = 17;
  public static final int LANGUAGES_DROPDOWN_X = 520;
  public static final int LANGUAGES_DROPDOWN_Y = 0;
  public static final int TURTLES_TITLE_X = 620;
  public static final int TURTLES_TITLE_Y = 17;
  public static final int TURTLES_DROPDOWN_X = 680;
  public static final int TURTLES_DROPDOWN_Y = 0;
  public static final int MAX_DROPDOWN_WIDTH = 50;
  public static final int OFFSET_X = 10;
  public static final int OFFSET_Y = 15;
  public static final int OFFSET_Y_TOP = 26;

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

  //Turtles
  protected final List<TurtleLinkage> allTurtleLinkages = new ArrayList<>();
  protected final List<ModelTurtle> allModelTurtles = new ArrayList<>();
  protected final List<ViewTurtle> allViewTurtles = new ArrayList<>();

  protected TurtleLinkage myTurtleLinkage;
  protected ModelTurtle myModelTurtle;
  protected ViewTurtle myViewTurtle;

  private ComboBox turtleDropdown;

  @Override
  protected Scene setupGame(int width, int height, Paint background) {
    //Initialize the view classes
    myGameProcessor = new Logo();
    spawnTurtle(0);
    performInitialSetup();
    initializeTurtleOptions(); //dropdown of all turtles and current running turtle
//    initializeCommandLine(); //initialize the command line
    initializeRunButton(); //initialize the program run button
//    initializeClearScreen();
//    initializeBoundaries(); // sets up program boundaries for where the turtle will move
//    initializeSaveButton(); //initializes the program save button
//    gameTitle();
//    initializeGameSetting(); //game type dropdown
//    savedTitle();
//    initializeSavedPrograms(); //saved programs dropdown
//    historyTitle();
//    initializeHistory(); //program history dropdown
//    languagesTitle();
//    initializeLanguages();
    turtleTitle();
    //Set the scene
    Scene scene = new Scene(root, width, height, background);
    scene.getStylesheets().add(LogoDisplay.class.getResource("Display.css").toExternalForm());
    return scene;
  }

  protected void spawnTurtle(int id) {
    myTurtleLinkage = new TurtleLinkage(id);

    myModelTurtle = myTurtleLinkage.myModelTurtle;
    myViewTurtle = myTurtleLinkage.myViewTurtle;

    allTurtleLinkages.add(myTurtleLinkage);
    allModelTurtles.add(myModelTurtle);
    allViewTurtles.add(myViewTurtle);

    root.getChildren().add(myViewTurtle.getMyTurtleView());
  }

  @Override
  protected void updateLanguage() {
    clearText();
    gameTitle();
    savedTitle();
    historyTitle();
    languagesTitle();
    turtleTitle();
    runTitle();
  }


  @Override
  protected void populateFileNames() {
    File[] files = new File("data/examples/logo").listFiles();
    for (File file : files) {
      if (file.isFile()) {
        savedPrograms.getItems().add(file.getName());
      }
    }
  }

  private void turtleTitle() {
    turtles = new Text(getWord("turtles_text"));
    turtles.setLayoutX(TURTLES_TITLE_X);
    turtles.setLayoutY(TURTLES_TITLE_Y);
    root.getChildren().add(turtles);
  }

  private void initializeTurtleOptions() {
    turtleDropdown = new ComboBox();
    turtleDropdown.setOnAction((event) -> {
      //TODO: switch to this turtle
      int id = Integer.parseInt(historyPrograms.getSelectionModel().getSelectedItem().toString());
//      for (ModelTurtle modelTurtle : allModelTurtles) {
//        if (modelTurtle.myTurtleId == id) {
//          myModelTurtle = modelTurtle;
//        }
//      }
      for (TurtleLinkage turtleLinkage : allTurtleLinkages) {
        if (turtleLinkage.myID == id) {
          switchTurtleLinkage(turtleLinkage);
        }
      }
      //TODO: clear previous lines?
    });
    turtleDropdown.setLayoutX(TURTLES_DROPDOWN_X);
    turtleDropdown.setLayoutY(TURTLES_DROPDOWN_Y);
    turtleDropdown.setMaxWidth(MAX_DROPDOWN_WIDTH);
    root.getChildren().add(turtleDropdown);
  }

  //  @Override
  protected void initializeRunButton() {
    Button runCommands = new Button(runTitle());
    runCommands.setPrefWidth(RUN_WIDTH);
    runCommands.setPrefHeight(RUN_HEIGHT);
    runCommands.setLayoutX(RUN_X);
    runCommands.setLayoutY(RUN_Y);
    root.getChildren().add(runCommands);
    runCommands.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        myGameProcessor.inputParser(0, 0, 0, commandLine.getText());
        validateCommandStream();
        myGameProcessor.saveHistory(commandLine.getText());
        updateHistoryDropdown();
      }
    });
  }


  private void tellTurtle(int id) {
    //loop through allTurtles - if ID exists, switch to this turtle
    boolean exists = false;
//    for (ModelTurtle modelTurtle : allModelTurtles) {
//      if (modelTurtle.myTurtleId == id) {
//        myModelTurtle = modelTurtle; //switch current turtle to this turtle
//        exists = true;
//        turtleDropdown.setValue(
//            modelTurtle.myTurtleId); //TODO: make sure this works, java being laggy
//        turtleDropdown.setValue(
//            modelTurtle.myTurtleId); //TODO: make sure this works, java being laggy
//      }
//    }
//    if (!exists) {//id doesn't exist - create new turtle with this ID
////        myTurtle = new Turtle(turtleHomeX,turtleHomeY,id);
//      myModelTurtle = new ModelTurtle(id);
//      allModelTurtles.add(myModelTurtle);
//      turtleDropdown.getItems().add(myModelTurtle.myTurtleId);
//      turtleDropdown.setValue(
//          myModelTurtle.myTurtleId); //TODO: make sure this works, java being laggy
//      //TODO: inform user that a new turtle has been spawned
//    }
    for (TurtleLinkage turtleLinkage : allTurtleLinkages) {
      if (turtleLinkage.myID == id) {
        switchTurtleLinkage(turtleLinkage);
        exists = true;
        turtleDropdown.setValue(
            turtleLinkage.myID); //TODO: make sure this works, java being laggy
        turtleDropdown.setValue(
            turtleLinkage.myID); //TODO: make sure this works, java being laggy
      }
    }
    if (!exists) {//id doesn't exist - create new turtle with this ID
//        myTurtle = new Turtle(turtleHomeX,turtleHomeY,id);
//      myModelTurtle = new ModelTurtle(id);
      spawnTurtle(id);
      turtleDropdown.getItems().add(myTurtleLinkage.myID);
      turtleDropdown.setValue(
          myTurtleLinkage.myID); //TODO: make sure this works, java being laggy
      //TODO: inform user that a new turtle has been spawned
    }
  }

  private void switchTurtleLinkage(TurtleLinkage turtleLinkage) {
    myTurtleLinkage = turtleLinkage;
    myModelTurtle = turtleLinkage.myModelTurtle;
    myViewTurtle = turtleLinkage.myViewTurtle;
  }

  //Create method that passes in queue of commands to Logo
  @Override
  protected void step() {
    checkForInstructionsAndExecute();
  }

  private void checkForInstructionsAndExecute() {
    //If an instruction has been sent to myLogo, run it
    LinkedList<Instruction> instructions = myGameProcessor.getMyInstructions();
    if (!instructions.isEmpty()) {
      Instruction currentInstruction = instructions.poll(); //pop a single instruction, FIFO
      executeInstruction(currentInstruction, myTurtleLinkage, root);
      //TODO: create map (possibly global) ->
      drawTurtleLine();
//      myGameProcessor.updateMyInstructions();
      myModelTurtle.updateCoordinates();
    }
  }

  @Override
  protected void executeInstruction(Instruction currentInstruction, TurtleLinkage turtleLinkage,
      Group root) {
    if (currentInstruction.order == Commands.TELL) {
      tellTurtle(turtleLinkage.myID);
    }
    InstructionProcessor instructionProcessor = new InstructionProcessor(currentInstruction,
        turtleLinkage, root);
  }


  protected void drawTurtleLine() {
    Coordinates turtleCoordinates = myTurtleLinkage.myModelTurtle.getTurtleVisualCoordinates();
    Line connector = new Line(turtleCoordinates.turtleOldX, turtleCoordinates.turtleOldY,
        turtleCoordinates.turtleNewX, turtleCoordinates.turtleNewY);
    connector.setOpacity(myTurtleLinkage.myViewTurtle.getPenOpacity());
    connector.setId("turtle-line");
    root.getChildren().add(connector);
  }

}