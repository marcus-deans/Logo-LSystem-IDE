package oolala.model.commands.conditionals;

import oolala.model.ModelCreature;
import oolala.model.commands.Commands;

public class IfEmptyConditionalCommand extends ConditionalCommand {

  public IfEmptyConditionalCommand(ModelCreature modelCreature, int nextCommand) {
    super(modelCreature, nextCommand);
    myCommandName = Commands.IFEMPTY;
    checkEmpty();
  }

  private void checkEmpty() {
    //TODO: check whether space nearby ahead of creature is empty and inside world's boundary
    executeSpecifiedInstruction();
  }
}
