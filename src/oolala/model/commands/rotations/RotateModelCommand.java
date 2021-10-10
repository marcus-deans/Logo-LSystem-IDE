package oolala.model.commands.rotations;

import oolala.model.ModelTurtle;
import oolala.model.commands.ModelCommand;

public abstract class RotateModelCommand extends ModelCommand {

  private static final int FULL_CIRCLE = 360;
  protected int degreesToRotate;

  /**
   * Create new rotation command to rotate turtle in given direction
   *
   * @param myModelTurtle the turtle on which the action will be imparted
   * @param degrees       number of degrees by which turtle will be rotated
   */
  public RotateModelCommand(ModelTurtle myModelTurtle, int degrees) {
    super(myModelTurtle, degrees);
    this.degreesToRotate = degrees;
  }

  //set the rotation of the Turtle object based on the updates
  //the combination of adding and modulus FULL_CIRCLE ensures positive rotation value (clockwise)
  protected void setTurtleRotation(int newDegreesRotation) {
    myModelTurtle.setDegreesRotation((newDegreesRotation + FULL_CIRCLE) % FULL_CIRCLE);
  }
}
