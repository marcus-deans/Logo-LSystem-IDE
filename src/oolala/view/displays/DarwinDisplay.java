package oolala.view.displays;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Paint;
import oolala.model.instructions.CreatureInstruction;
import oolala.model.instructions.Instruction;
import oolala.model.processors.Darwin;
import oolala.view.darwin.CreatureLinkage;

public class DarwinDisplay extends Display {

  private static List<CreatureLinkage> allCreatureLinkages;
  private List<String> allCreatureNames;
  private String mySpeciesIdentifier;
  private ComboBox allCreatures;
  private int myCreatureCount;

  public static List<CreatureLinkage> getAllCreatureLinkages() {
    return allCreatureLinkages;
  }

  @Override
  protected Scene setupGame(int width, int height, Paint background) {
    myGameProcessor = new Darwin();
    allCreatureLinkages = new ArrayList<>();
    performInitialSetup();
    initializeRunButton(getWord("run_text_darwin")); //initialize the program run button
    creaturesTitle(getWord("creatures_text_darwin"));
    initializeCreatureDropdown(); //initialize the dropdown of creatures
    makeScreenClickable();
    //Set the scene
    Scene scene = new Scene(root, width, height, background);
    scene.getStylesheets().add(LogoDisplay.class.getResource("Display.css").toExternalForm());
    return scene;
  }

  private void makeScreenClickable() {
    root.setOnMouseClicked(mouseEvent ->{
      double spawnX = mouseEvent.getX();
      double spawnY = mouseEvent.getY();
      //TODO: spawn new creature at x,y location
      //TODO: add creature to list of creatures, and if its not already in the list of exisitng creatures add it
      //TODO: set an image for the creature
    });
  }

  @Override
  protected void initializeCreatureDropdown() {
    allCreatures = new ComboBox();
    updateCreatureDropdown();
    allCreatures.setOnAction((event) -> {
      mySpeciesIdentifier = allCreatures.getSelectionModel().getSelectedItem().toString();
    });
    allCreatures.setLayoutX(CREATURES_DROPDOWN_X);
    allCreatures.setLayoutY(CREATURES_DROPDOWN_Y);
    allCreatures.setMaxWidth(MAX_DROPDOWN_WIDTH);
    root.getChildren().add(allCreatures);
  }

  private void updateCreatureDropdown() {
    allCreatures.getItems().clear();
    for(String creature: allCreatureNames){
      allCreatures.getItems().add(creature);
    }
  }

  @Override
  protected void handleInputParsing(String text) {
    myGameProcessor.inputParser(4, 4, 4, text);
  }

  //Create method that passes in queue of commands to Logo
  @Override
  protected void step() {
    //TODO: update dropdowns
    updateCreatureDropdown();
    checkForInstructionsAndExecute();
  }

  private void checkForInstructionsAndExecute() {
    allCreatureLinkages = randomizeCreatureOrder();
    for (CreatureLinkage checkCreatureLinkage : allCreatureLinkages) {
      int checkCreatureLinkageSpecies = checkCreatureLinkage.myModelCreature.getMySpeciesIdentifier();
      ArrayList<CreatureInstruction> checkInstructions = myGameProcessor.getMySpeciesInstructions(
          checkCreatureLinkageSpecies);

      while (!checkInstructions.isEmpty()) {
        Instruction currentInstruction = checkInstructions.get(0); //pop a single instruction, FIFO
        //TODO:
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

  private ArrayList<CreatureLinkage> randomizeCreatureOrder() {
    //TODO: get instructions and execute in random order
    Random rand = new Random();
    // create a temporary list for storing
    // selected element
    ArrayList<CreatureLinkage> randomizedCreatureLinkageOrder = new ArrayList<>();
//    ArrayList<ArrayList<CreatureInstruction>> allCreaturesInstructions = new ArrayList<>(); //myGameProcessor.getCreatureList;
    myCreatureCount = allCreatureLinkages.size();
    for (int i = 0; i < myCreatureCount; i++) {
      // take a random index between 0 to size of given List
      int randomIndex = rand.nextInt(allCreatureLinkages.size());

      // add element in temporary list
      randomizedCreatureLinkageOrder.add(allCreatureLinkages.get(randomIndex));

      // Remove selected element from original list
      allCreatureLinkages.remove(randomIndex);
    }
    return randomizedCreatureLinkageOrder;
//    ArrayList<Integer> numericOptions = createNumericOptionsArraylist();
  }

  @Override
  protected File[] getFilesFromPath() {
    return new File("data/examples/darwin").listFiles();
  }
}
