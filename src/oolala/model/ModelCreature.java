package oolala.model;

import java.util.ArrayList;
import java.util.List;

//For darwin class
public class ModelCreature extends ModelTurtle {

  private final List<Instruction> myCreatureInstructions = new ArrayList<>();
  private final int myNearbyThreshold;
  private int myNextInstructionIndex;
  private final int mySpeciesIdentifier;

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
}
