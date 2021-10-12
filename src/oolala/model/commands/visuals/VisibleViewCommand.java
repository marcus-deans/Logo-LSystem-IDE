package oolala.model.commands.visuals;

import oolala.model.commands.ViewCommand;
import oolala.view.ViewTurtle;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Create a visibility control command that will be used to either show/hide turtle on
 * screen Assumptions: Functioning ViewTurtle with defined JavaFX ImageView; other values
 * error-checked Dependencies: ViewTurtle, ViewCommand Example Usage: used for 'ht' and 'st'
 * commands User Details: None, usage exactly as dictated by command entry pattern (error-checked)
 */
public abstract class VisibleViewCommand extends ViewCommand {

  /**
   * Create a new abstract VisibleViewCommand to control whether turtle is shown
   *
   * @param viewTurtle the turtle on which the action will be imparted
   */
  public VisibleViewCommand(ViewTurtle viewTurtle) {
    super(viewTurtle);
  }

  //change the visibility of the JavaFX ImageView object by referencing ViewTurtle
  protected void displayTurtle() {
    myTurtleView.setVisible(!myTurtleView.isVisible());
  }
}
