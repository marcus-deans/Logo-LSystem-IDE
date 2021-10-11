package oolala.model.commands.conditionals;

import java.util.ArrayList;
import oolala.model.ModelCreature;
import oolala.model.commands.ModelCommand;
import oolala.model.instructions.CreatureInstruction;
import oolala.model.processors.InstructionProcessor;
import oolala.view.darwin.CreatureLinkage;

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
  protected CreatureLinkage myCreatureLinkage;
  protected int myNearbyThreshold;
  protected int myModelCreatureX;
  protected int myModelCreatureY;
  protected int myDegreesRotation;
  protected ArrayList<CreatureInstruction> myCreatureInstructions;

  /**
   * Create new abstract ConditionalCommand
   *
   * @param creatureLinkage ModelCreature object on which command will be imparted
   * @param nextCommand     conditional comand that will be executed if conditions are met
   */
  public ConditionalCommand(CreatureLinkage creatureLinkage, int nextCommand,
      ArrayList<CreatureInstruction> creatureInstructions) {
    super(creatureLinkage);
    myCreatureLinkage = creatureLinkage;
    myModelCreature = creatureLinkage.myModelCreature;
    myNextCommand = nextCommand;
    myCreatureInstructions = creatureInstructions;

    myNearbyThreshold = myModelCreature.getMyNearbyThreshold();
    myModelCreatureX = myModelCreature.getTurtleCoordinates().turtleNewX;
    myModelCreatureY = myModelCreature.getTurtleCoordinates().turtleNewY;
    myDegreesRotation = myModelCreature.getDegreesRotation();
  }

  //execute the next instruction since conditionality has been met
  protected void executeSpecifiedInstruction() {
    CreatureInstruction instructionToExecute = myCreatureInstructions.get(myNextCommand);
    InstructionProcessor processInstruction = new InstructionProcessor(instructionToExecute,
        myCreatureLinkage);
//    myModelCreature.setMyNextInstructionIndex(myNextCommand);
  }

}
