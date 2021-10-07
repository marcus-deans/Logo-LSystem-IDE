package oolala.model.commands.visuals;

import oolala.model.commands.Commands;
import oolala.model.commands.ViewCommand;
import oolala.view.ViewTurtle;

public class ShowViewCommand extends ViewCommand {

  public ShowViewCommand(ViewTurtle viewTurtle) {
    super(viewTurtle);
    myCommandName = Commands.SHOW;
    displayTurtle();
  }
}
