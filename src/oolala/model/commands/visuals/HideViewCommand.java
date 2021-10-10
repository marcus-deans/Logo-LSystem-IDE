package oolala.model.commands.visuals;

import oolala.model.commands.Commands;
import oolala.view.ViewTurtle;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Create a hide command that will hide the object on the user's screen Assumptions:
 * Functioning ViewTurtle with defined JavaFX ImageView; other values error-checked Dependencies:
 * ViewTurtle, ViewCommand, Command Example Usage: used for 'ht' command User Details: None, usage
 * exactly as dictated by command entry pattern (error-checked)
 */
public class HideViewCommand extends VisibleViewCommand {

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
