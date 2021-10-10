package oolala.model.commands.visuals;

import oolala.model.commands.Commands;
import oolala.model.commands.ViewCommand;
import oolala.view.ViewTurtle;

public class PenUpViewCommand extends ViewCommand {

  public PenUpViewCommand(ViewTurtle viewTurtle) {
    super(viewTurtle);
    myCommandName = Commands.PENUP;
    updateTurtleOpacity();
  }
}
