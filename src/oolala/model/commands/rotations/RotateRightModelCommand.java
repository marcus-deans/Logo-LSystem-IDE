package oolala.model.commands.rotations;

import oolala.model.ModelTurtle;
import oolala.model.commands.Commands;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Assumptions: Dependencies: Example Usage: User Details:
 */
public class RotateRightModelCommand extends RotateModelCommand {

  /**
   * Create new rotation command to rotate turtle right
   *
   * @param myModelTurtle the turtle on which the action will be imparted
   * @param degrees       number of degrees by which turtle will be rotated
   */
  public RotateRightModelCommand(ModelTurtle myModelTurtle, int degrees) {
    super(myModelTurtle, degrees);
    myCommandName = Commands.RIGHT;
    setRightRotation();
  }

  //compute the new rotation of the object by incrementing (rightward rotation)
  private void setRightRotation() {
    setTurtleRotation(myModelTurtle.getDegreesRotation() + degreesToRotate);
  }
}