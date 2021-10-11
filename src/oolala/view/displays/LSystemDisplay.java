package oolala.view.displays;

import java.io.File;

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import oolala.model.instructions.Instruction;
import oolala.model.processors.LSystem;

public class LSystemDisplay extends LogoDisplay {

  @Override
  protected Scene setupGame(int width, int height, Paint background) {
    //Initialize the view classes
    myGameProcessor = new LSystem();
    spawnTurtle(0);
    performInitialSetup();
    initializeRunButton(); //initialize the program run button
    //Set the scene
    Scene scene = new Scene(root, width, height, background);
    scene.getStylesheets().add(LogoDisplay.class.getResource("Display.css").toExternalForm());
    return scene;
  }

  //Create method that passes in queue of commands to Logo
  @Override
  protected void step() {
    checkForInstructionsAndExecute();
  }

  private void checkForInstructionsAndExecute() {
    //If an instruction has been sent to myLogo, run it
    ArrayList<ArrayList<Instruction>> instructions = myGameProcessor.getConvertedInstructionLevels();
    if (!instructions.isEmpty()) {
      ArrayList<Instruction> currentLevelInstructions = instructions.poll();
      while (!currentLevelInstructions.isEmpty()) {
        Instruction currentInstruction = currentLevelInstructions.poll(); //pop a single instruction, FIFO
        executeInstruction(currentInstruction, myTurtleLinkage, root);
        //TODO: create map (possibly global) ->
        drawTurtleLine();
//      myGameProcessor.updateMyInstructions();
        myModelTurtle.updateCoordinates();
      }
      //TODO: add several lines of spacing to the screen -> update ModelTurtle's coordinates
      //TODO: use TurtleLinkage to tell ViewTurtle to update its coordinates and location
    }
  }

  @Override
  protected void handleInputParsing(String text) {

  }

  @Override
  protected File[] getFilesFromPath() {
    return new File("data/examples/lsystem").listFiles();
  }
}
