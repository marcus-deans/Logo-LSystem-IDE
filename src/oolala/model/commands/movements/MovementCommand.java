package oolala.model.commands.movements;

import oolala.model.Coordinates;
import oolala.model.Turtle;
import oolala.model.commands.Command;

public class MovementCommand extends Command {
  Coordinates myTurtleCoordinates;
  int myDegreesRotation;
  int myXVector;
  int myYVector;
  int myNewX;
  int myNewY;

  public MovementCommand(Turtle myTurtle, int pixels){
    super(myTurtle, pixels);
    getTurtleCoordinates();
    myDegreesRotation = myTurtle.getDegreesRotation();
    computeVectors();
  }

  protected void getTurtleCoordinates(){
    myTurtleCoordinates = myTurtle.getTurtleCoordinates();
  }

  protected boolean rightFacing() {
    return (myDegreesRotation >= 0) && (myDegreesRotation <= 180);
  }

  protected boolean upwardFacing() {
    return (myDegreesRotation <= 90) || (myDegreesRotation >= 270);
  }

  protected void performMovement(){
    myTurtle.performMovement();
  }

  protected void computeVectors() {
    int sinLength = (int) Math.sin(myDegreesRotation) * pixels;
    int cosLength = (int) Math.cos(myDegreesRotation) * pixels;
    myXVector = checkVectorAngles() ? sinLength : cosLength;
    myYVector = checkVectorAngles() ? cosLength : sinLength;
  }

  protected boolean checkVectorAngles() {
    return (myDegreesRotation <= 45) || (myDegreesRotation >= 315) || ((myDegreesRotation >= 135) && (myDegreesRotation <= 225));
  }

  protected void setNewCoordinates(){
    myTurtle.setNewX(myNewX);
    myTurtle.setNewY(myNewY);
  }
}
