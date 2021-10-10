package oolala.model.commands.visuals;

import oolala.model.commands.Commands;
import oolala.model.commands.ViewCommand;
import oolala.view.ViewTurtle;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Assumptions: Dependencies: Example Usage: User Details:
 */
public class PenDownViewCommand extends ViewCommand {

  /**
   * Create a new PenDown command to set the lines for this turtle as pen down (i.e., draw them)
   *
   * @param viewTurtle the turtle on which the action will be imparted
   */
  public PenDownViewCommand(ViewTurtle viewTurtle) {
    super(viewTurtle);
    myCommandName = Commands.PENUP;
    updateTurtleOpacity();
  }
}
