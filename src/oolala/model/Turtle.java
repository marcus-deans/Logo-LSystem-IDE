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
        degreesRotation = 0;
        this.oldX = homeX;
        this.oldY = homeY;
        this.newX = homeX;
        this.newY = homeY;
        // make turtle shape and set property
        initializeTurtleView();
    }

    public void execute(Instruction instruction, Group root, Group lineRoot) {
        //Switch between the instructions
        computeCoordinates(instruction);
        switch (instruction.order) {
            case FORWARD, BACKWARD -> setMovement();
            case RIGHT, LEFT -> setRotation();
            case HIDE, SHOW -> displayTurtle();
            case STAMP -> stampTurtle(root, lineRoot);
            default -> {
            }
        }
    }

    private void initializeTurtleView() {
        myTurtleView = createTurtleView();
        myTurtleView.setX(TurtleHomeX);
        myTurtleView.setY(TurtleHomeY);
    }


    private ImageView createTurtleView() {
        ImageView newTurtleView = new ImageView(new Image(TURTLE_IMAGE));
        newTurtleView.setFitHeight(TURTLE_SIZE);
        newTurtleView.setFitWidth(TURTLE_SIZE);
        return newTurtleView;
    }

    private void displayTurtle() {
        myTurtleView.setVisible(!myTurtleView.isVisible());
    }

    private void stampTurtle(Group root, Group lineRoot) {
        ImageView stampTurtleView = createTurtleView();
        stampTurtleView.setRotate(degreesRotation);
        root.getChildren().add(stampTurtleView);
    }

    public ImageView getMyTurtleView() {
        return myTurtleView;
    }

    private void computeCoordinates(Instruction instruction) {
        length = instruction.pixels;
        computeVectors();
        switch (instruction.order) {
            case FORWARD -> computeForwardCoordinates();
            case BACKWARD -> computeBackwardCoordinates();
            case HOME -> computeHomeCoordinates();
            case RIGHT -> computeRightRotation();
            case LEFT -> computeLeftRotation();
            default -> {
            }
        }
    }

    private void computeVectors() {
        xVector = checkVectors() ? (int) Math.sin(degreesRotation) * length : (int) Math.cos(degreesRotation) * length;
        yVector = checkVectors() ? (int) Math.cos(degreesRotation) * length : (int) Math.sin(degreesRotation) * length;
    }

    public boolean checkVectors() {
        degreesRotation %= 360;
        return (degreesRotation <= 45) || (degreesRotation >= 315) || ((degreesRotation >= 135) && (degreesRotation <= 225));
    }

    public void updateCoordinates() {
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

    private void setRotation() {
        degreesRotation %= 360;
        myTurtleView.setRotate(degreesRotation);
    }

    private void setMovement() {
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
