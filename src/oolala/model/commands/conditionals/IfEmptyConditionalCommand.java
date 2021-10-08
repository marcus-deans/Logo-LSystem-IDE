package oolala.model.commands.conditionals;

import oolala.model.ModelCreature;

public class IfEmptyConditionalCommand extends ConditionalCommand {

  public IfEmptyConditionalCommand(ModelCreature modelCreature, int nextCommand) {
    super(modelCreature, nextCommand);
    checkEmpty();
  }

  private void checkEmpty() {
    //TODO: check whether space nearby ahead of creature is empty and inside world's boundary
    executeSpecifiedInstruction();
  }
}
