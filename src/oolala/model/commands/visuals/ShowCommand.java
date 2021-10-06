package oolala.model.commands.visuals;

import oolala.model.Turtle;
import oolala.model.commands.Commands;

public class ShowCommand extends VisualCommand {

  public ShowCommand(Turtle myTurtle) {
    super(myTurtle);
    myCommandName = Commands.SHOW;
    displayTurtle();
  }
}
