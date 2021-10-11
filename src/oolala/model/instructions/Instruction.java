package oolala.model.instructions;

import oolala.model.commands.Commands;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Create an instruction for the Turtle for perform an action Assumptions: Functioning
 * ModelTurtle; other values error-checked Dependencies: Commands Example Usage: used within any
 * ModelTurtle command and many ModelCreature commands User Details: None, usage exactly as dictated
 * by command entry pattern (error-checked)
 */
public class Instruction {

  public int pixels;
  public String command;
  public Commands order;

  /**
   * Create new instruction
   * @param inCommand string of command to create
   * @param inPixels amount of pixels for movement, or degrees for rotation
   */
  public Instruction(String inCommand, int inPixels) {
    this(inCommand);
    this.pixels = inPixels;
  }

  /**
   * Create new insruction
   * @param inCommand string of command to create
   */
  public Instruction(String inCommand) {
    this.command = inCommand;
    computeCommands();
  }

  //use the enum to determine whch command the string corresponds to
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
