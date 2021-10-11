package oolala.view.darwin;

import oolala.model.ModelCreature;
import oolala.view.TurtleLinkage;

public class CreatureLinkage extends TurtleLinkage {

  private final int myNearbyThreshold;
  private final int mySpeciesIdentifier;

  public CreatureLinkage(int id, int nearbyThreshold, int speciesIdentifier, int homeX, int homeY) {
    myID = id;
    myNearbyThreshold = nearbyThreshold;
    mySpeciesIdentifier = speciesIdentifier;
    myModelTurtle = new ModelCreature(id, nearbyThreshold, speciesIdentifier, homeX, homeY);
    myViewTurtle = new ViewCreature(id);
  }
}
