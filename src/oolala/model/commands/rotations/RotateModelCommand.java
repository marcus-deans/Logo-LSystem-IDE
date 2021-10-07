package oolala.model.commands.rotations;

import oolala.model.ModelTurtle;
import oolala.model.commands.ModelCommand;

public abstract class RotateModelCommand extends ModelCommand {

  private static final int FULL_CIRCLE = 360;

  public RotateModelCommand(ModelTurtle myModelTurtle, int pixels) {
    super(myModelTurtle, pixels);
  }

  protected void setRotation() {
    myModelTurtle.setDegreesRotation(myModelTurtle.getDegreesRotation() % FULL_CIRCLE);
//    myTurtle.performRotate();
  }
}
