package oolala.view;

import oolala.model.Turtle;

public class TurtleLinkage {

  public int myID;
  private final Turtle myTurtle;
  private final ViewTurtle myViewTurtle;

  public TurtleLinkage(int id) {
    myID = id;
    myTurtle = new Turtle(id);
    myViewTurtle = new ViewTurtle(id);
  }

  public void update() {
//    myTurtle.update();
    myViewTurtle.update(myTurtle);
  }
}
