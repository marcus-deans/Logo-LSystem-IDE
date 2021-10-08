package oolala.model.commands.conditionals;

import oolala.model.ModelCreature;

public class IfRandomConditionalCommand extends ConditionalCommand {

  public IfRandomConditionalCommand(ModelCreature modelCreature, int nextCommand) {
    super(modelCreature, nextCommand);
    checkRandom();
  }

  private void checkRandom() {
    //TODO: perform the given instruction next half of the time (otherwise continue with the next instruction half the time)
    executeSpecifiedInstruction();
  }
}
