package oolala.model.commands;

public enum Commands {
  FORWARD ("fd"),
  BACKWARD ("bk"),
  RIGHT ("rt"),
  LEFT ("lt"),
  HOME ("home"),
  HIDE ("ht"),
  SHOW ("st"),
  STAMP("stamp"),
  OOPS("oops")
  ;

  private String commandString;

  Commands(String commandString) {
    this.commandString = commandString;
  }

  public String getString() {
    return this.commandString;
  }
}
