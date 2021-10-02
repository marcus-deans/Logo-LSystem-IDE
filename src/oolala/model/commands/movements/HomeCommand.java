package oolala.model.commands.movements;

import static oolala.view.LogoDisplay.COMMAND_HEIGHT;
import static oolala.view.LogoDisplay.FRAME_HEIGHT;
import static oolala.view.LogoDisplay.FRAME_WIDTH;

import oolala.model.Turtle;

public class HomeCommand extends MovementCommand{
  int myTurtleHomeX;
  int myTurtleHomeY;

  public HomeCommand(Turtle myTurtle, int pixels) {
    super(myTurtle, pixels);
    determineHomeCoordinates();
    computeHomeCoordinates();
    setNewCoordinates();
    performMovement();
  }

  private void determineHomeCoordinates(){
//    myTurtleHomeX = frameWidth/2;
//    myTurtleHomeY = frameWidth/2;
    myTurtleHomeX = (int) (FRAME_WIDTH/2 - myTurtle.getMyTurtleView().getFitWidth()/2);
    myTurtleHomeY = (int) ((FRAME_HEIGHT-26-COMMAND_HEIGHT+15)/2 - myTurtle.getMyTurtleView().getFitHeight()/2);
  }

  private void computeHomeCoordinates() {
    myNewX = 0;
    myNewY = 0;
  }
}
