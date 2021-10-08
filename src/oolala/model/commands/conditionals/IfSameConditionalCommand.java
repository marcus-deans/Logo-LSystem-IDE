package oolala.model.commands.conditionals;

import oolala.model.ModelCreature;

public class IfSameConditionalCommand extends ConditionalCommand {

  public IfSameConditionalCommand(ModelCreature modelCreature, int nextCommand) {
    super(modelCreature, nextCommand);
    checkSame();
  }

  private void checkSame() {
    //TODO: check whether space nearby ahead of creature is occupied by creature of same species
    executeSpecifiedInstruction();
  }
}
