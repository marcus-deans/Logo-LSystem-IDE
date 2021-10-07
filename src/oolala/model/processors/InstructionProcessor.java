package oolala.model.processors;

import javafx.scene.Group;
import oolala.model.Instruction;
import oolala.model.commands.movements.BackwardModelCommand;
import oolala.model.commands.movements.ForwardModelCommand;
import oolala.model.commands.movements.HomeModelCommand;
import oolala.model.commands.rotations.RotateLeftModelCommand;
import oolala.model.commands.rotations.RotateRightModelCommand;
import oolala.model.commands.visuals.HideViewCommand;
import oolala.model.commands.visuals.PenDownViewCommand;
import oolala.model.commands.visuals.PenUpViewCommand;
import oolala.model.commands.visuals.ShowViewCommand;
import oolala.model.commands.visuals.StampViewCommand;
import oolala.view.TurtleLinkage;

public class InstructionProcessor {

  public InstructionProcessor(Instruction currentInst, TurtleLinkage myTurtLink, Group root) {
    performInstruction(currentInst, myTurtLink, root);
  }

  private void performInstruction(Instruction currentInstruction, TurtleLinkage myTurtLink,
      Group root) {
    int commandPixels = currentInstruction.pixels;
    switch (currentInstruction.order) {
      case PENUP -> new PenUpViewCommand(myTurtLink.myViewTurtle);
      //penOpacity = NO_OPACITY;
      case PENDOWN -> new PenDownViewCommand(myTurtLink.myViewTurtle);
      //penOpacity = FULL_OPACITY;
      case FORWARD -> new ForwardModelCommand(myTurtLink.myModelTurtle, commandPixels);
      case BACKWARD -> new BackwardModelCommand(myTurtLink.myModelTurtle, commandPixels);
      case RIGHT -> new RotateRightModelCommand(myTurtLink.myModelTurtle, commandPixels);
      case LEFT -> new RotateLeftModelCommand(myTurtLink.myModelTurtle, commandPixels);
      case HOME -> new HomeModelCommand(myTurtLink.myModelTurtle, 0);
      case HIDE -> new HideViewCommand(myTurtLink.myViewTurtle);
      case SHOW -> new ShowViewCommand(myTurtLink.myViewTurtle);
      case STAMP -> new StampViewCommand(myTurtLink.myViewTurtle, root);
      default -> {
      }
//      default -> myTurtle.execute(currentInstruction, root, lineRoot);
    }
    myTurtLink.update();
  }
}
