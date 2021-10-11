package oolala.model.commands.visuals;

import oolala.model.commands.Commands;
import oolala.view.TurtleLinkage;

/**
 * @author marcusdeans
 *
 * Purpose: Create a pendown command that will cause line's to be drawn on user's screen
 * Assumptions: Functioning ViewTurtle; other values error-checked
 * Dependencies: ViewTurtle, ViewCommand, PenViewComand
 * Example Usage: used for 'pd' command
 * User Details: None, usage exactly as dictated by command entry pattern (error-checked)
 */
public class PenDownViewCommand extends PenViewCommand {

  /**
   * Create a new PenDown command to set the lines for this turtle as pen down (i.e., draw them)
   *
   * @param turtleLinkage the turtle on which the action will be imparted
   */
  public PenDownViewCommand(TurtleLinkage turtleLinkage) {
    super(turtleLinkage);
    myCommandName = Commands.PENUP;
    updateTurtleOpacity();
  }
}
