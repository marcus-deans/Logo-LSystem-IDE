package oolala.model.commands.visuals;

import oolala.model.commands.Commands;
import oolala.view.TurtleLinkage;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Create a show command that will show the object on the user's screen Assumptions:
 * Functioning ViewTurtle with defined JavaFX ImageView; other values error-checked Dependencies:
 * ViewTurtle, ViewCommand, Command Example Usage: used for 'st' command User Details: None, usage
 * exactly as dictated by command entry pattern (error-checked)
 */
public class ShowViewCommand extends VisibleViewCommand {

  /**
   * Create a new Show command to show the turtle object on screen
   *
   * @param turtleLinkage the turtle on which the action will be imparted
   */
  public ShowViewCommand(TurtleLinkage turtleLinkage) {
    super(turtleLinkage);
    myCommandName = Commands.SHOW;
    displayTurtle();
  }
}
