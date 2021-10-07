package oolala.model.commands.movements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import oolala.model.ModelTurtle;
import org.junit.jupiter.api.Test;

class BackwardModelCommandTest {

  ModelTurtle myModelTurtle = new ModelTurtle(0);

  @Test
  void checkPositiveBackwardMovement() {
    assertEquals(myModelTurtle.getTurtleCoordinates().turtleNewX, 100);
  }
}