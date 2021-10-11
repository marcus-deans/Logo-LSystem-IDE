package oolala.model;

import static oolala.view.ViewTurtle.TURTLE_SIZE;
import static oolala.view.displays.LogoDisplay.COMMAND_HEIGHT;
import static oolala.view.displays.LogoDisplay.FRAME_HEIGHT;
import static oolala.view.displays.LogoDisplay.FRAME_WIDTH;

public class ModelTurtle {

  private static final String TURTLE_IMAGE = "turtle-picture.png";

  public static final int TurtleHomeX = (FRAME_WIDTH / 2);
  public static final int TurtleHomeY = ((FRAME_HEIGHT - 26 - COMMAND_HEIGHT + 15) / 2);

  public int myTurtleId;
  private int oldX;
  private int oldY;
  private int newX;
  private int newY;
  private int degreesRotation;

  private int visualOldX;
  private int visualOldY;
  private int visualNewX;
  private int visualNewY;

  public static final int NORTH = 0;
  public static final int SOUTH = 180;
  public static final int WEST = 270;
  public static final int EAST = 90;

  //    public Turtle(int homeX, int homeY, int id) {
  public ModelTurtle(int id) {
    myTurtleId = id;
    degreesRotation = 0;
    oldX = TurtleHomeX;
    oldY = TurtleHomeY;
    visualOldX = correctXCoordinate(TurtleHomeX);
    visualOldY = correctYCoordinate(TurtleHomeY);
//        TurtleHomeY = (int) ((FRAME_HEIGHT-26-COMMAND_HEIGHT+15)/2 - this.myTurtleView.getFitHeight()/2);
//        TurtleHomeX = (int) (FRAME_WIDTH/2 - this.myTurtleView.getFitWidth()/2);
  }

  public int getDegreesRotation() {
    return degreesRotation;
  }

  public void setDegreesRotation(int degreesRotation) {
    this.degreesRotation = degreesRotation;
  }

  public Coordinates getTurtleCoordinates() {
    return makeCoordinates(oldX, oldY, newX, newY);
  }

  private Coordinates makeCoordinates(int oldXValue, int oldYValue, int newXValue, int newYValue) {
    return new Coordinates(oldXValue, oldYValue, newXValue, newYValue);
  }

  public void setNewY(int newY) {
    this.newY = newY;
    visualNewY = correctYCoordinate(this.newY);
  }

  public void setNewX(int newX) {
    this.newX = newX;
    visualNewX = correctXCoordinate(this.newX);
  }

  public void setVisualNewY(int visualNewY) {
    this.visualNewY = visualNewY;
  }

  public void setVisualNewX(int visualNewX) {
    this.visualNewX = visualNewX;
  }

  public void updateCoordinates() {
    oldX = newX;
    visualOldX = visualNewX;

    oldY = newY;
    visualOldY = visualNewY;
  }

  public Coordinates getHomeCoordinates() {
    return new Coordinates(TurtleHomeX, TurtleHomeY);
  }

  public void setTurtleCoordinates(Coordinates newTurtleCoordinates) {
    this.oldX = newTurtleCoordinates.turtleOldX;
    this.oldY = newTurtleCoordinates.turtleOldY;
    this.newX = newTurtleCoordinates.turtleNewX;
    this.newY = newTurtleCoordinates.turtleNewY;
  }

  public Coordinates getTurtleVisualCoordinates() {
    return new Coordinates(visualOldX, visualOldY, visualNewX, visualNewY);
  }

  //TODO: compute actual coordinates based on vectors
  private int correctXCoordinate(int currentX) {
    return (int) (rightFacing() ? currentX + TURTLE_SIZE / 2 : currentX - TURTLE_SIZE / 2);
  }

  private int correctYCoordinate(int currentY) {
    return (int) (upwardFacing() ? currentY + TURTLE_SIZE / 2 : currentY - TURTLE_SIZE / 2);
  }

  private boolean rightFacing() {
    return (degreesRotation >= NORTH) && (degreesRotation <= SOUTH);
  }

  private boolean upwardFacing() {
    return (degreesRotation <= EAST) || (degreesRotation >= WEST);
  }
  //TODO: create map inside model (here)
  // then ViewTurtle can get the value from the map
  // as model updates values -> view gets updated values automatically (from binding)
}
