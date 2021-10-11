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
   * @param id                sets the ID for the creature being created for the Darwin application
   * @param nearbyThreshold   sets the threshold for the created creature
   * @param speciesIdentifier sets the identity for the creature
   * @param homeX             sets the home x coordinate for the created creature
   * @param homeY             sets the home y coordinate for the created creature
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

  /**
   * Declares the instruction index to track the creature's current instruction
   *
   * @param myNextInstructionIndex
   */
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
