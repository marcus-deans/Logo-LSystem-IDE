package oolala.view.darwin;

import oolala.model.ModelCreature;
import oolala.view.TurtleLinkage;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Create an associative construct betwen the ViewCreature and ModelCreature for same
 * creature Assumptions: Functioning coordinate system and commmands Dependencies: ModelCreature,
 * ViewCreature Example Usage: Used in Darwin programs to create each creature that moves on the
 * screen Details: None, usage exactly as dictated by command entry pattern (error-checked)
 */
public class CreatureLinkage extends TurtleLinkage {

  private final int myNearbyThreshold;
  private final int mySpeciesIdentifier;

  public ModelCreature myModelCreature;
  public ViewCreature myViewCreature;

  /**
   * Create new creature linkage
   *
   * @param id                unique identifier for linkage
   * @param nearbyThreshold   radius threshold for another creature to be deemed 'nearby'
   * @param speciesIdentifier unique identifier of the species
   * @param homeX             starting x position of creature
   * @param homeY             starting y position of creature
   */
  public CreatureLinkage(int id, int nearbyThreshold, int speciesIdentifier, int homeX, int homeY) {
    myID = id;
    myNearbyThreshold = nearbyThreshold;
    mySpeciesIdentifier = speciesIdentifier;
    myModelCreature = new ModelCreature(id, nearbyThreshold, speciesIdentifier, homeX, homeY);
    myModelTurtle = myModelCreature;
    myViewCreature = new ViewCreature(id);
    myViewTurtle = myViewCreature;
  }
}
