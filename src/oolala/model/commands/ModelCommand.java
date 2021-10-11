package oolala.model.commands;

import oolala.model.ModelTurtle;
import oolala.view.TurtleLinkage;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Create a command that will act on the ModelTurtle object Assumptions: Functioning
 * ModelTurtle; other values error-checked Dependencies: ModelTurtle Example Usage: used for 'lt',
 * 'rt', 'hm', 'fd', 'bk' User Details: None, usage exactly as dictated by command entry pattern
 * (error-checked)
 */
public abstract class ModelCommand {

  protected Commands myCommandName;
  protected int pixels;
  protected ModelTurtle myModelTurtle;
  protected TurtleLinkage myTurtleLinkage;

  /**
   * Create new abstract ModelCommand
   *
   * @param turtleLinkage TurtleLinkage on which commands should be imparted
   * @param pixels        length or rotation amount
   */
  public ModelCommand(TurtleLinkage turtleLinkage, int pixels) {
    this(turtleLinkage);
    this.pixels = pixels;
  }

  /**
   * Create new abstract ModelCommand
   *
   * @param turtleLinkage TurtleLinkage on which commands should be imparted
   */
  public ModelCommand(TurtleLinkage turtleLinkage) {
    myTurtleLinkage = turtleLinkage;
    this.myModelTurtle = myTurtleLinkage.myModelTurtle;
  }

}
