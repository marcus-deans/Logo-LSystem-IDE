package oolala.model.commands.conditionals;

import oolala.model.ModelCreature;
import oolala.model.commands.Commands;

public class IfSameConditionalCommand extends IfEntityConditionalCommand {

  boolean CHECKING_SAME_TYPE = true;

  public IfSameConditionalCommand(ModelCreature modelCreature, int nextCommand) {
    super(modelCreature, nextCommand);
    myCommandName = Commands.IFSAME;
    checkSame();
  }

  private void checkSame() {
    if(determineEntityPresentAndNature(CHECKING_SAME_TYPE)){
      executeSpecifiedInstruction();
    }
  }
}
