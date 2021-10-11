package oolala.view.displays;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import oolala.model.ModelCreature;
import oolala.model.instructions.CreatureInstruction;
import oolala.model.processors.Darwin;

public class DarwinDisplay extends Display {

  private static ArrayList<ModelCreature> allModelCreatures;

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
    LinkedList<ArrayList<CreatureInstruction>> creatureExecutionOrderList = randomizeCreatureOrder();
    //TODO: execute all instructions
  }

  private LinkedList<ArrayList<CreatureInstruction>> randomizeCreatureOrder() {
    //TODO: get instructions and execute in random order
    Random rand = new Random();
    // create a temporary list for storing
    // selected element
    LinkedList<ArrayList<CreatureInstruction>> randomizedOptionOrder = new ArrayList<>();
    ArrayList<ArrayList<CreatureInstruction>> allCreaturesInstructions = myGameProcessor.getCreatureList;
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

//  private ArrayList<Integer> createNumericOptionsArraylist (ArrayList<Integer> list)


  @Override
  protected File[] getFilesFromPath() {
    return new File("data/examples/darwin").listFiles();
  }

}
