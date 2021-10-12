package oolala.model.commands.movements;

import static oolala.view.displays.LogoDisplay.COMMAND_HEIGHT;
import static oolala.view.displays.LogoDisplay.FRAME_HEIGHT;
import static oolala.view.displays.LogoDisplay.FRAME_WIDTH;
import static org.junit.jupiter.api.Assertions.assertEquals;

import oolala.model.ModelTurtle;
import org.junit.jupiter.api.Test;

class HomeCommandTest {

  private static final int OFFSET_Y = 15;
  private static final int OFFSET_Y_TOP = 26;

  private static final int TurtleHomeX = (FRAME_WIDTH / 2);
  private static final int TurtleHomeY = ((FRAME_HEIGHT - OFFSET_Y_TOP - COMMAND_HEIGHT + OFFSET_Y)
      / 2);

  ModelTurtle myModelTurtle = new ModelTurtle(0);

  @Test
  void computeHomeCoordinates() {
    HomeModelCommand hc = new HomeModelCommand(myModelTurtle);
    assertEquals(TurtleHomeX, hc.myNewX);
    assertEquals(TurtleHomeY, hc.myNewY);
  }

}