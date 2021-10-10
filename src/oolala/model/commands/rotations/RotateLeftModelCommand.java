package oolala.model.commands.rotations;

import oolala.model.ModelTurtle;
import oolala.model.commands.Commands;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Assumptions: Dependencies: Example Usage: User Details:
 */
public class RotateLeftModelCommand extends RotateModelCommand {

  /**
   * Create new rotation command to rotate turtle left
   *
   * @param myModelTurtle the turtle on which the action will be imparted
   * @param degrees       number of degrees by which turtle will be rotated
   */
  public RotateLeftModelCommand(ModelTurtle myModelTurtle, int degrees) {
    super(myModelTurtle, degrees);
    myCommandName = Commands.LEFT;
    setLeftRotation();
  }

  //compute the new rotation of the object by decrementing (leftward rotation)
  private void setLeftRotation() {
    setTurtleRotation(myModelTurtle.getDegreesRotation() - degreesToRotate);
  }
}
