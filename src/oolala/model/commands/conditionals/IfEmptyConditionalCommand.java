package oolala.model.commands.conditionals;

import java.util.ArrayList;
import javafx.scene.Group;
import oolala.model.commands.Commands;
import oolala.model.instructions.CreatureInstruction;
import oolala.view.darwin.CreatureLinkage;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Create a conditional command to execute if empty (no species) in front of ModelCreature
 * Assumptions: Functioning ModelCreature and position; other values error-checked Dependencies:
 * IfEntityConditionalCommand, ConditionalCommand, ModelCommand, ModelCreature Example Usage: used
 * for 'ifempty' command User Details: None, usage exactly as dictated by command entry pattern
 * (error-checked)
 */
public class IfEmptyConditionalCommand extends IfEntityConditionalCommand {

  boolean CHECKING_SAME_TYPE = true;
  boolean NOT_CHECKING_SAME_TYPE = false;

  /**
   * Create new IfEmptyConditionalCommand
   *
   * @param creatureLinkage ModelCreature object on which command will be imparted
   * @param nextCommand     conditional command that will be executed if conditions are met
   */
  public IfEmptyConditionalCommand(CreatureLinkage creatureLinkage, int nextCommand,
      ArrayList<CreatureInstruction> creatureInstructions, Group root) {
    super(creatureLinkage, nextCommand, creatureInstructions, root);
    myCommandName = Commands.IFEMPTY;
    checkEmpty();
  }

  //check whether the space in front of the turtle is empty
  private void checkEmpty() {
    if ((!determineEntityPresentAndNature(CHECKING_SAME_TYPE)) && (!determineEntityPresentAndNature(
        NOT_CHECKING_SAME_TYPE))) {
      executeSpecifiedInstruction();
    }
  }
}
