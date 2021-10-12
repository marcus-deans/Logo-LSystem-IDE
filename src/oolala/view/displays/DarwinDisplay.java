package oolala.view.displays;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Paint;
import oolala.model.instructions.CreatureInstruction;
import oolala.model.instructions.Instruction;
import oolala.model.processors.Darwin;
import oolala.view.darwin.CreatureLinkage;

public class DarwinDisplay extends Display {

  private static ArrayList<CreatureLinkage> allCreatureLinkages;
  private List<String> allCreatureNames;
  private String mySpeciesIdentifier;
  private ComboBox allCreatures;

  private int myCreatureCount;
  private int myNearbyThreshold;

  public static ArrayList<CreatureLinkage> getAllCreatureLinkages() {
    return allCreatureLinkages;
  }

  @Override
  protected Scene setupGame(int width, int height, Paint background) {
    myGameProcessor = new Darwin();
    allCreatureLinkages = new ArrayList<>();
    allCreatureNames = new ArrayList<>();
    myCreatureCount = 0;
    myNearbyThreshold = 0;
    performInitialSetup(); //run in general display class
    initializeRunButton(getWord("run_text_darwin")); //initialize the program run button
    creaturesTitle(getWord("creatures_text_darwin"));
    initializeCreatureDropdown(); //initialize the dropdown of creatures
    makeScreenClickable();
    //Set the scene
    Scene scene = new Scene(root, width, height, background);
    scene.getStylesheets().add(LogoDisplay.class.getResource("Display.css").toExternalForm());
    return scene;
  }

  @Override
  protected void initializeRunButton(String runTitle) {
    String creatureName = getUserCreatureName();
    Button runCommands = new Button(runTitle);
    runCommands.setPrefWidth(RUN_WIDTH);
    runCommands.setPrefHeight(RUN_HEIGHT);
    runCommands.setLayoutX(RUN_X);
    runCommands.setLayoutY(RUN_Y);
    root.getChildren().add(runCommands);
    runCommands.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        handleInputParsing(commandLine.getText());
        validateCommandStream();
        myGameProcessor.saveHistory(commandLine.getText());
        updateHistoryDropdown();
        updateCreatureDropdown();
      }
    });
  }

  private String getUserCreatureName() {
    //TODO: popup to get user's name for this new creature
    return "";
  }

  @Override
  protected void clearSpecificGameDropdowns() {
    //TODO: choose which dropdowns to clear
  }

  private void makeScreenClickable() {
    root.setOnMouseClicked(mouseEvent -> {
      double spawnX = mouseEvent.getX();
      double spawnY = mouseEvent.getY();
      myCreatureCount++;
      spawnCreature(spawnX, spawnY, myCreatureCount);
      //TODO: spawn new creature at x,y location
      //TODO: add creature to list of creatures, and if its not already in the list of exisitng creatures add it
      //TODO: set an image for the creature
    });
  }

  private void spawnCreature(double spawnX, double spawnY, int creatureCount) {
    int speciesIdentifier = 0;
    CreatureLinkage createdCreatureLinkage = new CreatureLinkage(creatureCount, myNearbyThreshold,
        speciesIdentifier, (int) spawnX, (int) spawnY);
    allCreatureLinkages.add(createdCreatureLinkage);
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
    for (String creature : allCreatureNames) {
      allCreatures.getItems().add(creature);
    }
  }

  @Override
  protected void handleInputParsing(String text) {
    //TODO: pass in species identifier somehow
    myGameProcessor.inputParser(4, 4, 4, text);
  }

  //Create method that passes in queue of commands to Logo
  @Override
  protected void step() {
    updateCreatureDropdown(); //TODO: make sure step is updating all dropdowns
    checkForInstructionsAndExecute();
  }

  private void checkForInstructionsAndExecute() {
    allCreatureLinkages = randomizeCreatureOrder();
    for (CreatureLinkage checkCreatureLinkage : allCreatureLinkages) {
      int checkCreatureLinkageSpecies = checkCreatureLinkage.myModelCreature.getMySpeciesIdentifier();

      ArrayList<CreatureInstruction> instructionsToExecute = myGameProcessor.getMySpeciesInstructions(
          checkCreatureLinkageSpecies);

      while (!instructionsToExecute.isEmpty()) {
        Instruction currentInstruction = instructionsToExecute.get(
            0); //pop a single instruction, FIFO
        //TODO:
//        CreatureLinkage myCreatureLinkage = new CreatureLinkage(0, 0, 0, 10, 10);
        executeInstruction(currentInstruction, checkCreatureLinkage, root);
        //TODO: associate each creature with its map
        // drawTurtleLine();
        // myModelTurtle.updateCoordinates(0;
        instructionsToExecute.remove(0);
      }
//      instructionsToExecute.remove(0);
    }
//    //TODO: execute all instructions
  }

  private ArrayList<CreatureLinkage> randomizeCreatureOrder() {
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
