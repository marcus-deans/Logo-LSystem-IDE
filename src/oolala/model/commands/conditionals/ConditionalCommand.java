package oolala.model.commands.conditionals;

import oolala.model.ModelCreature;
import oolala.model.commands.ModelCommand;

public abstract class ConditionalCommand extends ModelCommand {

  protected int myNextCommand;

  public ConditionalCommand(ModelCreature modelCreature, int nextCommand) {
    super(modelCreature);
    myNextCommand = nextCommand;
  }
}
