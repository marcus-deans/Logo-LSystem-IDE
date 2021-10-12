package oolala.model.instructions;

import oolala.model.commands.Commands;

/**
 * @author marcusdeans Purpose: defined structured 'CreatureInstruction' that have the command as
 * well as movement value and conditional command to execute if needed Assumptions: Error-checked
 * inputs of String and pixels with appropriate call Dependencies: Commands, LogoInstruction Example
 * Usage: used for all commands in Darwin User Details: None, usage exactly as dictated by command
 * entry pattern (error-checked)
 */
public class CreatureInstruction extends LogoInstruction {

  /**
   * Create new CreatureInstruction
   * @param inCommand String to be assigned to CreatureInstruction
   * @param amount integer pixels for movement, for rotation, or for next command to execute
   */
  public CreatureInstruction(String inCommand, int amount) {
    super(inCommand, amount);
    checkCreatureCommand();
  }

  /**
   * Create new CreatureInstruction
   * @param inCommand String to be assigned to CreatureInstruction
   */
  public CreatureInstruction(String inCommand) {
    super(inCommand);
    checkCreatureCommand();
  }

  //determine the appropriate command based on Commands enum
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
