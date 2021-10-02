package oolala.model.commands.rotations;

import oolala.model.Turtle;

public class RotateRightCommand extends RotateCommand{
  public RotateRightCommand(Turtle myTurtle, int pixels){
    super(myTurtle, pixels);
    setRightRotation();
  }

  private void setRightRotation(){
    myTurtle.setDegreesRotation(myTurtle.getDegreesRotation()+pixels);
  }
}