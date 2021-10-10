package oolala.model.commands;

import javafx.scene.image.ImageView;
import oolala.view.ViewTurtle;

public abstract class ViewCommand {

  protected Commands myCommandName;
  protected ViewTurtle myViewTurtle;
  protected ImageView myTurtleView;

  public ViewCommand(ViewTurtle viewTurtle) {
    myViewTurtle = viewTurtle;
    myTurtleView = myViewTurtle.getMyTurtleView();
  }
}
