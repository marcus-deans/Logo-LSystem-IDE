package oolala.model.commands.conditionals;

import oolala.model.ModelCreature;
import oolala.model.commands.Commands;

public class IfEnemyConditionalCommand extends ConditionalCommand {

  public IfEnemyConditionalCommand(ModelCreature modelCreature, int nextCommand) {
    super(modelCreature, nextCommand);
    myCommandName = Commands.IFENEMY;
    checkEnemy();
  }

  private void checkEnemy() {
    //TODO: check whether space nearby ahead of creature is occupied by different species
    executeSpecifiedInstruction();
  }
}
