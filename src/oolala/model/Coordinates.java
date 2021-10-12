package oolala.model;

/**
 * @author marcusdeans Purpose: to declare the turtle's original and target x and y coordinates
 * Assumptions: Functioning ModelTurtle; and other values error-checked Dependencies: None Example
 * Usage: used for computeForwardCoordinates() to declare myTurtleOldX User Details: None, usage
 * exactly as dictated by command entry pattern (error-checked)
 */
public class Coordinates {

  public int turtleOldX;
  public int turtleOldY;
  public int turtleNewX;
  public int turtleNewY;

  /**
   * Constructor to create coordinates to set variables for the turtle's
   * first x and y position and the target x and y position
   * @param oldX sets the current x coordinate for the turtle
   * @param oldY sets the current y coordinate for the turtle
   * @param newX sets the new x coordinate for the turtle
   * @param newY sets the new y coordinate for the turtle
   */
  public Coordinates(int oldX, int oldY, int newX, int newY) {
    turtleOldX = oldX;
    turtleOldY = oldY;
    turtleNewX = newX;
    turtleNewY = newY;
  }

  /**
   * Constructor to create coordinates to set variables for the turtle's new x and y location
   * @param newX sets the turtle's new x location
   * @param newY set the turtle's new y location
   */
  public Coordinates(int newX, int newY) {
    turtleNewX = newX;
    turtleNewY = newY;
  }
}
