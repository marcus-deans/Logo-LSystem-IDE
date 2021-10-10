package oolala.model.commands.visuals;

import oolala.model.commands.Commands;
import oolala.model.commands.ViewCommand;
import oolala.view.ViewTurtle;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Assumptions: Dependencies: Example Usage: User Details:
 */
public class HideViewCommand extends ViewCommand {

  /**
   * Create a new Hide command to hide the turtle object from view on screen
   *
   * @param viewTurtle the turtle on which the action will be imparted
   */
  public HideViewCommand(ViewTurtle viewTurtle) {
    super(viewTurtle);
    myCommandName = Commands.HIDE;
    displayTurtle();
  }
}
