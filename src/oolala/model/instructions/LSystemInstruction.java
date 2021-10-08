package oolala.model.instructions;

public class LSystemInstruction extends Instruction{
  public int level;

  public LSystemInstruction(int inLevel, String inCommand){
    super(inCommand);
    level = inLevel;
  }

  public LSystemInstruction(int inLevel, String inCommand, int inPixels){
    super(inCommand, inPixels);
    level = inLevel;
  }
}
