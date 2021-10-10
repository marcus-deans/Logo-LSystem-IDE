package oolala.model.commands.movements;

import oolala.model.Coordinates;
import oolala.model.ModelTurtle;
import oolala.model.commands.Commands;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Assumptions: Dependencies: Example Usage: User Details:
 */
public class BackwardModelCommand extends MovementModelCommand {

  /**
   * Construct a backward command to move backward
   *
   * @param myModelTurtle the turtle on which the action will be imparted
   * @param pixels        the distance of movement
   */
  public BackwardModelCommand(ModelTurtle myModelTurtle, int pixels) {
    super(myModelTurtle, pixels);
    myCommandName = Commands.BACKWARD;
    computeBackwardCoordinates();
    setNewCoordinates();
//    performMovement();
  }

  //compute the coordinates after the backward movement
  private void computeBackwardCoordinates() {
    int myTurtleOldX = myTurtleCoordinates.turtleOldX;
    int myTurtleOldY = myTurtleCoordinates.turtleOldY;
    int myTurtleOldVisualX = myTurtleVisualCoordinates.turtleOldX;
    int myTurtleOldVisualY = myTurtleVisualCoordinates.turtleOldY;

    Coordinates newCoordinates = calculateBackwardCoordinates(myTurtleOldX, myTurtleOldY);
    myNewX = newCoordinates.turtleNewX;
    myNewY = newCoordinates.turtleNewY;

    Coordinates newVisualCoordinates = calculateBackwardCoordinates(myTurtleOldVisualX,
        myTurtleOldVisualY);
    myVisualNewX = newVisualCoordinates.turtleNewX;
    myVisualNewY = newVisualCoordinates.turtleNewY;
  }

  //perform the actual calculation of the coordinates based on vector mathematics
  private Coordinates calculateBackwardCoordinates(int oldX, int oldY) {
    int newX = rightFacing() ? oldX - myXVector : oldX + myXVector;
    int newY = upwardFacing() ? oldY + myYVector : oldY - myYVector;
    return new Coordinates(newX, newY);
  }
}


