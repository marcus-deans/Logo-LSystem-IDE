package oolala.model;

import static oolala.view.LogoDisplay.COMMAND_HEIGHT;
import static oolala.view.LogoDisplay.FRAME_HEIGHT;
import static oolala.view.LogoDisplay.FRAME_WIDTH;

public class ModelTurtle {

  private static final String TURTLE_IMAGE = "turtle-picture.png";
  private static final double TURTLE_SIZE = 70;

  public static final int TurtleHomeX = (FRAME_WIDTH / 2);
  public static final int TurtleHomeY = ((FRAME_HEIGHT - 26 - COMMAND_HEIGHT + 15) / 2);

  public int myTurtleId;
  private int oldX;
  private int oldY;
  private int newX;
  private int newY;
  private int degreesRotation;

  //    public Turtle(int homeX, int homeY, int id) {
  public ModelTurtle(int id) {
    myTurtleId = id;
    degreesRotation = 0;

    oldX = TurtleHomeX;
    oldY = TurtleHomeY;
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
    return makeCoordinates();
  }

  private Coordinates makeCoordinates() {
    return new Coordinates(oldX, oldY, newX, newY);
  }

  public void setNewY(int newY) {
    this.newY = newY;
  }

  public void setNewX(int newX) {
    this.newX = newX;
  }

  public void updateCoordinates() {
    oldX = newX;
    oldY = newY;
  }

  public Coordinates getHomeCoordinates() {
    return new Coordinates(TurtleHomeX, TurtleHomeY);
  }

  //TODO: create map inside model (here)
  // then ViewTurtle can get the value from the map
  // as model updates values -> view gets updated values automatically (from binding)
}
