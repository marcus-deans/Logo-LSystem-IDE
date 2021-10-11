package oolala.model.commands.conditionals;

import oolala.model.ModelCreature;
import oolala.model.commands.ModelCommand;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Abstract command for conditional commands that the ModelCreature may undertake
 * Assumptions: Functioning ModelCreature and position; other values error-checked Dependencies:
 * ModelCommand, ModelCreature Example Usage: used for 'go', 'ifempty', 'ifenemy', 'ifsame',
 * 'ifwall', 'ifrandom', 'ifentity' commands User Details: None, usage exactly as dictated by
 * command entry pattern (error-checked)
 */
public abstract class ConditionalCommand extends ModelCommand {

  protected static final int FULL_CIRCLE = 360;
  protected int myNextCommand;
  protected ModelCreature myModelCreature;
  protected int myNearbyThreshold;
  protected int myModelCreatureX;
  protected int myModelCreatureY;
  protected int myDegreesRotation;

  /**
   * Create new abstract ConditionalCommand
   *
   * @param modelCreature ModelCreature object on which command will be imparted
   * @param nextCommand   conditional comand that will be executed if conditions are met
   */
  public ConditionalCommand(ModelCreature modelCreature, int nextCommand) {
    super(modelCreature);
    myModelCreature = modelCreature;
    myNextCommand = nextCommand;
    myNearbyThreshold = myModelCreature.getMyNearbyThreshold();
    myModelCreatureX = myModelCreature.getTurtleCoordinates().turtleNewX;
    myModelCreatureY = myModelCreature.getTurtleCoordinates().turtleNewY;
    myDegreesRotation = myModelCreature.getDegreesRotation();
  }

  //execute the next instruction since conditionality has been met
  protected void executeSpecifiedInstruction() {
    myModelCreature.setMyNextInstructionIndex(myNextCommand);
  }

}
