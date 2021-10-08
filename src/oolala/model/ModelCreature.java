package oolala.model;

import java.util.ArrayList;
import java.util.List;

//For darwin class
public class ModelCreature extends ModelTurtle {

  private final List<Instruction> myCreatureInstructions = new ArrayList<>();
  private final int myNearbyThreshold;

  public ModelCreature(int id, int nearbyThreshold) {
    super(id);
    myNearbyThreshold = nearbyThreshold;
  }
}
