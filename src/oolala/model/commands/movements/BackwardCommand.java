package oolala.model.commands.movements;

import oolala.model.Turtle;
import oolala.model.commands.Commands;

public class BackwardCommand extends MovementCommand {
  public BackwardCommand(Turtle myTurtle, int pixels) {
    super(myTurtle, pixels);
    myCommandName = Commands.BACKWARD;
    computeBackwardCoordinates();
    setNewCoordinates();
    performMovement();
  }

  private void computeBackwardCoordinates() {
    myNewX = rightFacing() ? myTurtle.oldX - myXVector : myTurtle.oldX + myXVector;
    myNewY = upwardFacing() ? myTurtle.oldY + myYVector : myTurtle.oldY - myYVector;
  }
}


