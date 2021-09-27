package oolala.model;

public enum Commands {
  FORWARD ("fd"),
  BACK ("bk"),
  RIGHT ("rt"),
  LEFT ("lt"),
  HOME ("hm")
  ;

  private String commandString;

  Commands(String commandString) {
    this.commandString = commandString;
  }

  public String getString() {
    return this.commandString;
  }
}
