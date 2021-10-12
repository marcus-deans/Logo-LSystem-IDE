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
import oolala.model.instructions.LogoInstruction;
import oolala.view.TurtleLinkage;
import oolala.view.ViewTurtle;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Process Instructions that are created as part of Logo or LSystem Assumptions:
 * Functioning parsing system with accurate commands Dependencies: Insturction, TurtleLinkage,
 * Group, all Commands Example Usage: Used in Logo and LSystem to execute all of the commands that
 * are specified for turtles Details: None, usage exactly as dictated by command entry pattern
 * (error-checked)
 */
public class InstructionProcessor {

  /**
   * Create new InstructionProcessor
   *
   * @param currentInst   current instruction that should be executed
   * @param turtleLinkage TurtleLinkage associated with the Instruction
   * @param root          JavaFX Group being used for the current program
   */
  public InstructionProcessor(LogoInstruction currentInst, TurtleLinkage turtleLinkage,
      Group root) {
    performInstruction(currentInst, turtleLinkage, root);
  }

  //create an appropriate command corresponding to the already error-checked instruction
  private void performInstruction(LogoInstruction currentInstruction, TurtleLinkage turtleLinkage,
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
    }
    turtleLinkage.update();
  }
}
