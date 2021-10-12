package oolala.model.commands.rotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import oolala.model.ModelTurtle;
import oolala.view.TurtleLinkage;
import org.junit.jupiter.api.Test;

class RotateLeftCommandTest {

  ModelTurtle myModelTurtle = new ModelTurtle(0);

  @Test
  void computeDegreesRotationLeftPositive() {
    RotateLeftModelCommand rlc = new RotateLeftModelCommand(myModelTurtle, 50);
    assertEquals(myModelTurtle.getDegreesRotation(), 310);
  }

  @Test
  void computeDegreesRotationLeftNegative() {
    RotateLeftModelCommand rlc = new RotateLeftModelCommand(myModelTurtle, -75);
    assertEquals(myModelTurtle.getDegreesRotation(), 75);
    }

}