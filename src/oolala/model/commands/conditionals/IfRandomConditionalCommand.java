package oolala.model.commands.conditionals;

import oolala.model.ModelCreature;
import oolala.model.commands.Commands;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Create a conditional command to execute next command randomly (50% of time) Assumptions:
 * Functioning ModelCreature; other values error-checked Dependencies: ConditionalCommand,
 * ModelCommand, ModelCreature Example Usage: used for 'ifrandom' command User Details: None, usage
 * exactly as dictated by command entry pattern (error-checked)
 */
public class IfRandomConditionalCommand extends ConditionalCommand {

  double RANDOMNESS_THRESHOLD = 0.5;

  /**
   * Create new IfRandomConditionalCommand
   * @param modelCreature ModelCreature object on which command will be imparted
   * @param nextCommand conditional command that will be executed if conditions are met
   */
  public IfRandomConditionalCommand(ModelCreature modelCreature, int nextCommand) {
    super(modelCreature, nextCommand);
    myCommandName = Commands.IFRANDOM;
    checkRandom();
  }

  private void checkRandom() {
    double randomSelection = Math.random();
    if (randomSelection >= RANDOMNESS_THRESHOLD) {
      executeSpecifiedInstruction();
    }
  }
}
