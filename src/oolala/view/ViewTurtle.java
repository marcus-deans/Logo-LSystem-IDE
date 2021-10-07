package oolala.view;


import static oolala.view.LogoDisplay.COMMAND_HEIGHT;
import static oolala.view.LogoDisplay.FRAME_HEIGHT;
import static oolala.view.LogoDisplay.FRAME_WIDTH;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import oolala.model.ModelTurtle;

public class ViewTurtle {

  public int myID;
  private ImageView myTurtleView;
  private static final String TURTLE_IMAGE = "turtle-picture.png";
  private static final double TURTLE_SIZE = 70;
  private static final int OFFSET_Y = 15;
  private static final int OFFSET_Y_TOP = 26;

  private static final int TurtleHomeX = (FRAME_WIDTH / 2);
  private static final int TurtleHomeY = ((FRAME_HEIGHT - OFFSET_Y_TOP - COMMAND_HEIGHT + OFFSET_Y)
      / 2);

  private int myX;
  private int myY;
  private int myDegreesRotation;
  private boolean penState;

  public ViewTurtle(int id) {
    myID = id;
    // make turtle shape and set property
    initializeTurtleView();

    myTurtleView.setX(FRAME_WIDTH / 2.0);
    myTurtleView.setY(FRAME_HEIGHT / 2.0);
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

  public void performRotate() {
    myTurtleView.setRotate(myDegreesRotation);
  }

  public ImageView getMyTurtleView() {
    return myTurtleView;
  }

  public void performMovement() {
    myTurtleView.setX(myX);
    myTurtleView.setY(myY);
  }

  public void update(ModelTurtle linkedModelTurtle) {
    myX = linkedModelTurtle.getTurtleCoordinates().turtleNewX;
    myY = linkedModelTurtle.getTurtleCoordinates().turtleNewY;
    myDegreesRotation = linkedModelTurtle.getDegreesRotation();
    performMovement();
    performRotate();
  }

  public int getMyDegreesRotation() {
    return myDegreesRotation;
  }
}
