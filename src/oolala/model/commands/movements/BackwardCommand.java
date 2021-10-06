package oolala.model.commands.movements;

import oolala.model.Turtle;
import oolala.model.commands.Commands;

public class BackwardCommand extends MovementCommand {

  public BackwardCommand(Turtle myTurtle, int pixels) {
    super(myTurtle, pixels);
    myCommandName = Commands.BACKWARD;
    computeBackwardCoordinates();
    setNewCoordinates();
//    performMovement();
  }

  private void computeBackwardCoordinates() {
    int myTurtleOldX = myTurtleCoordinates.turtleOldX;
    int myTurtleOldY = myTurtleCoordinates.turtleOldY;
    myNewX = rightFacing() ? myTurtleOldX - myXVector : myTurtleOldX + myXVector;
    myNewY = upwardFacing() ? myTurtleOldY + myYVector : myTurtleOldY - myYVector;
  }
}


