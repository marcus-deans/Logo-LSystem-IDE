package oolala.model.commands.rotations;

import oolala.model.Turtle;
import oolala.model.commands.Commands;

public class RotateLeftCommand extends RotateCommand {

  public RotateLeftCommand(Turtle myTurtle, int pixels) {
    super(myTurtle, pixels);
    myCommandName = Commands.LEFT;
    setLeftRotation();
  }

  private void setLeftRotation() {
    myTurtle.setDegreesRotation(myTurtle.getDegreesRotation() - pixels);
    setRotation();
  }
}
