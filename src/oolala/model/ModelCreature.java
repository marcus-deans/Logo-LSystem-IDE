package oolala.model;

import java.util.ArrayList;
import java.util.List;
import oolala.model.instructions.CreatureInstruction;

//For darwin class
public class ModelCreature extends ModelTurtle {

  private List<CreatureInstruction> myCreatureInstructions = new ArrayList<>();
  private final int myNearbyThreshold;
  private int myNextInstructionIndex;
  private int mySpeciesIdentifier;

  public ModelCreature(int id, int nearbyThreshold, int speciesIdentifier) {
    super(id);
    myNearbyThreshold = nearbyThreshold;
    mySpeciesIdentifier = speciesIdentifier;
  }

  public void setMyNextInstructionIndex(int myNextInstructionIndex) {
    this.myNextInstructionIndex = myNextInstructionIndex;
  }

  public int getMySpeciesIdentifier() {
    return mySpeciesIdentifier;
  }

  public void setMySpeciesIdentifier(int mySpeciesIdentifier) {
    this.mySpeciesIdentifier = mySpeciesIdentifier;
  }

  public void addNewCreatureInstruction(CreatureInstruction newCreatureInstruction){
    myCreatureInstructions.add(newCreatureInstruction);
  }

  public int getMyNearbyThreshold() {
    return myNearbyThreshold;
  }
}
