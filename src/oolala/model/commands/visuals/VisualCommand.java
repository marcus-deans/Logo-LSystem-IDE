package oolala.model.commands.visuals;

import javafx.scene.image.ImageView;
import oolala.model.Turtle;
import oolala.model.commands.Command;

public abstract class VisualCommand extends Command {

  ImageView myTurtleView;

  public VisualCommand(Turtle myTurtle) {
    super(myTurtle);
    myTurtleView = myTurtle.getMyTurtleView();
  }

  protected void displayTurtle() {
    myTurtleView.setVisible(!myTurtleView.isVisible());
  }
}
