package oolala.model.commands.conditionals;

import oolala.model.ModelCreature;
import oolala.model.commands.Commands;

public class IfEnemyConditionalCommand extends IfEntityConditionalCommand {

  boolean NOT_CHECKING_SAME_TYPE = false;

  public IfEnemyConditionalCommand(ModelCreature modelCreature, int nextCommand) {
    super(modelCreature, nextCommand);
    myCommandName = Commands.IFENEMY;
    checkEnemy();
  }

  private void checkEnemy() {
    if (determineEntityPresentAndNature(NOT_CHECKING_SAME_TYPE)) {
      executeSpecifiedInstruction();
    }
  }
}
