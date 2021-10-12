package oolala.model.commands.movements;

import static oolala.view.displays.LogoDisplay.COMMAND_HEIGHT;
import static oolala.view.displays.LogoDisplay.FRAME_HEIGHT;
import static oolala.view.displays.LogoDisplay.FRAME_WIDTH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import oolala.model.ModelTurtle;
import org.junit.jupiter.api.Test;

class MovementModelCommandTest {

  private static final int TESTING_PIXELS = 100;

  private static final int OFFSET_Y = 15;
  private static final int OFFSET_Y_TOP = 26;

  private static final int TurtleHomeX = (FRAME_WIDTH / 2);
  private static final int TurtleHomeY = ((FRAME_HEIGHT - OFFSET_Y_TOP - COMMAND_HEIGHT + OFFSET_Y) / 2);

  ModelTurtle myModelTurtle = new ModelTurtle(0);

  @Test
  void getTurtleCoordinatesT() {
    myModelTurtle.setNewX(15);
    myModelTurtle.setNewY(15);
    assertEquals(myModelTurtle.getTurtleCoordinates().turtleNewX, 15);
    assertEquals(myModelTurtle.getTurtleCoordinates().turtleNewY, 15);
  }

  @Test
  void getTurtleCoordinatesMoving100Pixels() {
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertEquals(TurtleHomeX, fc.myNewX);
    assertEquals(TurtleHomeY-100, fc.myNewY);
  }

  @Test
  void rightFacingAt45Degrees() {
    myModelTurtle.setDegreesRotation(45);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertTrue(fc.rightFacing());
  }

  @Test
  void leftFacingAt270Degrees() {
    myModelTurtle.setDegreesRotation(270);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertFalse(fc.rightFacing());
  }

  @Test
  void upwardFacingAt15Degrees() {
      myModelTurtle.setDegreesRotation(15);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
      assertTrue(fc.upwardFacing());
  }

  @Test
  void downwardFacingAt195Degrees() {
    myModelTurtle.setDegreesRotation(195);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertFalse(fc.upwardFacing());
  }

//  @Test
//  void performMovement() {
//  }

  @Test
  void computeVectors() {



  }

  @Test
  void checkSineUsedForQuad1T() {
    myModelTurtle.setDegreesRotation(15);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertTrue(fc.checkVectorOrientation());
  }

  @Test
  void checkCosineUsedForQuad1T() {
    myModelTurtle.setDegreesRotation(65);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertFalse(fc.checkVectorOrientation());
  }

  @Test
  void checkSineUsedForQuad1F() {
    myModelTurtle.setDegreesRotation(50);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertFalse(fc.checkVectorOrientation());
  }

  @Test
  void checkCosineUsedForQuad1F() {
    myModelTurtle.setDegreesRotation(35);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertTrue(fc.checkVectorOrientation());
  }

  @Test
  void checkSineUsedForQuad2T() {
    myModelTurtle.setDegreesRotation(175);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertTrue(fc.checkVectorOrientation());
  }

  @Test
  void checkCosineUsedForQuad2T() {
    myModelTurtle.setDegreesRotation(105);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertFalse(fc.checkVectorOrientation());
  }

  @Test
  void checkSineUsedForQuad2F() {
    myModelTurtle.setDegreesRotation(100);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertFalse(fc.checkVectorOrientation());
  }

  @Test
  void checkCosineUsedForQuad2F() {
    myModelTurtle.setDegreesRotation(170);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertTrue(fc.checkVectorOrientation());
  }

  @Test
  void checkSineUsedForQuad3T() {
    myModelTurtle.setDegreesRotation(200);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertTrue(fc.checkVectorOrientation());
  }

  @Test
  void checkCosineUsedForQuad3T() {
    myModelTurtle.setDegreesRotation(240);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertFalse(fc.checkVectorOrientation());
  }

  @Test
  void checkSineUsedForQuad3F() {
    myModelTurtle.setDegreesRotation(250);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertFalse(fc.checkVectorOrientation());
  }

  @Test
  void checkCosineUsedForQuad3F() {
    myModelTurtle.setDegreesRotation(190);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertTrue(fc.checkVectorOrientation());
  }

  @Test
  void checkSineUsedForQuad4T() {
    myModelTurtle.setDegreesRotation(330);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertTrue(fc.checkVectorOrientation());
  }

  @Test
  void checkCosineUsedForQuad4T() {
    myModelTurtle.setDegreesRotation(280);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertFalse(fc.checkVectorOrientation());
  }

  @Test
  void checkSineUsedForQuad4F() {
    myModelTurtle.setDegreesRotation(290);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertFalse(fc.checkVectorOrientation());
  }

  @Test
  void checkCosineUsedForQuad4F() {
    myModelTurtle.setDegreesRotation(330);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertTrue(fc.checkVectorOrientation());
  }

  @Test
  void setNewCoordinates() {
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    fc.myNewX = 100;
    fc.myNewY = 100;
    fc.setNewCoordinates();
    assertEquals(myModelTurtle.getTurtleCoordinates().turtleNewX, 100);
    assertEquals(myModelTurtle.getTurtleCoordinates().turtleNewY, 100);
  }
}