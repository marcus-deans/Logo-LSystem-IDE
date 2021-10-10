package oolala.model.commands.rotations;

import oolala.model.ModelTurtle;
import oolala.model.commands.Commands;

public class RotateRightModelCommand extends RotateModelCommand {

  public RotateRightModelCommand(ModelTurtle myModelTurtle, int pixels) {
    super(myModelTurtle, pixels);
    myCommandName = Commands.RIGHT;
    setRightRotation();
  }

  public void setRightRotation() { //made public for testing purposes
    myModelTurtle.setDegreesRotation(myModelTurtle.getDegreesRotation() + pixels);
    setRotation();
  }
}