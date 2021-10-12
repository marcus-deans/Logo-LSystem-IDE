package oolala.model.processors;

import javafx.scene.Group;
import oolala.model.ModelTurtle;
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
import oolala.view.ViewTurtle;

public class InstructionProcessor {

  public InstructionProcessor(Instruction currentInst, TurtleLinkage turtleLinkage,
      Group root) {
    performInstruction(currentInst, turtleLinkage, root);
  }

  private void performInstruction(Instruction currentInstruction, TurtleLinkage turtleLinkage,
      Group root) {
    int commandPixels = currentInstruction.pixels;
    ModelTurtle myModelTurtle = turtleLinkage.myModelTurtle;
    ViewTurtle myViewTurtle = turtleLinkage.myViewTurtle;
    switch (currentInstruction.order) {
      case PENUP -> new PenUpViewCommand(myViewTurtle);
      case FORWARD -> new ForwardModelCommand(myModelTurtle, commandPixels);
      case BACKWARD -> new BackwardModelCommand(myModelTurtle, commandPixels);
      case RIGHT -> new RotateRightModelCommand(myModelTurtle, commandPixels);
      case LEFT -> new RotateLeftModelCommand(myModelTurtle, commandPixels);
      case HOME -> new HomeModelCommand(myModelTurtle);
      case HIDE -> new HideViewCommand(myViewTurtle);
      case SHOW -> new ShowViewCommand(myViewTurtle);
      case STAMP -> new StampViewCommand(myViewTurtle, root);
      default -> {
      }
//      default -> myTurtle.execute(currentInstruction, root, lineRoot);
    }
    turtleLinkage.update();
  }
}
