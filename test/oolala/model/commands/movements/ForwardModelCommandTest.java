package oolala.model.commands.movements;

import static oolala.view.displays.LogoDisplay.COMMAND_HEIGHT;
import static oolala.view.displays.LogoDisplay.FRAME_HEIGHT;
import static oolala.view.displays.LogoDisplay.FRAME_WIDTH;
import static org.junit.jupiter.api.Assertions.assertEquals;

import oolala.model.ModelTurtle;
import org.junit.jupiter.api.Test;

class ForwardModelCommandTest {

  private static final int TESTING_PIXELS = 100;

  private static final int OFFSET_Y = 15;
  private static final int OFFSET_Y_TOP = 26;

  private static final int TurtleHomeX = (FRAME_WIDTH / 2);
  private static final int TurtleHomeY = ((FRAME_HEIGHT - OFFSET_Y_TOP - COMMAND_HEIGHT + OFFSET_Y) / 2);

  ModelTurtle myModelTurtle = new ModelTurtle(0);

  @Test
  void computeForwardWithNorth100() {
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, TESTING_PIXELS);
    assertEquals(TurtleHomeX, fc.myNewX);
    assertEquals(TurtleHomeY-TESTING_PIXELS, fc.myNewY);
  }

  @Test
  void computeForwardWithSouth100() {
    myModelTurtle.setDegreesRotation(180);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, TESTING_PIXELS);
    assertEquals(TurtleHomeX, fc.myNewX);
    assertEquals(TurtleHomeY+TESTING_PIXELS, fc.myNewY);
  }

  @Test
  void computeForwardWithNortheast100() {
    myModelTurtle.setDegreesRotation(45);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertEquals(TurtleHomeX + 70, fc.myNewX);
    assertEquals(TurtleHomeY - 70, fc.myNewY);
  }

  @Test
  void computeForwardWithNorthwest100() {
    myModelTurtle.setDegreesRotation(315);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertEquals(TurtleHomeX - 70, fc.myNewX);
    assertEquals(TurtleHomeY - 70, fc.myNewY);
  }

  @Test
  void computeForwardWithSouthEast100() {
    myModelTurtle.setDegreesRotation(225);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertEquals(TurtleHomeX + 70, fc.myNewX);
    assertEquals(TurtleHomeY - 70, fc.myNewY);
  }

  @Test
  void computeForwardWithDegrees295Length100() {
    myModelTurtle.setDegreesRotation(295);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertEquals(TurtleHomeX - 90, fc.myNewX);
    assertEquals(TurtleHomeY - 42, fc.myNewY);
  }

}