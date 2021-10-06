package oolala.model.commands.movements;

import static oolala.view.LogoDisplay.COMMAND_HEIGHT;
import static oolala.view.LogoDisplay.FRAME_HEIGHT;
import static oolala.view.LogoDisplay.FRAME_WIDTH;

import oolala.model.Turtle;
import oolala.model.commands.Commands;

public class HomeCommand extends MovementCommand {

  private static final int OFFSET_Y = 15;
  private static final int OFFSET_Y_TOP = 26;

  public HomeCommand(Turtle myTurtle, int pixels) {
    super(myTurtle, pixels);
    myCommandName = Commands.HOME;
    computeHomeCoordinates();
    setNewCoordinates();
    performMovement();
  }

  private void computeHomeCoordinates() {
    myNewX = (int) (FRAME_WIDTH / 2 - myTurtle.getMyTurtleView().getFitWidth() / 2);
    myNewY = (int) ((FRAME_HEIGHT - OFFSET_Y_TOP - COMMAND_HEIGHT + OFFSET_Y) / 2
        - myTurtle.getMyTurtleView().getFitHeight() / 2);
  }
}
