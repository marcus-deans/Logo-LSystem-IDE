package oolala.model;

import oolala.model.commands.Commands;

public class Instruction {

  public int pixels;
  public String command;
  public Commands order;

  public Instruction(String inCommand, int inPixels) {
    this(inCommand);
    this.pixels = inPixels;
  }

  public Instruction(String inCommand) {
    this.command = inCommand;
    computeCommands();
  }

  private void computeCommands() {
    switch (this.command) {
      case "fd" -> this.order = Commands.FORWARD;
      case "bk" -> this.order = Commands.BACKWARD;
      case "rt" -> this.order = Commands.RIGHT;
      case "lt" -> this.order = Commands.LEFT;
      case "home" -> this.order = Commands.HOME;
      case "ht" -> this.order = Commands.HIDE;
      case "st" -> this.order = Commands.SHOW;
      case "stamp" -> this.order = Commands.STAMP;
      case "tell" -> this.order = Commands.TELL;
      default -> this.order = Commands.OOPS;
    }
  }

}
