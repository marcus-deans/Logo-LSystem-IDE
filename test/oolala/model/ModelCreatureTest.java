package oolala.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ModelCreatureTest {

  private final int myID = 3;
  private final int myNearbyThreshold = 4;
  private final int mySpeciesIdentifier = 7;
  private final int myHomeX = 40;
  private final int myHomeY = 36;


  ModelCreature myModelCreature = new ModelCreature(myID, myNearbyThreshold, mySpeciesIdentifier,
      myHomeX, myHomeY);

  @Test
  void setMyNextInstructionIndex() {
    int expected = 3;
    myModelCreature.setMyNextInstructionIndex(3);
  }

  @Test
  void getMySpeciesIdentifier() {
    assertEquals(mySpeciesIdentifier, myModelCreature.getMySpeciesIdentifier());
  }

  @Test
  void setMySpeciesIdentifier() {
    int newSpeciesIdentifier = 4;
    myModelCreature.setMySpeciesIdentifier(newSpeciesIdentifier);
    assertEquals(newSpeciesIdentifier, myModelCreature.getMySpeciesIdentifier());
  }

  @Test
  void getMyNearbyThreshold() {
    assertEquals(myModelCreature.getMyNearbyThreshold(), myNearbyThreshold);
  }
}