package oolala.model.commands.visuals;

import oolala.model.commands.ViewCommand;
import oolala.view.ViewTurtle;

public abstract class PenViewCommand extends ViewCommand {

  public PenViewCommand(ViewTurtle viewTurtle) {
    super(viewTurtle);
  }

  protected void updateTurtleOpacity() {
    myViewTurtle.changePenOpacity();
  }
}
