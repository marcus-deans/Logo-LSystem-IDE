package oolala.view.displays;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import oolala.model.ModelCreature;
import oolala.model.instructions.CreatureInstruction;
import oolala.model.instructions.Instruction;
import oolala.model.processors.Darwin;
import oolala.view.darwin.CreatureLinkage;

public class DarwinDisplay extends Display {

  private static ArrayList<ModelCreature> allModelCreatures;
  private int myCreatureCount;

  @Override
  protected Scene setupGame(int width, int height, Paint background) {
    myGameProcessor = new Darwin();
    allModelCreatures = new ArrayList<>();
    performInitialSetup();
    initializeRunButton(); //initialize the program run button
    //Set the scene
    Scene scene = new Scene(root, width, height, background);
    scene.getStylesheets().add(LogoDisplay.class.getResource("Display.css").toExternalForm());
    return scene;
  }


  private void initializeCreatureDropdown() {
    allCreatures = new ComboBox();
    allCreatures.setOnAction((event) -> {

    });
    allCreatures.setLayoutX(CREATURES_DROPDOWN_X);
    allCreatures.setLayoutY(CREATURES_DROPDOWN_Y);
    allCreatures.setMaxWidth(MAX_DROPDOWN_WIDTH);
    root.getChildren().add(allCreatures);

  }

  public static ArrayList<ModelCreature> getAllModelCreatures() {
    return allModelCreatures;
  }

  @Override
  protected void handleInputParsing(String text) {
    myGameProcessor.inputParser(4, 4, 4, text);
  }

  //Create method that passes in queue of commands to Logo
  @Override
  protected void step() {
    checkForInstructionsAndExecute();
  }

  private void checkForInstructionsAndExecute() {
    ArrayList<ArrayList<CreatureInstruction>> creatureExecutionOrderList = randomizeCreatureOrder();
    while (!creatureExecutionOrderList.isEmpty()) {
      ArrayList<CreatureInstruction> currentCreatureInstructions = creatureExecutionOrderList.get(
          0);
      while (!currentCreatureInstructions.isEmpty()) {
        Instruction currentInstruction = currentCreatureInstructions.get(
            0); //pop a single instruction, FIFO
        CreatureLinkage myCreatureLinkage = new CreatureLinkage(0, 0, 0, 10, 10);
        executeInstruction(currentInstruction, myCreatureLinkage, root);
        //TODO: associate each creature with its map
        // drawTurtleLine();
        // myModelTurtle.updateCoordinates(0;
        currentCreatureInstructions.remove(0);
      }
      creatureExecutionOrderList.remove(0);
    }
    //TODO: execute all instructions
  }

  private ArrayList<ArrayList<CreatureInstruction>> randomizeCreatureOrder() {
    //TODO: get instructions and execute in random order
    Random rand = new Random();
    // create a temporary list for storing
    // selected element
    ArrayList<ArrayList<CreatureInstruction>> randomizedOptionOrder = new ArrayList<>();
    ArrayList<ArrayList<CreatureInstruction>> allCreaturesInstructions = new ArrayList<>(); //myGameProcessor.getCreatureList;
    myCreatureCount = allCreaturesInstructions.size();
    for (int i = 0; i < allCreaturesInstructions.size(); i++) {

      // take a random index between 0 to size
      // of given List
      int randomIndex = rand.nextInt(allCreaturesInstructions.size());

      // add element in temporary list
      randomizedOptionOrder.add(allCreaturesInstructions.get(randomIndex));

      // Remove selected element from original list
      allCreaturesInstructions.remove(randomIndex);
    }
    return randomizedOptionOrder;
//    ArrayList<Integer> numericOptions = createNumericOptionsArraylist();
  }

  @Override
  protected File[] getFilesFromPath() {
    return new File("data/examples/darwin").listFiles();
  }
}
