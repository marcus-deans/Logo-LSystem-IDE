package oolala.model.commands.visuals;

import oolala.model.Turtle;
import oolala.model.commands.Commands;

public class HideCommand extends VisualCommand {
  public HideCommand(Turtle myTurtle) {
    super(myTurtle);
    myCommandName = Commands.HIDE;
    displayTurtle();
  }
}
