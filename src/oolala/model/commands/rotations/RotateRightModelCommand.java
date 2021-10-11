package oolala.model.commands.rotations;

import oolala.model.commands.Commands;
import oolala.view.TurtleLinkage;

/**
 * @author marcusdeans
 *
 * Purpose: Create a rotation command that will be used to rotate object right
 * Assumptions: Functioning ModelTurtle with defined rotation on screen; other values error-checked
 * Dependencies: ModelTurtle, RotateModelCommand, ModelCommand
 * Example Usage: used for 'rt' command
 * User Details: None, usage exactly as dictated by command entry pattern (error-checked)
 */
public class RotateRightModelCommand extends RotateModelCommand {

  /**
   * Create new rotation command to rotate turtle right
   *
   * @param turtleLinkage the turtle on which the action will be imparted
   * @param degrees       number of degrees by which turtle will be rotated
   */
  public RotateRightModelCommand(TurtleLinkage turtleLinkage, int degrees) {
    super(turtleLinkage, degrees);
    myCommandName = Commands.RIGHT;
    setRightRotation();
  }

  //compute the new rotation of the object by incrementing (rightward rotation)
  //made public for testing purposes
  public void setRightRotation() {
    setTurtleRotation(myModelTurtle.getDegreesRotation() + degreesToRotate);
  }
}