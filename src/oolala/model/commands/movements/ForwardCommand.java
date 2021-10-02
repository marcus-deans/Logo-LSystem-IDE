package oolala.model.commands.movements;

import oolala.model.Turtle;

public class ForwardCommand extends MovementCommand {
  public ForwardCommand(Turtle myTurtle, int pixels) {
    super(myTurtle, pixels);
    computeForwardCoordinates();
    setNewCoordinates();
    performMovement();
  }

  private void computeForwardCoordinates() {
    myNewX = rightFacing() ? myTurtle.oldX + myXVector : myTurtle.oldX - myXVector;
    myNewY = upwardFacing() ? myTurtle.oldY - myYVector : myTurtle.oldY + myYVector;
  }
}
