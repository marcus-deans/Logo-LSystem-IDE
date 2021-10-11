package oolala.model.commands.conditionals;

import oolala.model.ModelCreature;
import oolala.model.commands.Commands;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Create a conditional command to execute specified instruction rather than consecutive
 * Assumptions: Functioning ModelCreature and position; other values error-checked Dependencies:
 * ConditionalCommand, ModelCommand, ModelCreature Example Usage: used for 'go' command User
 * Details: None, usage exactly as dictated by command entry pattern (error-checked)
 */
public class GoConditionalCommand extends ConditionalCommand {

  /**
   * Create new GoConditionalCommand
   * @param modelCreature ModelCreature object on which command will be imparted
   * @param nextCommand conditional command that will be executed if conditions are met
   */
  public GoConditionalCommand(ModelCreature modelCreature, int nextCommand) {
    super(modelCreature, nextCommand);
    myCommandName = Commands.GO;
    checkPresent();
  }

  private void checkPresent() {
    //TODO: check that there is a valid next instruction specified -> maybe already be error-checked
    executeSpecifiedInstruction();
  }
}
