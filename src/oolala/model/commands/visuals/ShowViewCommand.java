package oolala.model.commands.visuals;

import oolala.model.commands.Commands;
import oolala.model.commands.ViewCommand;
import oolala.view.ViewTurtle;

public class ShowViewCommand extends ViewCommand {

  /**
   * Create a new Show command to shjow the turtle object on screen
   *
   * @param viewTurtle the turtle on which the action will be imparted
   */
  public ShowViewCommand(ViewTurtle viewTurtle) {
    super(viewTurtle);
    myCommandName = Commands.SHOW;
    displayTurtle();
  }
}
