package oolala.model.commands;

import javafx.scene.image.ImageView;
import oolala.view.ViewTurtle;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Create a command that will act on the ViewTurtle object Assumptions: Functioning
 * ViewTurtle; other values error-checked Dependencies: ViewTurtle Example Usage: used for 'ht',
 * 'st', 'stamp', 'pu', 'pd' User Details: None, usage exactly as dictated by command entry pattern
 * (error-checked)
 */
public abstract class ViewCommand {

  protected Commands myCommandName;
  protected ViewTurtle myViewTurtle;
  protected ImageView myTurtleView;

  /**
   * Create new abstract ViewCommand
   *
   * @param viewTurtle ViewTurtle on which commands should be imparted
   */
  public ViewCommand(ViewTurtle viewTurtle) {
    myViewTurtle = viewTurtle;
    myTurtleView = myViewTurtle.getMyTurtleView();
  }
}
