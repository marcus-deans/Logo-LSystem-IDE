package oolala.model;

public class Instruction{
  public int pixels;
  public String command;
  public Commands cmd;

  public Instruction(String inCommand, int inPixels){
    this.pixels = inPixels;
    this.command = inCommand;
    computeCommands();
  }

  public Instruction(String inCommand){
    this.command = inCommand;
    computeCommands();
  }

  private void computeCommands(){
    switch(this.command){
      case "fd":
        this.cmd = Commands.FORWARD;
        break;
      default:
        this.cmd = Commands.HOME;
    }
  }

}
