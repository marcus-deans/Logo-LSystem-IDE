package oolala.model.commands.movements;

import oolala.model.ModelTurtle;
import oolala.model.commands.Commands;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Assumptions: Dependencies: Example Usage: User Details:
 */
public class HomeModelCommand extends MovementModelCommand {

  /**
   * Construct a home command to move Turtle home
   *
   * @param myModelTurtle the turtle on which the action will be imparted
   */
  public HomeModelCommand(ModelTurtle myModelTurtle) {
    super(myModelTurtle, 0);
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
