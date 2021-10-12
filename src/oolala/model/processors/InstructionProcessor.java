package oolala.model.processors;

import javafx.scene.Group;
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

public class InstructionProcessor {

  public InstructionProcessor(Instruction currentInst, TurtleLinkage turtleLinkage,
      Group root) {
    performInstruction(currentInst, turtleLinkage, root);
  }

  private void performInstruction(Instruction currentInstruction, TurtleLinkage turtleLinkage,
      Group root) {
    int commandPixels = currentInstruction.pixels;
    switch (currentInstruction.order) {
      case PENUP -> new PenUpViewCommand(turtleLinkage);
      case FORWARD -> new ForwardModelCommand(turtleLinkage, commandPixels);
      case BACKWARD -> new BackwardModelCommand(turtleLinkage, commandPixels);
      case RIGHT -> new RotateRightModelCommand(turtleLinkage, commandPixels);
      case LEFT -> new RotateLeftModelCommand(turtleLinkage, commandPixels);
      case HOME -> new HomeModelCommand(turtleLinkage);
      case HIDE -> new HideViewCommand(turtleLinkage);
      case SHOW -> new ShowViewCommand(turtleLinkage);
      case STAMP -> new StampViewCommand(turtleLinkage, root);
      default -> {
      }
//      default -> myTurtle.execute(currentInstruction, root, lineRoot);
    }
    turtleLinkage.update();
  }
}
