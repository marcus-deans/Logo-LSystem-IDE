package oolala.model.commands.movements;

import oolala.model.Turtle;
import oolala.model.commands.Commands;

public class ForwardCommand extends MovementCommand {
  public ForwardCommand(Turtle myTurtle, int pixels) {
    super(myTurtle, pixels);
    myCommandName = Commands.FORWARD;
    computeForwardCoordinates();
    setNewCoordinates();
    performMovement();
  }

  private void computeForwardCoordinates() {
    int myTurtleOldX = myTurtleCoordinates.turtleOldX;
    int myTurtleOldY = myTurtleCoordinates.turtleOldY;
    myNewX = rightFacing() ? myTurtleOldX + myXVector : myTurtleOldX - myXVector;
    myNewY = upwardFacing() ? myTurtleOldY - myYVector : myTurtleOldY + myYVector;
  }
}
