package oolala.model.commands.conditionals;

import oolala.model.ModelCreature;

public class IfEnemyConditionalCommand extends ConditionalCommand {

  public IfEnemyConditionalCommand(ModelCreature modelCreature, int nextCommand) {
    super(modelCreature, nextCommand);
    checkEnemy();
  }

  private void checkEnemy() {
    //TODO: check whether space nearby ahead of creature is occupied by different species
    executeSpecifiedInstruction();
  }
}
