package oolala.model;

import java.util.ArrayList;
import java.util.List;
import oolala.model.instructions.CreatureInstruction;

/**
 * @author Purpose: Extends from ModelTrutle for the Darwin application Assumptions: Functioning ;
 * and other values error-checked Example Usage: used for computeForwardCoordinates() to declare
 * myTurtleOldX User Details: None, usage exactly as dictated by command entry pattern
 * (error-checked)
 */
public class ModelCreature extends ModelTurtle {

  private final List<CreatureInstruction> myCreatureInstructions = new ArrayList<>();
  private final int myNearbyThreshold;
  private int myNextInstructionIndex;
  private int mySpeciesIdentifier;
  private final int myCreatureHomeX;
  private final int myCreatureHomeY;

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
