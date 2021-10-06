package oolala.model.commands.rotations;

import oolala.model.Turtle;
import oolala.model.commands.Command;

public abstract class RotateCommand extends Command {
  private static final int FULL_CIRCLE = 360;

  public RotateCommand(Turtle myTurtle, int pixels) {
    super(myTurtle, pixels);
  }

  protected void setRotation() {
    myTurtle.setDegreesRotation(myTurtle.getDegreesRotation() % FULL_CIRCLE);
    myTurtle.performRotate();
  }
}
