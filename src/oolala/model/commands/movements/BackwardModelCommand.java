package oolala.model.commands.movements;

import oolala.model.ModelTurtle;
import oolala.model.commands.Commands;

public class BackwardModelCommand extends MovementModelCommand {

  public BackwardModelCommand(ModelTurtle myModelTurtle, int pixels) {
    super(myModelTurtle, pixels);
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


