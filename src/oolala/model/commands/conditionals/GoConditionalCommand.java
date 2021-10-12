package oolala.model.commands.conditionals;

import java.util.ArrayList;
import javafx.scene.Group;
import oolala.model.ModelCreature;
import oolala.model.commands.Commands;
import oolala.model.instructions.CreatureInstruction;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Create a conditional command to execute specified instruction rather than consecutive
 * Assumptions: Functioning ModelCreature and position; other values error-checked Dependencies:
 * ConditionalCommand, ModelCommand, ModelCreature Example Usage: used for 'go' command User
 * Details: None, usage exactly as dictated by command entry pattern (error-checked)
 */
public class GoConditionalCommand extends ConditionalCommand {

  /**
   * Create new GoConditionalCommand
   *
   * @param modelCreature ModelCreature object on which command will be imparted
   * @param nextCommand   conditional command that will be executed if conditions are met
   */
  public GoConditionalCommand(ModelCreature modelCreature, int nextCommand,
      ArrayList<CreatureInstruction> creatureInstructions, Group root) {
    super(modelCreature, nextCommand, creatureInstructions, root);
    myCommandName = Commands.GO;
    checkPresent();
  }

  //confirm that there is a valid next instruction present, then execute the instruction
  private void checkPresent() {
    //TODO: check that there is a valid next instruction specified -> maybe already be error-checked
    executeSpecifiedInstruction();
  }
}
