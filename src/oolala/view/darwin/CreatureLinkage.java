package oolala.view.darwin;

import oolala.model.ModelCreature;
import oolala.view.TurtleLinkage;

public class CreatureLinkage extends TurtleLinkage {

  private final int myNearbyThreshold;
  private final int mySpeciesIdentifier;

  public ModelCreature myModelCreature;
  public ViewCreature myViewCreature;

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
