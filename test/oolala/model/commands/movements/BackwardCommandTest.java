package oolala.model.commands.movements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import oolala.model.Turtle;
import org.junit.jupiter.api.Test;

class BackwardCommandTest {

  Turtle myTurtle = new Turtle(0);

  @Test
  void checkPositiveBackwardMovement() {
    assertEquals(myTurtle.getTurtleCoordinates().turtleNewX, 100);
  }
}