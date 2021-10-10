package oolala.model.commands.visuals;

import oolala.model.commands.ViewCommand;
import oolala.view.ViewTurtle;

public abstract class VisibleViewCommand extends ViewCommand {

  public VisibleViewCommand(ViewTurtle viewTurtle) {
    super(viewTurtle);
  }

  protected void displayTurtle() {
    myTurtleView.setVisible(!myTurtleView.isVisible());
  }
}
