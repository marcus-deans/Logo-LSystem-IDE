package oolala.model.commands.movements;

import oolala.model.Coordinates;
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
    int myTurtleOldVisualX = myTurtleVisualCoordinates.turtleOldX;
    int myTurtleOldVisualY = myTurtleVisualCoordinates.turtleOldY;

    Coordinates newCoordinates = calculateForwardCoordinates(myTurtleOldX, myTurtleOldY);
    myNewX = newCoordinates.turtleNewX;
    myNewY = newCoordinates.turtleNewY;

    Coordinates newVisualCoordinates = calculateForwardCoordinates(myTurtleOldVisualX,
        myTurtleOldVisualY);
    myVisualNewX = newVisualCoordinates.turtleNewX;
    myVisualNewY = newVisualCoordinates.turtleNewY;
  }

  private Coordinates calculateForwardCoordinates(int oldX, int oldY) {
    int newX = rightFacing() ? oldX + myXVector : oldX - myXVector;
    int newY = upwardFacing() ? oldY - myYVector : oldY + myYVector;
    return new Coordinates(newX, newY);
  }
}
