package oolala.model.commands.conditionals;

import oolala.model.ModelCreature;

public class IfWallConditionalCommand extends ConditionalCommand {

  public IfWallConditionalCommand(ModelCreature modelCreature, int nextCommand) {
    super(modelCreature, nextCommand);
    checkWall();
  }

  private void checkWall() {
    //TODO: check whether space nearby ahead of creature is the world's boundary
    executeSpecifiedInstruction();
  }
}
