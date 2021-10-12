package oolala.model.commands.rotations;

import oolala.model.ModelTurtle;
import oolala.model.commands.Commands;

/**
 * @author marcusdeans
 *
 * Purpose: Create a rotation command that will be used to rotate object left
 * Assumptions: Functioning ModelTurtle with defined rotation on screen; other values error-checked
 * Dependencies: ModelTurtle, RotateModelCommand, ModelCommand
 * Example Usage: used for 'lt' command
 * User Details: None, usage exactly as dictated by command entry pattern (error-checked)
 */
public class RotateLeftModelCommand extends RotateModelCommand {

  /**
   * Create new rotation command to rotate turtle left
   *
   * @param modelTurtle the turtle on which the action will be imparted
   * @param degrees     number of degrees by which turtle will be rotated
   */
  public RotateLeftModelCommand(ModelTurtle modelTurtle, int degrees) {
    super(modelTurtle, degrees);
    myCommandName = Commands.LEFT;
    setLeftRotation();
  }

  //compute the new rotation of the object by decrementing (leftward rotation)
  //made public for testing purposes
  public void setLeftRotation() {
    setTurtleRotation(myModelTurtle.getDegreesRotation() - degreesToRotate);
  }
}
