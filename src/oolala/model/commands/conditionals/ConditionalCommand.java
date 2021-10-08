package oolala.model.commands.conditionals;

import oolala.model.ModelCreature;
import oolala.model.commands.ModelCommand;

public abstract class ConditionalCommand extends ModelCommand {

  protected int myNextCommand;
  protected ModelCreature myModelCreature;
  protected int myNearbyThreshold;
  protected int myModelCreatureX;
  protected int myModelCreatureY;
  protected int myDegreesRotation;
  protected static final int FULL_CIRCLE = 360;

  public ConditionalCommand(ModelCreature modelCreature, int nextCommand) {
    super(modelCreature);
    myModelCreature = modelCreature;
    myNextCommand = nextCommand;
    myNearbyThreshold = myModelCreature.getMyNearbyThreshold();
    myModelCreatureX = myModelCreature.getTurtleCoordinates().turtleNewX;
    myModelCreatureY = myModelCreature.getTurtleCoordinates().turtleNewY;
    myDegreesRotation = myModelCreature.getDegreesRotation();
  }

  protected void executeSpecifiedInstruction() {
    myModelCreature.setMyNextInstructionIndex(myNextCommand);
  }

}
