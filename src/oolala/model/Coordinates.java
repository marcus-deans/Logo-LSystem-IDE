package oolala.model;

public class Coordinates {

  public int turtleOldX;
  public int turtleOldY;
  public int turtleNewX;
  public int turtleNewY;

  public Coordinates(int oldX, int oldY, int newX, int newY) {
    turtleOldX = oldX;
    turtleOldY = oldY;
    turtleNewX = newX;
    turtleNewY = newY;
  }

  public Coordinates(int newX, int newY) {
    turtleNewX = newX;
    turtleNewY = newY;
  }
}
