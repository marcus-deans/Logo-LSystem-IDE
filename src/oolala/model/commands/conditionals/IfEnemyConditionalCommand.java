package oolala.model.commands.conditionals;

import java.util.ArrayList;
import javafx.scene.Group;
import oolala.model.commands.Commands;
import oolala.model.instructions.CreatureInstruction;
import oolala.view.darwin.CreatureLinkage;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Create a conditional command to execute if different species in front of ModelCreature
 * Assumptions: Functioning ModelCreature and position; other values error-checked Dependencies:
 * IfEntityConditionalCommand, ConditionalCommand, ModelCommand, ModelCreature Example Usage: used
 * for 'ifempty' command User Details: None, usage exactly as dictated by command entry pattern
 * (error-checked)
 */
public class IfEnemyConditionalCommand extends IfEntityConditionalCommand {

  boolean NOT_CHECKING_SAME_TYPE = false;

  /**
   * Create new IfEnemyConditionalCommand
   *
   * @param creatureLinkage ModelCreature object on which command will be imparted
   * @param nextCommand     conditional command that will be executed if conditions are met
   */
  public IfEnemyConditionalCommand(CreatureLinkage creatureLinkage, int nextCommand,
      ArrayList<CreatureInstruction> creatureInstructions, Group root) {
    super(creatureLinkage, nextCommand, creatureInstructions, root);
    myCommandName = Commands.IFENEMY;
    checkEnemy();
  }

  //check whether there is an enemy (an entity of different species) in front
  private void checkEnemy() {
    if (determineEntityPresentAndNature(NOT_CHECKING_SAME_TYPE)) {
      executeSpecifiedInstruction();
    }
  }
}
