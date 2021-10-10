package oolala.model.commands;

import javafx.scene.image.ImageView;
import oolala.view.ViewTurtle;

public abstract class ViewCommand {

  protected Commands myCommandName;
  protected int pixels;
  protected ViewTurtle myViewTurtle;
  ImageView myTurtleView;

  public ViewCommand(ViewTurtle viewTurtle) {
    myViewTurtle = viewTurtle;
    myTurtleView = myViewTurtle.getMyTurtleView();
  }

  protected void displayTurtle() {
    myTurtleView.setVisible(!myTurtleView.isVisible());
  }

  protected void updateTurtleOpacity() {
    myViewTurtle.changePenOpacity();
  }
}
