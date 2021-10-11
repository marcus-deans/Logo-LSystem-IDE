package oolala.model.commands.conditionals;

import oolala.model.ModelCreature;
import oolala.model.commands.Commands;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Create a conditional command to execute if same species in front of ModelCreature
 * Assumptions: Functioning ModelCreature and position; other values error-checked Dependencies:
 * IfEntityConditionalComand, ConditionalCommand, ModelCommand, ModelCreature Example Usage: used
 * for 'ifsame' command User Details: None, usage exactly as dictated by command entry pattern
 * (error-checked)
 */
public class IfSameConditionalCommand extends IfEntityConditionalCommand {

  boolean CHECKING_SAME_TYPE = true;

  /**
   * Create new IfSameConditionalCommand
   * @param modelCreature ModelCreature object on which command will be imparted
   * @param nextCommand conditional command that will be executed if conditions are met
   */
  public IfSameConditionalCommand(ModelCreature modelCreature, int nextCommand) {
    super(modelCreature, nextCommand);
    myCommandName = Commands.IFSAME;
    checkSame();
  }

  //check if there is a same entity (same species) in front of the ModelCreature
  private void checkSame() {
    if (determineEntityPresentAndNature(CHECKING_SAME_TYPE)) {
      executeSpecifiedInstruction();
    }
  }
}
