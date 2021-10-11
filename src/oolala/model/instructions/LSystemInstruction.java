package oolala.model.instructions;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Create an instruction for the turtle for perform an LSystem action Assumptions:
 * Functioning ModerlTurtle; other values error-checked Dependencies: Instruction Example Usage:
 * used for any LSystem command User Details: None, usage exactly as dictated by command entry
 * pattern (error-checked)
 */
public class LSystemInstruction extends Instruction {

  public int level;

  /**
   * Create a new LSystem instruction
   *
   * @param inLevel   level of the fractal
   * @param inCommand string of command name
   */
  public LSystemInstruction(int inLevel, String inCommand) {
    super(inCommand);
    level = inLevel;
  }

  /**
   * Create a new LSystem instruction
   *
   * @param inLevel   level of the fractal
   * @param inCommand string of command name
   * @param inPixels  amount of pixels for movement or degrees for rotation
   */
  public LSystemInstruction(int inLevel, String inCommand, int inPixels) {
    super(inCommand, inPixels);
    level = inLevel;
  }
}
