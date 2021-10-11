package oolala.model.commands.movements;

import oolala.model.commands.Commands;
import oolala.view.TurtleLinkage;

/**
 * @author marcusdeans
 *
 * Purpose: Create a home command to move the object home
 * Assumptions: Functioning ModelTurtle with defined home position; other values error-checked
 * Dependencies: ModelTurtle, MovementModelCommand, ModelCommand
 * Example Usage: used for 'hm' command
 * User Details: None, usage exactly as dictated by command entry pattern (error-checked)
 */
public class HomeModelCommand extends MovementModelCommand {

  /**
   * Construct a home command to move Turtle home
   *
   * @param turtleLinkage the turtle on which the action will be imparted
   */
  public HomeModelCommand(TurtleLinkage turtleLinkage) {
    super(turtleLinkage, 0);
    myCommandName = Commands.HOME;
    computeHomeCoordinates();
    setNewCoordinates();
  }

  //compute the home coordinates by retrieving that information from the TUrtle
  private void computeHomeCoordinates() {
    myNewX = myModelTurtle.getHomeCoordinates().turtleNewX;
    myNewY = myModelTurtle.getHomeCoordinates().turtleNewY;
  }
}
