package oolala.model.commands.rotations;

import oolala.model.ModelTurtle;
import oolala.model.commands.Commands;

/**
 * @author marcusdeans
 *
 * Purpose: Create a rotation command that will be used to rotate object right
 * Assumptions: Functioning ModelTurtle with defined rotation on screen; other values error-checked
 * Dependencies: ModelTurtle, RotateModelCommand, Command
 * Example Usage: used for 'rt' command
 * User Details: None, usage exactly as dictated by command entry pattern (error-checked)
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