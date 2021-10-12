package oolala.view.displays;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
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

  //Turtles
  protected final List<TurtleLinkage> allTurtleLinkages = new ArrayList<>();
  protected final List<ModelTurtle> allModelTurtles = new ArrayList<>();
  protected final List<ViewTurtle> allViewTurtles = new ArrayList<>();

  protected TurtleLinkage myTurtleLinkage;
  protected ModelTurtle myModelTurtle;
  protected ViewTurtle myViewTurtle;

  private ComboBox creatureDropdown;

  @Override
  protected Scene setupGame(int width, int height, Paint background) {
    //Initialize the view classes
    performInitialSetup(); //setup everything common between all 3 displays
    myGameProcessor = new Logo();
    spawnTurtle(0);
    initializeCreatureDropdown(); //dropdown of all turtles and current running turtle
    creaturesTitle(getWord("creatures_text_logo"));
    //Set the scene
    Scene scene = new Scene(root, width, height, background);
    scene.getStylesheets().add(LogoDisplay.class.getResource("Display.css").toExternalForm());
    return scene;
  }

  @Override
  protected File[] getFilesFromPath() {
      return new File("data/examples/logo").listFiles();
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
    creaturesTitle(getWord(""));
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

  @Override
  protected void handleInputParsing(String text) {
    myGameProcessor.inputParser(0, 0, 0, text);
  }

  @Override
  protected void initializeCreatureDropdown() {
    creatureDropdown = new ComboBox();
    creatureDropdown.setOnAction((event) -> {
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
    //TODO: add all the existing turtles to the dropdown
    creatureDropdown.setLayoutX(CREATURES_DROPDOWN_X);
    creatureDropdown.setLayoutY(CREATURES_DROPDOWN_Y);
    creatureDropdown.setMaxWidth(MAX_DROPDOWN_WIDTH);
    root.getChildren().add(creatureDropdown);
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
        creatureDropdown.setValue(
            turtleLinkage.myID); //TODO: make sure this works, java being laggy
        creatureDropdown.setValue(
            turtleLinkage.myID); //TODO: make sure this works, java being laggy
      }
    }
    if (!exists) {//id doesn't exist - create new turtle with this ID
//        myTurtle = new Turtle(turtleHomeX,turtleHomeY,id);
//      myModelTurtle = new ModelTurtle(id);
      spawnTurtle(id);
      creatureDropdown.getItems().add(myTurtleLinkage.myID);
      creatureDropdown.setValue(
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
    //TODO: update dropdowns
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