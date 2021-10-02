package oolala.model;

import static oolala.view.LogoDisplay.COMMAND_HEIGHT;
import static oolala.view.LogoDisplay.FRAME_HEIGHT;
import static oolala.view.LogoDisplay.FRAME_WIDTH;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Turtle {

    private int oldX;
    private int oldY;
    private int newX;
    private int newY;
    private int degreesRotation;

    private int length;
    private int yVector;
    private int xVector;

    private int TurtleHomeX;
    private int TurtleHomeY;

    public int myTurtleId;

    private ImageView myTurtleView;

    private static final String TURTLE_IMAGE = "turtle-picture.png";
    private final double TURTLE_SIZE = 70;

//    public Turtle(int homeX, int homeY, int id) {
    public Turtle(int id){
        myTurtleId = id;
        degreesRotation = 0;

        TurtleHomeX = (FRAME_WIDTH/2);
        TurtleHomeY = ((FRAME_HEIGHT-26-COMMAND_HEIGHT+15)/2);

        // make turtle shape and set property
        initializeTurtleView();

        oldX = TurtleHomeX;
        oldY = TurtleHomeY;

        myTurtleView.setX(FRAME_WIDTH/2);
        myTurtleView.setY(FRAME_HEIGHT/2);
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

//    public void execute(Instruction instruction, Group root, Group lineRoot) {
//        //Switch between the instructions
//        computeCoordinates(instruction);
//        switch (instruction.order) {
//            case FORWARD, BACKWARD -> setMovement();
//            case RIGHT, LEFT -> setRotation();
//            case HIDE, SHOW -> displayTurtle();
//            case STAMP -> stampTurtle(root, lineRoot);
//            default -> {
//            }
//        }
//    }

//
//    private void displayTurtle() {
//        myTurtleView.setVisible(!myTurtleView.isVisible());
//    }
//
//    private void stampTurtle(Group root, Group lineRoot) {
//        ImageView stampTurtleView = createTurtleView();
//        stampTurtleView.setRotate(degreesRotation);
//        root.getChildren().add(stampTurtleView);
//    }


//    private void computeCoordinates(Instruction instruction) {
//        length = instruction.pixels;
//        computeVectors();
//        switch (instruction.order) {
//            case FORWARD -> computeForwardCoordinates();
//            case BACKWARD -> computeBackwardCoordinates();
//            case HOME -> computeHomeCoordinates();
//            case RIGHT -> computeRightRotation();
//            case LEFT -> computeLeftRotation();
//            default -> {
//            }
//        }
//    }
//
//    private void computeVectors() {
//        xVector = checkVectors() ? (int) Math.sin(degreesRotation) * length : (int) Math.cos(degreesRotation) * length;
//        yVector = checkVectors() ? (int) Math.cos(degreesRotation) * length : (int) Math.sin(degreesRotation) * length;
//    }
//
//    public boolean checkVectors() {
//        degreesRotation %= 360;
//        return (degreesRotation <= 45) || (degreesRotation >= 315) || ((degreesRotation >= 135) && (degreesRotation <= 225));
//    }
//
//    public void updateCoordinates() {
//        oldX = newX;
//        oldY = newY;
//    }
//
//    private void computeForwardCoordinates() {
//        newX = rightFacing() ? oldX + xVector : oldX - xVector;
//        newY = upwardFacing() ? oldY - yVector : oldY + yVector;
//    }
//
//    private void computeBackwardCoordinates() {
//        newX = rightFacing() ? oldX - xVector : oldX + xVector;
//        newY = upwardFacing() ? oldY + yVector : oldY - yVector;
//    }
//
//    private void computeHomeCoordinates() {
//        newX = 0;
//        newY = 0;
//    }
//
//
//    private void setMovement() {
//        myTurtleView.setX(newX);
//        myTurtleView.setY(newY);
//    }
//
//    private boolean rightFacing() {
//        return (degreesRotation >= 0) && (degreesRotation <= 180);
//    }
//
//    private boolean upwardFacing() {
//        return (degreesRotation <= 90) || (degreesRotation >= 270);
//    }
//
//    private void computeRightRotation() {
//        degreesRotation += length;
//    }
//
//    private void computeLeftRotation() {
//        degreesRotation -= length;
//    }
//
//    private void setRotation() {
//        degreesRotation %= 360;
//        myTurtleView.setRotate(degreesRotation);
//    }

    public void setDegreesRotation(int degreesRotation) {
        this.degreesRotation = degreesRotation;
    }

    public int getDegreesRotation() {
        return degreesRotation;
    }

    public void performRotate(){
        myTurtleView.setRotate(degreesRotation);
    }

    public Coordinates getTurtleCoordinates(){
        return makeCoordinates();
    }

    private Coordinates makeCoordinates(){
        return new Coordinates(oldX, oldY, newX, newY);
    }

    public void performMovement(){
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



}
