package oolala.model.commands.conditionals;

import oolala.model.ModelCreature;
import oolala.model.commands.Commands;

public class IfWallConditionalCommand extends ConditionalCommand {

  public IfWallConditionalCommand(ModelCreature modelCreature, int nextCommand) {
    super(modelCreature, nextCommand);
    myCommandName = Commands.IFWALL;
    if (checkWall()) {
      executeSpecifiedInstruction();
    }
  }

  private boolean checkWall() {
    //TODO: check whether there is a wall present

    return true;
  }

}
