package oolala.model.commands.rotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import oolala.model.ModelTurtle;
import oolala.model.commands.movements.ForwardModelCommand;
import oolala.view.TurtleLinkage;
import org.junit.jupiter.api.Test;

class RotateModelCommandTest {

  ModelTurtle myModelTurtle = new ModelTurtle(0);
  TurtleLinkage turtLink = new TurtleLinkage(0);

  @Test
  void setRotationLeft() {
    RotateLeftModelCommand rlc = new RotateLeftModelCommand(myModelTurtle, 75);
    rlc.setLeftRotation();
    assertEquals(myModelTurtle.getDegreesRotation(), -75);
  }

  @Test
  void setRotationRight() {
    RotateRightModelCommand rrc = new RotateRightModelCommand(myModelTurtle, 200);
    rrc.setRightRotation();
    assertEquals(myModelTurtle.getDegreesRotation(), 200);
  }

  @Test
  void checkLeftRotationFacingRightT() {
    myModelTurtle.setDegreesRotation(80);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertTrue(fc.rightFacing()); //made public for testing purposes
  }

  @Test
  void checkLeftRotationFacingRightF() {
    myModelTurtle.setDegreesRotation(190);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertFalse(fc.rightFacing()); //made public for testing purposes
  }

  @Test
  void checkLeftRotationFacingUpT() {
    myModelTurtle.setDegreesRotation(10);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertTrue(fc.upwardFacing()); //made public for testing purposes
  }

  @Test
  void checkLeftRotationFacingUpF() {
    myModelTurtle.setDegreesRotation(100);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertFalse(fc.upwardFacing()); //made public for testing purposes
  }

}