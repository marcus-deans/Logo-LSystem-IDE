package oolala.model.commands.movements;

import oolala.model.ModelTurtle;
import oolala.model.commands.Commands;

public class ForwardModelCommand extends MovementModelCommand {

  public ForwardModelCommand(ModelTurtle myModelTurtle, int pixels) {
    super(myModelTurtle, pixels);
    myCommandName = Commands.FORWARD;
    computeForwardCoordinates();
    //TODO: only update coordinates if they are actually within bounds (also used for Creatures)
    setNewCoordinates();
//    performMovement();
  }

  public void computeForwardCoordinates() {
    int myTurtleOldX = myTurtleCoordinates.turtleOldX;
    int myTurtleOldY = myTurtleCoordinates.turtleOldY;
    myNewX = rightFacing() ? myTurtleOldX + myXVector : myTurtleOldX - myXVector;
    myNewY = upwardFacing() ? myTurtleOldY - myYVector : myTurtleOldY + myYVector;
  }
}
