package oolala.model;

public class Instruction{
  public int pixels;
  public String command;

  public Instruction(int inPixels, String inCommand){
    pixels = inPixels;
    command = inCommand;
  }

  public Instruction(String inCommand){
    command = inCommand;
  }

}
