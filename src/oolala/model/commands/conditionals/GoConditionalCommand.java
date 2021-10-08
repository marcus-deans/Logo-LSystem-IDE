package oolala.model.commands.conditionals;

import oolala.model.ModelCreature;
import oolala.model.commands.Commands;

public class GoConditionalCommand extends ConditionalCommand {

  public GoConditionalCommand(ModelCreature modelCreature, int nextCommand) {
    super(modelCreature, nextCommand);
    myCommandName = Commands.GO;
    checkPresent();
  }

  private void checkPresent() {
    //TODO: check that there is a valid next instruction specified
    executeSpecifiedInstruction();
  }
}
