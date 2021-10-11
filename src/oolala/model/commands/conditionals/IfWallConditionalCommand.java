package oolala.model.commands.conditionals;

import oolala.model.ModelCreature;
import oolala.model.commands.Commands;

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
   * @param modelCreature ModelCreature object on which command will be imparted
   * @param nextCommand conditional command that will be executed if conditions are met
   */
  public IfWallConditionalCommand(ModelCreature modelCreature, int nextCommand) {
    super(modelCreature, nextCommand);
    myCommandName = Commands.IFWALL;
    if (checkWall()) {
      executeSpecifiedInstruction();
    }
  }

  //determine if there is a wall present in field of view
  private boolean checkWall() {
    //TODO: check whether there is a wall present
<<<<<<< HEAD
=======

>>>>>>> d78dfef445eb4985d0d788eea8cca607f4abc428
    return true;
  }

}
