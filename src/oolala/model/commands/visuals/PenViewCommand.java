package oolala.model.commands.visuals;

import oolala.model.commands.ViewCommand;
import oolala.view.ViewTurtle;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Create a pen control command that will be used to either show/hide lines on screen
 * Assumptions: Functioning ViewTurtle ; other values error-checked Dependencies: ViewTurtle,
 * ViewCommand Example Usage: used for 'pd' and 'pu' commands User Details: None, usage exactly as
 * dictated by command entry pattern (error-checked)
 */
public abstract class PenViewCommand extends ViewCommand {

  /**
   * Create a new abstract PenViewCommand command to control lines for a turtle
   *
   * @param viewTurtle the turtle on which the action will be imparted
   */
  public PenViewCommand(ViewTurtle viewTurtle) {
    super(viewTurtle);
  }

  //change the TurtleOpacity boolean (referenced when drawing lines)
  protected void updateTurtleOpacity() {
    myViewTurtle.changePenOpacity();
  }
}
