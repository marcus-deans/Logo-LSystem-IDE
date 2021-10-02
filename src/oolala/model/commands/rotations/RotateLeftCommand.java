package oolala.model.commands.rotations;

import oolala.model.Turtle;

public class RotateLeftCommand extends RotateCommand{
  public RotateLeftCommand(Turtle myTurtle, int pixels){
    super(myTurtle, pixels);
    setLeftRotation();
  }

  private void setLeftRotation(){
    myTurtle.setDegreesRotation(myTurtle.getDegreesRotation()-pixels);
  }
}
