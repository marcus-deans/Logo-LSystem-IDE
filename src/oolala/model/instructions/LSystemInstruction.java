package oolala.model.instructions;

/**
 * @author marcusdeans Purpose: defined structured 'LSystemInstruction' that have the command as
 * well as movement value if needed Assumptions: Error-checked inputs of String and pixels with
 * appropriate call Dependencies: Commands, LogoInstruction Example Usage: used for all commands in
 * LSystem User Details: None, usage exactly as dictated by command entry pattern (error-checked)
 */
public class LSystemInstruction extends LogoInstruction {

  public int level;

  /**
   * Create new LSystemInstruction
   *
   * @param inCommand String to be assigned to LSystemInstruction
   * @param inLevel   integer specifying which level of the fractal this instruction is for
   */
  public LSystemInstruction(int inLevel, String inCommand) {
    super(inCommand);
    level = inLevel;
  }

  /**
   * Create new LSystemInstruction
   *
   * @param inCommand String to be assigned to LSystemInstruction
   * @param inPixels  integer pixels for movement or for rotation
   * @param inLevel   integer specifying which level of the fractal this instruction is for
   */
  public LSystemInstruction(int inLevel, String inCommand, int inPixels) {
    super(inCommand, inPixels);
    level = inLevel;
  }
}
