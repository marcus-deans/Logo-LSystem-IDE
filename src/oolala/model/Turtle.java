package oolala.model;

import static oolala.view.LogoDisplay.COMMAND_HEIGHT;
import static oolala.view.LogoDisplay.FRAME_HEIGHT;
import static oolala.view.LogoDisplay.FRAME_WIDTH;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Turtle {

  private static final String TURTLE_IMAGE = "turtle-picture.png";
  private static final double TURTLE_SIZE = 70;

  private static final int TurtleHomeX = (FRAME_WIDTH / 2);
  private static final int TurtleHomeY = ((FRAME_HEIGHT - 26 - COMMAND_HEIGHT + 15) / 2);

  public int myTurtleId;
  private int oldX;
  private int oldY;
  private int newX;
  private int newY;
  private int degreesRotation;
  private ImageView myTurtleView;

  //    public Turtle(int homeX, int homeY, int id) {
  public Turtle(int id) {
    myTurtleId = id;
    degreesRotation = 0;

    // make turtle shape and set property
    initializeTurtleView();

    oldX = TurtleHomeX;
    oldY = TurtleHomeY;

    myTurtleView.setX(FRAME_WIDTH / 2.0);
    myTurtleView.setY(FRAME_HEIGHT / 2.0);
//        TurtleHomeY = (int) ((FRAME_HEIGHT-26-COMMAND_HEIGHT+15)/2 - this.myTurtleView.getFitHeight()/2);
//        TurtleHomeX = (int) (FRAME_WIDTH/2 - this.myTurtleView.getFitWidth()/2);
  }

  private void initializeTurtleView() {
    myTurtleView = createTurtleView();
    myTurtleView.setX(TurtleHomeX);
    myTurtleView.setY(TurtleHomeY);
  }

  public ImageView createTurtleView() {
    ImageView newTurtleView = new ImageView(new Image(TURTLE_IMAGE));
    newTurtleView.setFitHeight(TURTLE_SIZE);
    newTurtleView.setFitWidth(TURTLE_SIZE);
    return newTurtleView;
  }

  public int getDegreesRotation() {
    return degreesRotation;
  }

  public void setDegreesRotation(int degreesRotation) {
    this.degreesRotation = degreesRotation;
  }

  public void performRotate() {
    myTurtleView.setRotate(degreesRotation);
  }

  public Coordinates getTurtleCoordinates() {
    return makeCoordinates();
  }

  private Coordinates makeCoordinates() {
    return new Coordinates(oldX, oldY, newX, newY);
  }

  public void performMovement() {
    myTurtleView.setX(newX);
    myTurtleView.setY(newY);
  }

  public void setNewY(int newY) {
    this.newY = newY;
  }

  public void setNewX(int newX) {
    this.newX = newX;
  }

  public ImageView getMyTurtleView() {
    return myTurtleView;
  }

  public void updateCoordinates() {
    oldX = newX;
    oldY = newY;
  }
}
