package oolala.model.commands.rotations;

import oolala.model.Turtle;
import oolala.model.commands.Commands;

public class RotateRightCommand extends RotateCommand {

  public RotateRightCommand(Turtle myTurtle, int pixels) {
    super(myTurtle, pixels);
    myCommandName = Commands.RIGHT;
    setRightRotation();
  }

  private void setRightRotation() {
    myTurtle.setDegreesRotation(myTurtle.getDegreesRotation() + pixels);
    setRotation();
  }
}