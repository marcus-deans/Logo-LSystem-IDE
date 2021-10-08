package oolala.model.commands.conditionals;

import oolala.model.ModelCreature;
import oolala.model.commands.Commands;

public class IfSameConditionalCommand extends ConditionalCommand {

  public IfSameConditionalCommand(ModelCreature modelCreature, int nextCommand) {
    super(modelCreature, nextCommand);
    myCommandName = Commands.IFSAME;
    checkSame();
  }

  private void checkSame() {
    //TODO: check whether space nearby ahead of creature is occupied by creature of same species
    executeSpecifiedInstruction();
  }
}
