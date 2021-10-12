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

/**
 * @author marcusdeans
 * <p>
 * Purpose: Process CreatureInstructions that are created as part of Darwin Assumptions:Functioning
 * parsing system with accurate commands Dependencies: CreatureInstruction, CreatureLinkage, Group,
 * all Commands Example Usage: Used in Darwin to execute all of the commands that are specified for
 * creatures Details: None, usage exactly as dictated by command entry pattern (error-checked)
 */
public class DarwinInstructionProcessor {

  private ArrayList<CreatureInstruction> allInstructions;

  /**
   * Create new DarwinInstructionProcessor
   * @param currentInst current instruction that should be executed
   * @param creatureLinkage creatureLinkage associated with the CreatureInstruction
   * @param root JavaFX Group being used for the current program
   */
  public DarwinInstructionProcessor(CreatureInstruction currentInst,
      CreatureLinkage creatureLinkage,
      Group root) {
    performInstruction(currentInst, creatureLinkage, root);
  }

  /**
   * Create new DarwinInstructionProcessor
   * @param currentInst current instruction that should be executed
   * @param creatureLinkage creatureLinkage associated with the CreatureInstruction
   * @param root JavaFX Group being used for the current program
   * @param instructions list of additional instruction for the current object to be used in conditional commands
   */
  public DarwinInstructionProcessor(CreatureInstruction currentInst,
      CreatureLinkage creatureLinkage,
      Group root,
      ArrayList<CreatureInstruction> instructions) {
    this.allInstructions = instructions;
    performInstruction(currentInst, creatureLinkage, root);
  }

  /**
   * Create new DarwinInstructionProcessor
   * @param currentInst current instruction that should be executed
   *    * @param creatureLinkage creatureLinkage associated with the CreatureInstruction
   */
  public DarwinInstructionProcessor(CreatureInstruction currentInst,
      CreatureLinkage creatureLinkage) {
    Group root = new Group();
    performInstruction(currentInst, creatureLinkage, root);
  }

  //create an appropriate command corresponding to the already error-checked instruction
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
    }
    creatureLinkage.update();
  }
}
