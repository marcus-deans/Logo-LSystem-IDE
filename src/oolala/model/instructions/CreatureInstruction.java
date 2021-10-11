package oolala.model.instructions;

import oolala.model.commands.Commands;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Create an instruction for the creature for perform an action Assumptions: Functioning
 * ModelCreature; other values error-checked Dependencies: Instruction, Commands Example Usage: used
 * within any ModelCreature specific command (conditionals) command User Details: None, usage
 * exactly as dictated by command entry pattern (error-checked)
 */
public class CreatureInstruction extends Instruction {

  /**
   * Create new creature instruction
   * @param inCommand string of command
   * @param amount integer index of command to execute
   */
  public CreatureInstruction(String inCommand, int amount) {
    super(inCommand, amount);
    checkCreatureCommand();
  }

  /**
   * Create new creature instruction
   * @param inCommand integer index of command to execute
   */
  public CreatureInstruction(String inCommand) {
    super(inCommand);
    checkCreatureCommand();
  }

  //use the commands enum to classify this instruction
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
