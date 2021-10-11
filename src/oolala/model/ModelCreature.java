package oolala.model;

import java.util.ArrayList;
import java.util.List;
import oolala.model.instructions.CreatureInstruction;

/**
 * @author Purpose: Extends from ModelTurtle for the Darwin application Assumptions: Utilizes
 * functioning creature x and y locations; nearby threshold and species identifier; other values
 * error checked Example Usage: used to create specific creatures for the Darwin application
 */
public class ModelCreature extends ModelTurtle {

  private final List<CreatureInstruction> myCreatureInstructions = new ArrayList<>();
  private final int myNearbyThreshold;
  private int myNextInstructionIndex;
  private int mySpeciesIdentifier;
  private final int myCreatureHomeX;
  private final int myCreatureHomeY;

  /**
   * @param id
   * @param nearbyThreshold
   * @param speciesIdentifier
   * @param homeX
   * @param homeY
   */
  public ModelCreature(int id, int nearbyThreshold, int speciesIdentifier, int homeX, int homeY) {
    super(id);
    myCreatureHomeX = homeX;
    myCreatureHomeY = homeY;
    myNearbyThreshold = nearbyThreshold;
    mySpeciesIdentifier = speciesIdentifier;
    visualOldX = correctXCoordinate(myCreatureHomeX);
    visualOldY = correctYCoordinate(myCreatureHomeY);
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
