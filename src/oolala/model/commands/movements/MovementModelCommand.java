package oolala.model.commands.movements;

import oolala.model.Coordinates;
import oolala.model.ModelTurtle;
import oolala.model.commands.ModelCommand;

public abstract class MovementModelCommand extends ModelCommand {

  //Direction quantifiers in degrees, named via compass
  public static final int NORTH = 0;
  public static final int SOUTH = 180;
  public static final int WEST = 270;
  public static final int EAST = 90;
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

  public MovementModelCommand(ModelTurtle myModelTurtle, int pixels) {
    super(myModelTurtle, pixels);
    getTurtleCoordinates();
    myDegreesRotation = myModelTurtle.getDegreesRotation();
    computeVectors();
  }

  protected void getTurtleCoordinates() {
    myTurtleCoordinates = myModelTurtle.getTurtleCoordinates();
    myTurtleVisualCoordinates = myModelTurtle.getTurtleVisualCoordinates();
  }

  protected boolean rightFacing() {
    return (myDegreesRotation >= NORTH) && (myDegreesRotation <= SOUTH);
  }

  protected boolean upwardFacing() {
    return (myDegreesRotation <= EAST) || (myDegreesRotation >= WEST);
  }

//  protected void performMovement() {
//    myTurtle.performMovement();
//  }

  protected void computeVectors() {
    int sinLength = (int) Math.abs(Math.sin(myDegreesRotation) * pixels);
    int cosLength = (int) Math.abs(Math.cos(myDegreesRotation) * pixels);
    myXVector = checkVectorAngles() ? sinLength : cosLength;
    myYVector = checkVectorAngles() ? cosLength : sinLength;
  }

  protected boolean checkVectorAngles() {
    return (myDegreesRotation <= NORTHEAST) || (myDegreesRotation >= NORTHWEST) || (
        (myDegreesRotation >= SOUTHEAST)
            && (myDegreesRotation <= SOUTHWEST));
  }

  protected void setNewCoordinates() {
    myModelTurtle.setNewX(myNewX);
    myModelTurtle.setVisualNewX(myVisualNewX);
    myModelTurtle.setNewY(myNewY);
    myModelTurtle.setVisualNewY(myVisualNewY);
  }
}
