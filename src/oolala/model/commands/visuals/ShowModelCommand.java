package oolala.model.commands.visuals;

import oolala.model.commands.Commands;
import oolala.model.commands.ViewCommand;
import oolala.view.ViewTurtle;

public class ShowModelCommand extends ViewCommand {

  public ShowModelCommand(ViewTurtle viewTurtle) {
    super(viewTurtle);
    myCommandName = Commands.SHOW;
    displayTurtle();
  }
}
