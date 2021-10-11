package oolala.model.commands;

/**
 * Enum to describe the the different commands for better formatting
 */
public enum Commands {
  FORWARD("fd"),
  BACKWARD("bk"),
  RIGHT("rt"),
  LEFT("lt"),
  HOME("home"),
  HIDE("ht"),
  SHOW("st"),
  STAMP("stamp"),
  TELL("tell"),
  PENUP("pu"),
  PENDOWN("pd"),
  INFECT("infect"),
  IFEMPTY("ifempty"),
  IFWALL("ifempty"),
  IFSAME("ifsame"),
  IFENEMY("ifenemy"),
  IFRANDOM("ifrandom"),
  GO("go"),
  OOPS("oops");

  private final String commandString;

  Commands(String commandString) {
    this.commandString = commandString;
  }

  /**
   * Get the string associated with command
   *
   * @return String that was specified for the command
   */
  public String getString() {
    return this.commandString;
  }
}
