package oolala.model.commands.movements;

import oolala.model.Coordinates;
import oolala.model.commands.ModelCommand;
import oolala.view.TurtleLinkage;

/**
 * @author marcusdeans
 *
 * Purpose: Create a abstract movement command that will be used to move object around
 * Assumptions: Functioning ModelTurtle with defined coordinates on screen; other values error-checked
 * Dependencies: ModelTurtle, ModelCommand
 * Example Usage: used for 'fd', 'bk', 'hm' commands to move Turtle around
 * User Details: None, usage exactly as dictated by command entry pattern (error-checked)
 */
public abstract class MovementModelCommand extends ModelCommand {

  //Direction quantifiers in degrees, named via compass
  public static final int NORTH = 0;
  public static final int EAST = 90;
  public static final int SOUTH = 180;
  public static final int WEST = 270;
  public static final int NORTHEAST = 45;
  public static final int NORTHWEST = 315;
  public static final int SOUTHWEST = 225;
  public static final int SOUTHEAST = 135;

  protected Coordinates myTurtleCoordinates;
  protected Coordinates myTurtleVisualCoordinates;

  protected int myDegreesRotation;
  protected int myXVector;
  protected int myYVector;

  //public for testing, protected normally
  public int myNewX;
  public int myNewY;

  protected int myVisualNewX;
  protected int myVisualNewY;

  /**
   * Construct a movement command to move the turtle
   *
   * @param turtleLinkage the turtle on which the action will be imparted
   * @param pixels        the distance of movement
   */
  public MovementModelCommand(TurtleLinkage turtleLinkage, int pixels) {
    super(turtleLinkage, pixels);
    getTurtleCoordinates();
    myDegreesRotation = myModelTurtle.getDegreesRotation();
    computeVectors();
  }

  //get the current coordinates of the turtle
  protected void getTurtleCoordinates() {
    myTurtleCoordinates = myModelTurtle.getTurtleCoordinates();
    myTurtleVisualCoordinates = myModelTurtle.getTurtleVisualCoordinates();
  }

  //determine if turtle is facing to the right
  //made public for testing purposes
  public boolean rightFacing() {
    return (myDegreesRotation >= NORTH) && (myDegreesRotation <= SOUTH);
  }

  //determine is turtle is facing upward
  //made public for testing purposes
  public boolean upwardFacing() {
    return (myDegreesRotation <= EAST) || (myDegreesRotation >= WEST);
  }

  //use vector math to determine the value of the vectors on each axis
  protected void computeVectors() {
    Math.toRadians(myDegreesRotation);
    int sinLength = (int) Math.abs(Math.sin(myDegreesRotation) * pixels);
    int cosLength = (int) Math.abs(Math.cos(myDegreesRotation) * pixels);
    myXVector = checkVectorOrientation() ? sinLength : cosLength;
    myYVector = checkVectorOrientation() ? cosLength : sinLength;
  }

  //determine orientation of turtle so that appropriate trigonometric conversions used
  protected boolean checkVectorOrientation() {
    return (myDegreesRotation <= NORTHEAST) || (myDegreesRotation >= NORTHWEST) || (
        (myDegreesRotation >= SOUTHEAST)
            && (myDegreesRotation <= SOUTHWEST));
  }

  //set the newly computed coordinates in the Turtle object
  protected void setNewCoordinates() {
    myModelTurtle.setNewX(myNewX);
    myModelTurtle.setVisualNewX(myVisualNewX);
    myModelTurtle.setNewY(myNewY);
    myModelTurtle.setVisualNewY(myVisualNewY);
  }
}
