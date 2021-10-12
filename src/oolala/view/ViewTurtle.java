package oolala.view;


import static oolala.view.displays.LogoDisplay.COMMAND_HEIGHT;
import static oolala.view.displays.LogoDisplay.FRAME_HEIGHT;
import static oolala.view.displays.LogoDisplay.FRAME_WIDTH;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import oolala.model.ModelTurtle;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Create a view of the turtle that will move around on the screen Assumptions: Functioning
 * coordinate system and commmands Dependencies: None Example Usage: Used in Logo and LSystem
 * programs to create the visual object that moves on the screen Details: None, usage exactly as
 * dictated by command entry pattern (error-checked)
 */
public class ViewTurtle {

  private final int myID;
  public static final int TURTLE_SIZE = 40;
  private static final int OFFSET_Y = 15;
  private static final int OFFSET_Y_TOP = 26;

  private ImageView myTurtleView;
  private static final String TURTLE_IMAGE = "turtle-picture.png";

  private static final int TurtleHomeX = (FRAME_WIDTH / 2);
  private static final int TurtleHomeY = ((FRAME_HEIGHT - OFFSET_Y_TOP - COMMAND_HEIGHT + OFFSET_Y)
      / 2);

  private int myX;
  private int myY;
  private int myDegreesRotation;
  private boolean penOpacity;

  /**
   * Create new ViewTirtle
   *
   * @param id unique identifier of Turtle
   */
  public ViewTurtle(int id) {
    myID = id;
    penOpacity = true;
    initializeTurtleView();
  }

  //create and adjust image of Turtle
  private void initializeTurtleView() {
    myTurtleView = createTurtleView();
    myTurtleView.setX(TurtleHomeX);
    myTurtleView.setY(TurtleHomeY);
  }

  //create JavaFX ImageView of the Turtle with appropriate sizing
  public ImageView createTurtleView() {
    ImageView newTurtleView = new ImageView(new Image(TURTLE_IMAGE));
    newTurtleView.setFitHeight(TURTLE_SIZE);
    newTurtleView.setFitWidth(TURTLE_SIZE);

    return newTurtleView;
  }

  //perform rotation to adjust to obtained degrees rotation
  private void performRotate() {
    myTurtleView.setRotate(myDegreesRotation);
  }

  /**
   * Return ImageView of turtle
   *
   * @return JavaFX ImageView
   */
  public ImageView getMyTurtleView() {
    return myTurtleView;
  }

  //perform turtle movement to adjust to obtained coordinates
  private void performMovement() {
    myTurtleView.setX(myX);
    myTurtleView.setY(myY);
  }

  /**
   * Get updated coordiantes and degrees rotation from ModelTurtle; update visuals to match
   *
   * @param linkedModelTurtle the ModelTurtle associated with this ViewTurtle
   */
  public void update(ModelTurtle linkedModelTurtle) {
    myX = linkedModelTurtle.getTurtleCoordinates().turtleNewX;
    myY = linkedModelTurtle.getTurtleCoordinates().turtleNewY;
    myDegreesRotation = linkedModelTurtle.getDegreesRotation();
    performMovement();
    performRotate();
  }

  /**
   * Get the degrees rotation of the ViewTurtle
   *
   * @return the integer degrees rotation
   */
  public int getMyDegreesRotation() {
    return myDegreesRotation;
  }

  /**
   * Change the pen opacity of this ViewTurtle
   */
  public void changePenOpacity() {
    this.penOpacity = !(this.penOpacity);
  }

  /**
   * Get the pen opacity of this ViewTurtle
   *
   * @return the double value of pen opaciy
   */
  public double getPenOpacity() {
    return penOpacity ? 1.0 : 0.0;
  }
}
