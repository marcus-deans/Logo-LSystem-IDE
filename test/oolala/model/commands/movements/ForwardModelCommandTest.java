package oolala.model.commands.movements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import oolala.model.ModelTurtle;
import oolala.view.LogoDisplay;
import org.junit.jupiter.api.Test;

class ForwardModelCommandTest {

  LogoDisplay myLogoDisplay = new LogoDisplay();
  //  Turtle myTurtle = new Turtle(1);
  ModelTurtle myModelTurtle = myLogoDisplay.myModelTurtle;
  int expected = 100;

  @Test
  void computeForwardWithUpward100() {
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
//    assertEquals(fc.computeForwardCoordinates, expected);
  }

  @Test
  void computeForwardWithNortheast100() {
    myModelTurtle.setDegreesRotation(45);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
//    assertEquals(fc.computeForwar);
  }

  @Test
  void computeForwardWithNorthwest100() {
    myModelTurtle.setDegreesRotation(45);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertEquals(fc.myNewX, 50);
    assertEquals(fc.myNewY, -50);
  }

  @Test
  void computeForwardWithSouth100() {
    myModelTurtle.setDegreesRotation(180);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertEquals(fc.myNewX, 0);
    assertEquals(fc.myNewY, 100);
  }

  @Test
  void computeForwardWithSouthEast100() {
    myModelTurtle.setDegreesRotation(225);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertEquals(fc.myNewY, 50);
    assertEquals(fc.myNewX, 50);
  }

  @Test
  void computeForwardWithDegrees295Length100() {
    myModelTurtle.setDegreesRotation(295);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertEquals(fc.myNewX, 91); //sin
    assertEquals(fc.myNewY, 42); //cos
  }
}