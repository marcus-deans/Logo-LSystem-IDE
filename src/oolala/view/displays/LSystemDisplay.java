package oolala.view.displays;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import oolala.model.instructions.Instruction;
import oolala.model.processors.LSystem;

public class LSystemDisplay extends LogoDisplay {

  private static final int BORDER_HEIGHT = OFFSET_Y_TOP - (COMMAND_Y - OFFSET_Y);
  public static final int BUFFER = 200;
  private int numLevels;

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
    myModelTurtle.setNewX(OFFSET_X);
    myModelTurtle.setNewY(OFFSET_Y_TOP);

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
    int level=0;

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
    myModelTurtle.setNewX(OFFSET_X);
    myModelTurtle.setNewY(OFFSET_Y_TOP + ((BORDER_HEIGHT - BUFFER) / numLevels) * level * 2);
//    myModelTurtle.setTurtleCoordinates(new Coordinates(OFFSET_X,OFFSET_Y_TOP+(BUFFER/2), OFFSET_X, OFFSET_Y_TOP+(BUFFER/2)));
    myModelTurtle.updateCoordinates();
    myViewTurtle.update(myModelTurtle);
  }

  @Override
  protected File[] getFilesFromPath() {
    return new File("data/examples/lsystem").listFiles();
  }
}
