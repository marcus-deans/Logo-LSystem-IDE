package oolala.model.processors;

import javafx.scene.Group;
import oolala.model.commands.InfectCommand;
import oolala.model.commands.conditionals.GoConditionalCommand;
import oolala.model.commands.conditionals.IfEmptyConditionalCommand;
import oolala.model.commands.conditionals.IfEnemyConditionalCommand;
import oolala.model.commands.conditionals.IfRandomConditionalCommand;
import oolala.model.commands.conditionals.IfSameConditionalCommand;
import oolala.model.commands.conditionals.IfWallConditionalCommand;
import oolala.model.commands.movements.BackwardModelCommand;
import oolala.model.commands.movements.ForwardModelCommand;
import oolala.model.commands.movements.HomeModelCommand;
import oolala.model.commands.rotations.RotateLeftModelCommand;
import oolala.model.commands.rotations.RotateRightModelCommand;
import oolala.model.commands.visuals.HideViewCommand;
import oolala.model.commands.visuals.PenUpViewCommand;
import oolala.model.commands.visuals.ShowViewCommand;
import oolala.model.commands.visuals.StampViewCommand;
import oolala.model.instructions.Instruction;
import oolala.view.TurtleLinkage;
import oolala.view.darwin.CreatureLinkage;

public class InstructionProcessor {

  public InstructionProcessor(Instruction currentInst, TurtleLinkage myTurtLink, Group root) {
    performInstruction(currentInst, myTurtLink, root);
  }

  private void performInstruction(Instruction currentInstruction, CreatureLinkage myTurtLink,
      Group root) {
    int commandPixels = currentInstruction.pixels;
    switch (currentInstruction.order) {
      case PENUP -> new PenUpViewCommand(myTurtLink);
      case FORWARD -> new ForwardModelCommand(myTurtLink, commandPixels);
      case BACKWARD -> new BackwardModelCommand(myTurtLink, commandPixels);
      case RIGHT -> new RotateRightModelCommand(myTurtLink, commandPixels);
      case LEFT -> new RotateLeftModelCommand(myTurtLink, commandPixels);
      case HOME -> new HomeModelCommand(myTurtLink);
      case HIDE -> new HideViewCommand(myTurtLink);
      case SHOW -> new ShowViewCommand(myTurtLink);
      case STAMP -> new StampViewCommand(myTurtLink, root);
      case INFECT -> new InfectCommand(myTurtLink);
      case IFEMPTY -> new IfEmptyConditionalCommand(myTurtLink, commandPixels);
      case IFWALL -> new IfWallConditionalCommand(myTurtLink, commandPixels);
      case IFSAME -> new IfSameConditionalCommand(myTurtLink, commandPixels);
      case IFENEMY -> new IfEnemyConditionalCommand(myTurtLink, commandPixels);
      case IFRANDOM -> new IfRandomConditionalCommand(myTurtLink, commandPixels);
      case GO -> new GoConditionalCommand(myTurtLink, commandPixels);
      default -> {
      }
//      default -> myTurtle.execute(currentInstruction, root, lineRoot);
    }
    myTurtLink.update();
  }
}
