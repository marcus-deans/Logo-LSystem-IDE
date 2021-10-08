package oolala.model;

import oolala.model.commands.Commands;

public class CreatureInstruction extends Instruction {

  public CreatureInstruction(String inCommand, int amount) {
    super(inCommand, amount);
    checkCreatureCommand();
  }

  public CreatureInstruction(String inCommand) {
    super(inCommand);
    checkCreatureCommand();
  }

  private void checkCreatureCommand() {
    if (this.order == Commands.OOPS) {
      switch (this.command) {
        case "move" -> this.order = Commands.FORWARD;
        case "left" -> this.order = Commands.LEFT;
        case "right" -> this.order = Commands.RIGHT;
        case "infect" -> this.order = Commands.INFECT;
        case "ifempty" -> this.order = Commands.IFEMPTY;
        case "ifwall" -> this.order = Commands.IFWALL;
        case "ifsame" -> this.order = Commands.IFSAME;
        case "ifenemy" -> this.order = Commands.IFENEMY;
        case "ifrandom" -> this.order = Commands.IFRANDOM;
        case "go" -> this.order = Commands.GO;
      }
    }
  }
}
