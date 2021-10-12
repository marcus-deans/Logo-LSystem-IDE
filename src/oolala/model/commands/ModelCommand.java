package oolala.model.commands;

import oolala.model.ModelTurtle;

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

  /**
   * Create new abstract ModelCommand
   *
   * @param modelTurtle TurtleLinkage on which commands should be imparted
   * @param pixels      length or rotation amount
   */
  public ModelCommand(ModelTurtle modelTurtle, int pixels) {
    this(modelTurtle);
    this.pixels = pixels;
  }

  /**
   * Create new abstract ModelCommand
   *
   * @param modelTurtle TurtleLinkage on which commands should be imparted
   */
  public ModelCommand(ModelTurtle modelTurtle) {
    myModelTurtle = modelTurtle;
  }

}
