package oolala.model.commands.visuals;

import oolala.model.commands.Commands;
import oolala.model.commands.ViewCommand;
import oolala.view.ViewTurtle;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Assumptions: Dependencies: Example Usage: User Details:
 */
public class PenUpViewCommand extends ViewCommand {

  /**
   * Create a new PenUp command to set the lines for this turtle as pen up (i.e., don't draw them)
   *
   * @param viewTurtle the turtle on which the action will be imparted
   */
  public PenUpViewCommand(ViewTurtle viewTurtle) {
    super(viewTurtle);
    myCommandName = Commands.PENUP;
    updateTurtleOpacity();
  }
}
