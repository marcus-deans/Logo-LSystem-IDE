package oolala.model.commands.movements;

import oolala.model.Coordinates;
import oolala.model.ModelTurtle;
import oolala.model.commands.Commands;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Assumptions: Dependencies: Example Usage: User Details:
 */
public class ForwardModelCommand extends MovementModelCommand {

  /**
   * Construct a forward command to move forward
   *
   * @param myModelTurtle the turtle on which the action will be imparted
   * @param pixels        the distance of movement
   */
  public ForwardModelCommand(ModelTurtle myModelTurtle, int pixels) {
    super(myModelTurtle, pixels);
    myCommandName = Commands.FORWARD;
    computeForwardCoordinates();
    //TODO: only update coordinates if they are actually within bounds (also used for Creatures)
    setNewCoordinates();
  }

  //compute the coordinates after the forward movement
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

  //perform the actual calculation of the coordinates based on vector mathematics
  private Coordinates calculateForwardCoordinates(int oldX, int oldY) {
    int newX = rightFacing() ? oldX + myXVector : oldX - myXVector;
    int newY = upwardFacing() ? oldY - myYVector : oldY + myYVector;
    return new Coordinates(newX, newY);
  }
}
