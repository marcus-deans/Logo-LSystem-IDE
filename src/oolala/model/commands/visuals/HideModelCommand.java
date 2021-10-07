package oolala.model.commands.visuals;

import oolala.model.commands.Commands;
import oolala.model.commands.ViewCommand;
import oolala.view.ViewTurtle;

public class HideModelCommand extends ViewCommand {

  public HideModelCommand(ViewTurtle viewTurtle) {
    super(viewTurtle);
    myCommandName = Commands.HIDE;
    displayTurtle();
  }
}
