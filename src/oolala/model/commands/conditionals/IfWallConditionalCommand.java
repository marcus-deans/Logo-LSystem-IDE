package oolala.model.commands.conditionals;

import java.util.ArrayList;
import javafx.scene.Group;
import oolala.model.commands.Commands;
import oolala.model.instructions.CreatureInstruction;
import oolala.view.darwin.CreatureLinkage;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Create a conditional command to execute if wall in front of ModelCreature Assumptions:
 * Functioning ModelCreature and position; other values error-checked Dependencies:
 * ConditionalCommand, ModelCommand, ModelCreature Example Usage: used for 'ifwall' command User
 * Details: None, usage exactly as dictated by command entry pattern (error-checked)
 */
public class IfWallConditionalCommand extends ConditionalCommand {

  /**
   * Create new IfWallConditionalCommand
   *
   * @param creatureLinkage ModelCreature object on which command will be imparted
   * @param nextCommand     conditional command that will be executed if conditions are met
   */
  public IfWallConditionalCommand(CreatureLinkage creatureLinkage, int nextCommand,
      ArrayList<CreatureInstruction> creatureInstructions, Group root) {
    super(creatureLinkage, nextCommand, creatureInstructions, root);
    myCommandName = Commands.IFWALL;
    if (checkWall()) {
      executeSpecifiedInstruction();
    }
  }

  //determine if there is a wall present in field of view
  private boolean checkWall() {
    //TODO: check whether there is a wall present

    return true;
  }

}
