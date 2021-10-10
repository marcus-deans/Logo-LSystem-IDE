package oolala.model.commands.movements;

import oolala.model.ModelTurtle;
import oolala.model.commands.Commands;

public class HomeModelCommand extends MovementModelCommand {

  private static final int OFFSET_Y = 15;
  private static final int OFFSET_Y_TOP = 26;

  public HomeModelCommand(ModelTurtle myModelTurtle, int pixels) {
    super(myModelTurtle, pixels);
    myCommandName = Commands.HOME;
    computeHomeCoordinates();
    setNewCoordinates();
//    performMovement();
  }

  private void computeHomeCoordinates() {
    myNewX = myModelTurtle.getHomeCoordinates().turtleNewX;
    myNewY = myModelTurtle.getHomeCoordinates().turtleNewY;
//    myNewX = (int) (FRAME_WIDTH / 2 - myTurtle.getMyTurtleView().getFitWidth() / 2);
//    myNewY = (int) ((FRAME_HEIGHT - OFFSET_Y_TOP - COMMAND_HEIGHT + OFFSET_Y) / 2
//        - myTurtle.getMyTurtleView().getFitHeight() / 2);
  }
}
