package oolala.model.commands.movements;

import static oolala.view.displays.LogoDisplay.COMMAND_HEIGHT;
import static oolala.view.displays.LogoDisplay.FRAME_HEIGHT;
import static oolala.view.displays.LogoDisplay.FRAME_WIDTH;
import static org.junit.jupiter.api.Assertions.assertEquals;

import oolala.model.ModelTurtle;
import oolala.view.TurtleLinkage;
import org.junit.jupiter.api.Test;

class BackwardModelCommandTest {

  private static final int TESTING_PIXELS = 100;

  private static final int OFFSET_Y = 15;
  private static final int OFFSET_Y_TOP = 26;
  private static final int TurtleHomeX = (FRAME_WIDTH / 2);
  private static final int TurtleHomeY = ((FRAME_HEIGHT - OFFSET_Y_TOP - COMMAND_HEIGHT + OFFSET_Y) / 2);

  ModelTurtle myModelTurtle = new ModelTurtle(0);
  TurtleLinkage turtLink = new TurtleLinkage(0);

  @Test
  void checkPositiveBackwardMovement() {
    assertEquals(myModelTurtle.getTurtleCoordinates().turtleNewX, 100);
  }

  @Test
  void computeBackwardWithNorth100() {
    BackwardModelCommand bc = new BackwardModelCommand(myModelTurtle, TESTING_PIXELS);
    assertEquals(TurtleHomeX, bc.myNewX);
    assertEquals(TurtleHomeY+TESTING_PIXELS, bc.myNewY);
  }

  @Test
  void computeBackwardWithSouth100() {
    BackwardModelCommand bc = new BackwardModelCommand(myModelTurtle, TESTING_PIXELS);
    assertEquals(TurtleHomeX, bc.myNewX);
    assertEquals(TurtleHomeY-TESTING_PIXELS, bc.myNewY);
  }

  @Test
  void computeBackwardWithNortheast100() {
    myModelTurtle.setDegreesRotation(45);
    BackwardModelCommand bc = new BackwardModelCommand(myModelTurtle, 100);
    assertEquals(TurtleHomeX - 70, bc.myNewX);
    assertEquals(TurtleHomeY + 70, bc.myNewY);
  }

  @Test
  void computeBackwardWithNorthwest100() {
    myModelTurtle.setDegreesRotation(45);
    BackwardModelCommand bc = new BackwardModelCommand(myModelTurtle, 100);
    assertEquals(TurtleHomeX + 70, bc.myNewX);
    assertEquals(TurtleHomeX + 70, bc.myNewY);
  }

  @Test
  void computeForwardWithDegrees295Length100() {
    myModelTurtle.setDegreesRotation(295);
    ForwardModelCommand fc = new ForwardModelCommand(myModelTurtle, 100);
    assertEquals(TurtleHomeX + 90, fc.myNewX); //sin --> new x-coord
    assertEquals(TurtleHomeY + 42, fc.myNewY); //cos --> new y-coord
  }

}