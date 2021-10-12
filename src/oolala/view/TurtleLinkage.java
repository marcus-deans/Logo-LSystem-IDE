package oolala.view;

import oolala.model.ModelTurtle;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Create an associate construct betwen the ViewTurtle and ModelTurtle for same turtle
 * Assumptions: Functioning coordinate system and commmands Dependencies: ModelTurtle, ViewTurtle
 * Example Usage: Used in Logo and LSystem programs to create the object that moves on the screen
 * Details: None, usage exactly as dictated by command entry pattern (error-checked)
 */
public class TurtleLinkage {

  public int myID;
  public ModelTurtle myModelTurtle;
  public ViewTurtle myViewTurtle;

  /**
   * Create new TurtleLinkage object
   *
   * @param id unique identifier for this TurtleLinkage (same given to its constituents)
   */
  public TurtleLinkage(int id) {
    myID = id;
    myModelTurtle = new ModelTurtle(id);
    myViewTurtle = new ViewTurtle(id);
  }

  /**
   * Update the ViewTurtle's values by obtaining them from the associated Model Turtle
   */
  public void update() {
    myViewTurtle.update(myModelTurtle);
  }
}
