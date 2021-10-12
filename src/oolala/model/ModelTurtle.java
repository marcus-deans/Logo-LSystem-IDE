package oolala.model;

import static oolala.view.ViewTurtle.TURTLE_SIZE;
import static oolala.view.displays.LogoDisplay.COMMAND_HEIGHT;
import static oolala.view.displays.LogoDisplay.FRAME_HEIGHT;
import static oolala.view.displays.LogoDisplay.FRAME_WIDTH;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Create a model of the turtle that will move around on the screen Assumptions:
 * Functioning coordinate system and commmands Dependencies: None Example Usage: Used in Logo and
 * LSystem programs to create the object that moves on the screen Details: None, usage exactly as
 * dictated by command entry pattern (error-checked)
 */
public class ModelTurtle {

  private static final int TurtleHomeX = (FRAME_WIDTH / 2);
  private static final int TurtleHomeY = ((FRAME_HEIGHT - 26 - COMMAND_HEIGHT + 15) / 2);

  public int myTurtleId;
  protected int oldX;
  protected int oldY;
  protected int newX;
  protected int newY;
  protected int degreesRotation;

  protected int visualOldX;
  protected int visualOldY;
  protected int visualNewX;
  protected int visualNewY;

  private static final int NORTH = 0;
  private static final int SOUTH = 180;
  private static final int WEST = 270;
  private static final int EAST = 90;

  /**
   * Create new ModleTurtle
   *
   * @param id unique integer id to reference this ModelTurtle
   */
  public ModelTurtle(int id) {
    myTurtleId = id;
    degreesRotation = 0;
    oldX = TurtleHomeX;
    oldY = TurtleHomeY;
    visualOldX = correctXCoordinate(TurtleHomeX);
    visualOldY = correctYCoordinate(TurtleHomeY);

    newX = TurtleHomeX;
    newY = TurtleHomeY;
    visualNewX = correctXCoordinate(TurtleHomeX);
    visualNewY = correctYCoordinate(TurtleHomeY);
//        TurtleHomeY = (int) ((FRAME_HEIGHT-26-COMMAND_HEIGHT+15)/2 - this.myTurtleView.getFitHeight()/2);
//        TurtleHomeX = (int) (FRAME_WIDTH/2 - this.myTurtleView.getFitWidth()/2);
  }

  /**
   * Get degrees rotation
   *
   * @return integer degrees rotation
   */
  public int getDegreesRotation() {
    return degreesRotation;
  }

  /**
   * Set degrees rotation
   *
   * @param degreesRotation degrees rotation that should be set
   */
  public void setDegreesRotation(int degreesRotation) {
    this.degreesRotation = degreesRotation;
  }

  /**
   * Get coordinates of turtle
   *
   * @return Coordinates object containing Turtle's past and present condition
   */
  public Coordinates getTurtleCoordinates() {
    return makeCoordinates(oldX, oldY, newX, newY);
  }

  //general method to construct coordinates for the turtle's position
  private Coordinates makeCoordinates(int oldXValue, int oldYValue, int newXValue, int newYValue) {
    return new Coordinates(oldXValue, oldYValue, newXValue, newYValue);
  }

  /**
   * Update the Y value of the turtle
   *
   * @param newY integer value of new Y
   */
  public void setNewY(int newY) {
    this.newY = newY;
    visualNewY = correctYCoordinate(this.newY);
  }

  /**
   * Update the X value of the turtle
   *
   * @param newX integer value of new X
   */
  public void setNewX(int newX) {
    this.newX = newX;
    visualNewX = correctXCoordinate(this.newX);
  }

  /**
   * Update the visual Y value of the turtle
   *
   * @param visualNewY integer value of visual new Y
   */
  public void setVisualNewY(int visualNewY) {
    this.visualNewY = visualNewY;
  }

  /**
   * Update the visual X value of the turtle
   *
   * @param visualNewX integer value of visual new X
   */
  public void setVisualNewX(int visualNewX) {
    this.visualNewX = visualNewX;
  }

  /**
   * Update the coordinates of the turtle defined as setting the old coordinates equal to the new
   * coordinates
   */
  public void updateCoordinates() {
    oldX = newX;
    visualOldX = visualNewX;

    oldY = newY;
    visualOldY = visualNewY;
  }

  /**
   * Get the home coordinates of the turtle
   *
   * @return Coordinates object containing home X and home Y of the Turtle
   */
  public Coordinates getHomeCoordinates() {
    return new Coordinates(TurtleHomeX, TurtleHomeY);
  }

  /**
   * Get the visual coordinates of hte turtle
   *
   * @return Coordinates object containing visual old and new coordinates of the turtle
   */
  public Coordinates getTurtleVisualCoordinates() {
    return new Coordinates(visualOldX, visualOldY, visualNewX, visualNewY);
  }

  /**
   * Correct the x-coordinate of the turtle (move the visual coordinate to centre of turtle)
   *
   * @param currentX x position of the turtle presently
   * @return the adjusted integer value for the centre x of the turtle
   */
  protected int correctXCoordinate(int currentX) {
    return (int) (rightFacing() ? currentX + TURTLE_SIZE / 2 : currentX - TURTLE_SIZE / 2);
  }

  /**
   * Correct the y-coordinate of the turtle (move the visual coordinate to centre of turtle)
   *
   * @param currentY y position of the turtle presently
   * @return the adjusted integer value for the centre y of the turtle
   */
  protected int correctYCoordinate(int currentY) {
    return (int) (upwardFacing() ? currentY + TURTLE_SIZE / 2 : currentY - TURTLE_SIZE / 2);
  }

  //determine whether the turtle is facing to the right (if not it is facing left)
  private boolean rightFacing() {
    return (degreesRotation >= NORTH) && (degreesRotation <= SOUTH);
  }

  //determine whether the turtle is facing upwards (if not it is facing downwards)
  private boolean upwardFacing() {
    return (degreesRotation <= EAST) || (degreesRotation >= WEST);
  }
}
