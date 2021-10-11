package oolala.view;

import oolala.model.ModelTurtle;

public class TurtleLinkage {

  public int myID;
  public ModelTurtle myModelTurtle;
  public ViewTurtle myViewTurtle;

  public TurtleLinkage(int id) {
    myID = id;
    myModelTurtle = new ModelTurtle(id);
    myViewTurtle = new ViewTurtle(id);
  }

  public TurtleLinkage() {
  }

  public void update() {
//    myTurtle.update();
    myViewTurtle.update(myModelTurtle);
  }
}
