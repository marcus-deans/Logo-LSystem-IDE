package oolala.model.instructions;

import oolala.model.commands.Commands;

/**
 * @author marcusdeans Purpose: defined structured 'LogoInstruction' that have the command as well
 * as movement value if needed Assumptions: Error-checked inputs of String and pixels with
 * appropriate call Dependencies: Commands Example Usage: used for all commands in Logo User
 * Details: None, usage exactly as dictated by command entry pattern (error-checked)
 */
public class LogoInstruction {

  public int pixels;
  public String command;
  public Commands order;

  /**
   * Create new LogoInstruction
   * @param inCommand String to be assigned to LogoInstruction
   * @param inPixels integer pixels for movement or for rotation
   */
  public LogoInstruction(String inCommand, int inPixels) {
    this(inCommand);
    this.pixels = inPixels;
  }

  /**
   * Create new LogoInstruction
   * @param inCommand String to be assigned to LogoInstruction
   */
  public LogoInstruction(String inCommand) {
    this.command = inCommand;
    computeCommands();
  }

  //determine the appropriate command based on Commands enum
  private void computeCommands() {
    switch (this.command) {
      case "fd" -> this.order = Commands.FORWARD;
      case "bk" -> this.order = Commands.BACKWARD;
      case "rt" -> this.order = Commands.RIGHT;
      case "lt" -> this.order = Commands.LEFT;
      case "home" -> this.order = Commands.HOME;
      case "ht" -> this.order = Commands.HIDE;
      case "st" -> this.order = Commands.SHOW;
      case "pd" -> this.order = Commands.PENDOWN;
      case "pu" -> this.order = Commands.PENUP;
      case "stamp" -> this.order = Commands.STAMP;
      case "tell" -> this.order = Commands.TELL;
      default -> this.order = Commands.OOPS;
    }
  }

}
