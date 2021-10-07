package oolala.view;

import oolala.model.ModelTurtle;

public class TurtleLinkage {

  public int myID;
  private final ModelTurtle myModelTurtle;
  private final ViewTurtle myViewTurtle;

  public TurtleLinkage(int id) {
    2
    myID = id;
    myModelTurtle = new ModelTurtle(id);
    myViewTurtle = new ViewTurtle(id);
  }

  public void update() {
//    myTurtle.update();
    myViewTurtle.update(myModelTurtle);
  }
}
