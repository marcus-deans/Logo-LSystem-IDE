package oolala.model.processors;

import java.util.ArrayList;
import javafx.scene.Group;
import oolala.model.ModelCreature;
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
    ModelCreature myModelCreature = creatureLinkage.myModelCreature;
    switch (currentInstruction.order) {
      case FORWARD -> new ForwardModelCommand(myModelCreature, commandPixels);
      case BACKWARD -> new BackwardModelCommand(myModelCreature, commandPixels);
      case RIGHT -> new RotateRightModelCommand(myModelCreature, commandPixels);
      case LEFT -> new RotateLeftModelCommand(myModelCreature, commandPixels);
      case HOME -> new HomeModelCommand(myModelCreature);
      case INFECT -> new InfectCommand(myModelCreature);
      case IFEMPTY -> new IfEmptyConditionalCommand(myModelCreature, commandPixels,
          allInstructions, root);
      case IFWALL -> new IfWallConditionalCommand(myModelCreature, commandPixels, allInstructions,
          root);
      case IFSAME -> new IfSameConditionalCommand(myModelCreature, commandPixels, allInstructions,
          root);
      case IFENEMY -> new IfEnemyConditionalCommand(myModelCreature, commandPixels,
          allInstructions, root);
      case IFRANDOM -> new IfRandomConditionalCommand(myModelCreature, commandPixels,
          allInstructions, root);
      case GO -> new GoConditionalCommand(myModelCreature, commandPixels, allInstructions, root);
      default -> {
      }
//      default -> myTurtle.execute(currentInstruction, root, lineRoot);
    }
    creatureLinkage.update();
  }
}
