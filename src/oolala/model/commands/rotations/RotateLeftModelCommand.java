package oolala.model.commands.rotations;

import oolala.model.ModelTurtle;
import oolala.model.commands.Commands;

public class RotateLeftModelCommand extends RotateModelCommand {

  public RotateLeftModelCommand(ModelTurtle myModelTurtle, int pixels) {
    super(myModelTurtle, pixels);
    myCommandName = Commands.LEFT;
    setLeftRotation();
  }

  private void setLeftRotation() {
    myModelTurtle.setDegreesRotation(myModelTurtle.getDegreesRotation() - pixels);
    setRotation();
  }
}
