package oolala.model.commands.rotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import oolala.model.ModelTurtle;
import oolala.view.TurtleLinkage;
import org.junit.jupiter.api.Test;

class RotateLeftCommandTest {

  ModelTurtle myModelTurtle = new ModelTurtle(0);
  TurtleLinkage turtLink = new TurtleLinkage(0);

  @Test
  void computeDegreesRotationLeftPositive() {
    RotateLeftModelCommand rlc = new RotateLeftModelCommand(turtLink, 50);
    rlc.setLeftRotation();
    assertEquals(myModelTurtle.getDegreesRotation(), -100);
  }

  @Test
  void computeDegreesRotationLeftNegative() {
    RotateLeftModelCommand rlc = new RotateLeftModelCommand(turtLink, -75);
        rlc.setLeftRotation();
        assertEquals(myModelTurtle.getDegreesRotation(), 150);
    }

}