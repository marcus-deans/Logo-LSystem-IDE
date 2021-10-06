package oolala.model.commands.movements;

import oolala.model.Coordinates;
import oolala.model.Turtle;
import oolala.model.commands.Command;

public abstract class MovementCommand extends Command {

  //Direction quantifiers in degrees, named via compass
  public static final int NORTH = 0;
  public static final int SOUTH = 180;
  public static final int WEST = 270;
  public static final int EAST = 90;
  public static final int NORTHEAST = 45;
  public static final int NORTHWEST = 315;
  public static final int SOUTHWEST = 225;
  public static final int SOUTHEAST = 135;

  Coordinates myTurtleCoordinates;
  int myDegreesRotation;
  int myXVector;
  int myYVector;
  int myNewX;
  int myNewY;

  public MovementCommand(Turtle myTurtle, int pixels) {
    super(myTurtle, pixels);
    getTurtleCoordinates();
    myDegreesRotation = myTurtle.getDegreesRotation();
    computeVectors();
  }

  protected void getTurtleCoordinates() {
    myTurtleCoordinates = myTurtle.getTurtleCoordinates();
  }

  protected boolean rightFacing() {
    return (myDegreesRotation >= NORTH) && (myDegreesRotation <= SOUTH);
  }

  protected boolean upwardFacing() {
    return (myDegreesRotation <= EAST) || (myDegreesRotation >= WEST);
  }

  protected void performMovement() {
    myTurtle.performMovement();
  }

  protected void computeVectors() {
    int sinLength = (int) Math.sin(myDegreesRotation) * pixels;
    int cosLength = (int) Math.cos(myDegreesRotation) * pixels;
    myXVector = checkVectorAngles() ? sinLength : cosLength;
    myYVector = checkVectorAngles() ? cosLength : sinLength;
  }

  protected boolean checkVectorAngles() {
    return (myDegreesRotation <= NORTHEAST) || (myDegreesRotation >= NORTHWEST) || (
        (myDegreesRotation >= SOUTHEAST)
            && (myDegreesRotation <= SOUTHWEST));
  }

  protected void setNewCoordinates() {
    myTurtle.setNewX(myNewX);
    myTurtle.setNewY(myNewY);
  }
}
