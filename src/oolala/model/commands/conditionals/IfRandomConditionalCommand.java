package oolala.model.commands.conditionals;

import oolala.model.ModelCreature;
import oolala.model.commands.Commands;

public class IfRandomConditionalCommand extends ConditionalCommand {

  double RANDOMNESS_THRESHOLD = 0.5;

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
