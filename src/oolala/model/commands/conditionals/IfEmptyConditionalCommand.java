package oolala.model.commands.conditionals;

import oolala.model.ModelCreature;
import oolala.model.commands.Commands;

public class IfEmptyConditionalCommand extends IfEntityConditionalCommand {

  boolean CHECKING_SAME_TYPE = true;
  boolean NOT_CHECKING_SAME_TYPE = false;


  public IfEmptyConditionalCommand(ModelCreature modelCreature, int nextCommand) {
    super(modelCreature, nextCommand);
    myCommandName = Commands.IFEMPTY;
    checkEmpty();
  }

  private void checkEmpty() {
    if ((!determineEntityPresentAndNature(CHECKING_SAME_TYPE)) && (!determineEntityPresentAndNature(NOT_CHECKING_SAME_TYPE))) {
      executeSpecifiedInstruction();
    }
  }
}
