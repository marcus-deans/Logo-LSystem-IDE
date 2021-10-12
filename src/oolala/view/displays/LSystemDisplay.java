package oolala.view.displays;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import oolala.model.instructions.Instruction;
import oolala.model.processors.LSystem;

public class LSystemDisplay extends LogoDisplay {

  private static final int BORDER_HEIGHT = OFFSET_Y_TOP - (COMMAND_Y - OFFSET_Y);
  public static final int BUFFER = 50;
  private int numLevels;

  private final int LSYSTEM_OFFSET_X = 300;
  private final int LSYSTEM_OFFSET_Y = 100;

  @Override
  protected Scene setupGame(int width, int height, Paint background) {
    //Initialize the view classes
    myGameProcessor = new LSystem();
    spawnTurtle(0);
    performInitialSetup();
    //Set the scene
    Scene scene = new Scene(root, width, height, background);
    scene.getStylesheets().add(LogoDisplay.class.getResource("Display.css").toExternalForm());
    return scene;
  }

  @Override
  protected void spawnTurtle(int id){
    super.spawnTurtle(id); //replace with actual code if doesn't work
    relocateAndHideTurtle();
  }

  void relocateAndHideTurtle() {
    //set Turtle starting coordinates to top left corner
    myModelTurtle.setNewX(LSYSTEM_OFFSET_X);
    myModelTurtle.setNewY(LSYSTEM_OFFSET_Y);
//    myModelTurtle.setNewY(OFFSET_Y_TOP + ((Math.abs(BORDER_HEIGHT) - BUFFER)));

//    myModelTurtle.setTurtleCoordinates(new Coordinates(OFFSET_X,OFFSET_Y_TOP+(BUFFER/2), OFFSET_X, OFFSET_Y_TOP+(BUFFER/2)));
    myModelTurtle.updateCoordinates();
    myViewTurtle.update(myModelTurtle);

    //hide turtle by issuing hide turtle command
//    executeInstruction(new Instruction("ht"), myTurtleLinkage, root);
    executeInstruction(new Instruction("rt", 90), myTurtleLinkage, root);
  }

  //Create method that passes in queue of commands to Logo
  @Override
  protected void step() {
    checkForInstructionsAndExecute();
  }

  @Override
  protected void handleInputParsing(String text) {
    myGameProcessor.inputParser(4, 60, 20, text);
  }

  private void checkForInstructionsAndExecute() {
    //If an instruction has been sent to myGameProcessor, run it
    ArrayList<ArrayList<Instruction>> instructions = myGameProcessor.getConvertedInstructionLevels();
    numLevels = instructions.size();
    int level = 1;

    if (!instructions.isEmpty()) {
      ArrayList<Instruction> currentLevelInstructions = instructions.get(0);
      while (!currentLevelInstructions.isEmpty()) {
        Instruction currentInstruction = currentLevelInstructions.get(0); //pop a single instruction, FIFO
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

  private void updateTurtleCoordinatesAndPositioning(int level) {
    myModelTurtle.setNewX(LSYSTEM_OFFSET_X);
//    myModelTurtle.setNewY(LSYSTEM_OFFSET_Y*level);
    myModelTurtle.setNewY(
        LSYSTEM_OFFSET_Y + ((Math.abs(BORDER_HEIGHT) - BUFFER) / numLevels) * level);
//    myModelTurtle.setTurtleCoordinates(new Coordinates(OFFSET_X,OFFSET_Y_TOP+(BUFFER/2), OFFSET_X, OFFSET_Y_TOP+(BUFFER/2)));
    myModelTurtle.updateCoordinates();
    myViewTurtle.update(myModelTurtle);
  }

//  private void drawTurtleLine() {
//    Coordinates turtleCoordinates = myTurtleLinkage.myModelTurtle.getTurtleVisualCoordinates();
//    Line connector = new Line(turtleCoordinates.turtleOldX, turtleCoordinates.turtleOldY,
//        turtleCoordinates.turtleNewX, turtleCoordinates.turtleNewY);
//    connector.setOpacity(1.0);
//    connector.setStrokeWidth(3.0);
////    connector.setOpacity(myTurtleLinkage.myViewTurtle.getPenOpacity());
////    connector.setId("turtle-line");
//    root.getChildren().add(connector);
//  }

  @Override
  protected File[] getFilesFromPath() {
    return new File("data/examples/lsystem").listFiles();
  }
}
