package oolala.model.commands.visuals;

import oolala.model.commands.Commands;
import oolala.view.TurtleLinkage;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Create a hide command that will hide the object on the user's screen Assumptions:
 * Functioning ViewTurtle with defined JavaFX ImageView; other values error-checked Dependencies:
 * ViewTurtle, ViewCommand, PenViewComand Example Usage: used for 'pu' command User Details: None,
 * usage exactly as dictated by command entry pattern (error-checked)
 */
public class PenUpViewCommand extends PenViewCommand {

  /**
   * Create a new PenUp command to set the lines for this turtle as pen up (i.e., don't draw them)
   *
   * @param turtleLinkage the turtle on which the action will be imparted
   */
  public PenUpViewCommand(TurtleLinkage turtleLinkage) {
    super(turtleLinkage);
    myCommandName = Commands.PENUP;
    updateTurtleOpacity();
  }
}
