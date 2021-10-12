package oolala.model.processors;

import java.util.ArrayList;
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
import oolala.model.commands.visuals.PenDownViewCommand;
import oolala.model.commands.visuals.PenUpViewCommand;
import oolala.model.commands.visuals.ShowViewCommand;
import oolala.model.commands.visuals.StampViewCommand;
import oolala.model.instructions.CreatureInstruction;
import oolala.view.darwin.CreatureLinkage;

public class DarwinInstructionProcessor {

  private ArrayList<CreatureInstruction> allInstructions;

  public DarwinInstructionProcessor(CreatureInstruction currentInst,
      CreatureLinkage creatureLinkage,
      Group root) {
    performInstruction(currentInst, creatureLinkage, root);
  }

  public DarwinInstructionProcessor(CreatureInstruction currentInst,
      CreatureLinkage creatureLinkage,
      Group root,
      ArrayList<CreatureInstruction> instructions) {
    this.allInstructions = instructions;
    performInstruction(currentInst, creatureLinkage, root);
  }

  public DarwinInstructionProcessor(CreatureInstruction currentInst,
      CreatureLinkage creatureLinkage) {
    Group root = new Group();
    performInstruction(currentInst, creatureLinkage, root);
  }

  private void performInstruction(CreatureInstruction currentInstruction,
      CreatureLinkage creatureLinkage,
      Group root) {
    int commandPixels = currentInstruction.pixels;
    switch (currentInstruction.order) {
      case TELL -> {
      }
      case PENUP -> new PenUpViewCommand(creatureLinkage);
      case FORWARD -> new ForwardModelCommand(creatureLinkage, commandPixels);
      case BACKWARD -> new BackwardModelCommand(creatureLinkage, commandPixels);
      case RIGHT -> new RotateRightModelCommand(creatureLinkage, commandPixels);
      case LEFT -> new RotateLeftModelCommand(creatureLinkage, commandPixels);
      case HOME -> new HomeModelCommand(creatureLinkage);
      case HIDE -> new HideViewCommand(creatureLinkage);
      case SHOW -> new ShowViewCommand(creatureLinkage);
      case STAMP -> new StampViewCommand(creatureLinkage, root);
      case PENDOWN -> new PenDownViewCommand(creatureLinkage);
      case INFECT -> new InfectCommand(creatureLinkage);
      case IFEMPTY -> new IfEmptyConditionalCommand(creatureLinkage, commandPixels,
          allInstructions, root);
      case IFWALL -> new IfWallConditionalCommand(creatureLinkage, commandPixels, allInstructions,
          root);
      case IFSAME -> new IfSameConditionalCommand(creatureLinkage, commandPixels, allInstructions,
          root);
      case IFENEMY -> new IfEnemyConditionalCommand(creatureLinkage, commandPixels,
          allInstructions, root);
      case IFRANDOM -> new IfRandomConditionalCommand(creatureLinkage, commandPixels,
          allInstructions, root);
      case GO -> new GoConditionalCommand(creatureLinkage, commandPixels, allInstructions, root);
      case OOPS -> {
      }
      default -> {
      }
//      default -> myTurtle.execute(currentInstruction, root, lineRoot);
    }
    creatureLinkage.update();
  }
}
