package oolala.model.commands.movements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import oolala.model.Turtle;
import oolala.view.LogoDisplay;
import org.junit.jupiter.api.Test;

class ForwardCommandTest {

  LogoDisplay myLogoDisplay = new LogoDisplay();
  //  Turtle myTurtle = new Turtle(1);
  Turtle myTurtle = myLogoDisplay.myTurtle;
  int expected = 100;

  @Test
  void computeForwardWithUpward100() {
    ForwardCommand fc = new ForwardCommand(myTurtle, 100);
//    assertEquals(fc.computeForwardCoordinates, expected);
  }

  @Test
  void computeForwardWithNortheast100() {
    myTurtle.setDegreesRotation(45);
    ForwardCommand fc = new ForwardCommand(myTurtle, 100);
//    assertEquals(fc.computeForwar);
  }

  @Test
  void computeForwardWithNorthwest100() {
    myTurtle.setDegreesRotation(45);
    ForwardCommand fc = new ForwardCommand(myTurtle, 100);
    assertEquals(fc.myNewX, 50);
    assertEquals(fc.myNewY, -50);
  }

  @Test
  void computeForwardWithSouth100() {
    myTurtle.setDegreesRotation(180);
    ForwardCommand fc = new ForwardCommand(myTurtle, 100);
    assertEquals(fc.myNewX, 0);
    assertEquals(fc.myNewY, 100);
  }

  @Test
  void computeForwardWithSouthEast100() {
    myTurtle.setDegreesRotation(225);
    ForwardCommand fc = new ForwardCommand(myTurtle, 100);
    assertEquals(fc.myNewY, 50);
    assertEquals(fc.myNewX, 50);
  }

  @Test
  void computeForwardWithDegrees295Length100() {
    myTurtle.setDegreesRotation(295);
    ForwardCommand fc = new ForwardCommand(myTurtle, 100);
    assertEquals(fc.myNewX, 91); //sin
    assertEquals(fc.myNewY, 42); //cos
  }
}