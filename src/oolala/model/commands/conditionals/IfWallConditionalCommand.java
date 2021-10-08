package oolala.model.commands.conditionals;

import java.util.List;
import oolala.model.ModelCreature;
import oolala.model.commands.Commands;
import oolala.view.displays.DarwinDisplay;

public class IfWallConditionalCommand extends ConditionalCommand {

  public IfWallConditionalCommand(ModelCreature modelCreature, int nextCommand) {
    super(modelCreature, nextCommand);
    myCommandName = Commands.IFWALL;
    if(checkWall()){
      executeSpecifiedInstruction();
    }
  }

  private boolean checkWall() {

}
