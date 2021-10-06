package oolala.model.commands;

import oolala.model.Turtle;

public abstract class Command {

  protected Commands myCommandName;
  protected int pixels;
  protected Turtle myTurtle;

  public Command(Turtle myTurtle, int pixels) {
    this(myTurtle);
    this.pixels = pixels;
  }

  public Command(Turtle myTurtle) {
    this.myTurtle = myTurtle;
  }
}
