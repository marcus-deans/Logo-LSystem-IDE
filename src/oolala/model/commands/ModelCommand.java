package oolala.model.commands;

import oolala.model.ModelTurtle;

public abstract class ModelCommand {

  protected Commands myCommandName;
  protected int pixels;
  protected ModelTurtle myModelTurtle;

  public ModelCommand(ModelTurtle myModelTurtle, int pixels) {
    this(myModelTurtle);
    this.pixels = pixels;
  }

  public ModelCommand(ModelTurtle myModelTurtle) {
    this.myModelTurtle = myModelTurtle;
  }

}
