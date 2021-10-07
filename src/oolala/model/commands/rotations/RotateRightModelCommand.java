package oolala.model.commands.rotations;

import oolala.model.ModelTurtle;
import oolala.model.commands.Commands;

public class RotateRightModelCommand extends RotateModelCommand {

  public RotateRightModelCommand(ModelTurtle myModelTurtle, int pixels) {
    super(myModelTurtle, pixels);
    myCommandName = Commands.RIGHT;
    setRightRotation();
  }

  private void setRightRotation() {
    myModelTurtle.setDegreesRotation(myModelTurtle.getDegreesRotation() + pixels);
    setRotation();
  }
}