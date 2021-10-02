package oolala.model.commands.rotations;

import oolala.model.Turtle;
import oolala.model.commands.Command;

public abstract class RotateCommand extends Command {
  public RotateCommand(Turtle myTurtle, int pixels){
    super(myTurtle, pixels);
  }

  protected void setRotation() {
    myTurtle.setDegreesRotation(myTurtle.getDegreesRotation()%360);
    myTurtle.performRotate();
  }
}
