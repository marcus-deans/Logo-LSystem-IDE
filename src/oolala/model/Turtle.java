package oolala.model;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Turtle {

  public int oldX;
  public int oldY;
  public int newX;
  public int newY;
  public int degreesRotation;

  private int length;
  private int yVector;
  private int xVector;

  private double TurtleHomeX;
  private double TurtleHomeY;

  public int myId;

  private ImageView myTurtleView;

  private static final String TURTLE_IMAGE = "turtle-picture.png";
  private final double TURTLE_SIZE = 70;

  public Turtle(int homeX, int homeY, int id) {
    TurtleHomeX = homeX;
    TurtleHomeY = homeY;
    myId = id;
    this.oldX = homeX;
    this.oldY = homeY;
    this.newX = 0;
    this.newY = 0;
    // make turtle shape and set property
    initializeTurtleView();
  }

  public void execute(Instruction instruction, Group root, Group lineRoot) {
    //Switch between the instructions
    computeCoordinates(instruction);
    switch(instruction.command){
      case "fd", "bk" -> setMovement();
      case "rt", "lt" -> setRotation();
      case "ht", "st" -> displayTurtle();
      case "stamp" -> stampTurtle(root, lineRoot);
      default -> {
      }
    }
  }

  private void initializeTurtleView(){
    myTurtleView = createTurtleView();
    myTurtleView.setX(TurtleHomeX);
    myTurtleView.setY(TurtleHomeY);
  }


  private ImageView createTurtleView(){
    ImageView newTurtleView = new ImageView(new Image(TURTLE_IMAGE));
    newTurtleView.setFitHeight(TURTLE_SIZE);
    newTurtleView.setFitWidth(TURTLE_SIZE);
    return newTurtleView;
  }

  private void displayTurtle() {
    myTurtleView.setVisible(!myTurtleView.isVisible());
  }

  private void stampTurtle(Group root, Group lineRoot){
    ImageView stampTurtleView = createTurtleView();
    stampTurtleView.setRotate(degreesRotation);
    root.getChildren().add(stampTurtleView);
  }

  public ImageView getMyTurtleView(){
    return myTurtleView;
  }

  private void computeCoordinates(Instruction instruction) {
    length = instruction.pixels;
    xVector = (int) Math.sin(degreesRotation) * length;
    yVector = (int) Math.cos(degreesRotation) * length;
    switch (instruction.command) {
      case "fd" -> computeForwardCoordinates();
      case "bk" -> computeBackwardCoordinates();
      case "home" -> computeHomeCoordinates();
      case "rt" -> computeRightRotation();
      case "lt" -> computeLeftRotation();
      default -> {
      }
    }
  }

  public void updateCoordinates(){
    oldX = newX;
    oldY = newY;
  }

  private void computeForwardCoordinates() {
    newX = rightFacing() ? oldX + xVector : oldX - xVector;
    newY = upwardFacing() ? oldY - yVector : oldY + yVector;
  }

  private void computeBackwardCoordinates() {
    newX = rightFacing() ? oldX - xVector : oldX + xVector;
    newY = upwardFacing() ? oldY + yVector : oldY - yVector;
  }

  private void computeHomeCoordinates() {
    newX = 0;
    newY = 0;
  }

  private void computeRightRotation() {
    degreesRotation += length;
  }

  private void computeLeftRotation() {
    degreesRotation -= length;
  }

  private void setRotation(){
    degreesRotation %= 360;
    myTurtleView.setRotate(degreesRotation);
  }

  private void setMovement(){
    myTurtleView.setX(newX);
    myTurtleView.setY(newY);
  }

  private boolean rightFacing() {
    return (degreesRotation >= 0) && (degreesRotation <= 180);
  }

  private boolean upwardFacing() {
    return (degreesRotation <= 90) || (degreesRotation >= 270);
  }
}
