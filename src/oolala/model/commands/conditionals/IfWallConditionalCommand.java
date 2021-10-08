package oolala.model.commands.conditionals;

import oolala.model.ModelCreature;
import oolala.model.commands.Commands;

public class IfWallConditionalCommand extends ConditionalCommand {

  public IfWallConditionalCommand(ModelCreature modelCreature, int nextCommand) {
    super(modelCreature, nextCommand);
    myCommandName = Commands.IFWALL;
    checkWall();
  }

  private void checkWall() {
    //TODO: check whether space nearby ahead of creature is the world's boundary
    executeSpecifiedInstruction();
  }
}
