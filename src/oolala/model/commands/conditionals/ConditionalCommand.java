package oolala.model.commands.conditionals;

import oolala.model.ModelCreature;
import oolala.model.commands.ModelCommand;

public abstract class ConditionalCommand extends ModelCommand {

  protected int myNextCommand;
  protected ModelCreature myModelCreature;
  protected int myNearbyThreshold;

  public ConditionalCommand(ModelCreature modelCreature, int nextCommand) {
    super(modelCreature);
    myModelCreature = modelCreature;
    myNextCommand = nextCommand;
    myNearbyThreshold = myModelCreature.getMyNearbyThreshold();
  }

  protected void executeSpecifiedInstruction() {
    myModelCreature.setMyNextInstructionIndex(myNextCommand);
  }

}
