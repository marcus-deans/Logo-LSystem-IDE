package oolala.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ModelTurtleTest {

  int myDegreesRotation = 70;
  int myID = 0;

  ModelTurtle myModelTurtle = new ModelTurtle(myID);

  @Test
  void getDegreesRotation() {
  }

  @Test
  void setDegreesRotation() {
    myModelTurtle.setDegreesRotation(myDegreesRotation);
    assertEquals(myDegreesRotation, myModelTurtle.getDegreesRotation());
  }

  @Test
  void getTurtleCoordinates() {
  }

  @Test
  void setNewY() {
    int newY;
  }

  @Test
  void setNewX() {
  }

  @Test
  void setVisualNewY() {
  }

  @Test
  void setVisualNewX() {
  }

  @Test
  void updateCoordinates() {
  }

  @Test
  void getHomeCoordinates() {
  }

  @Test
  void getTurtleVisualCoordinates() {
  }

  @Test
  void correctXCoordinate() {
  }

  @Test
  void correctYCoordinate() {
  }
}