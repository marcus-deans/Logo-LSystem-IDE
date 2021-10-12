package oolala.view.darwin;

import oolala.view.ViewTurtle;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Create a view of the creature that will move around on the screen Assumptions:
 * Functioning coordinate system and commmands Dependencies: ViewTurtle Example Usage: Used in Logo
 * and LSystem programs to create the visual object that moves on the screen Details: None, usage
 * exactly as dictated by command entry pattern (error-checked)
 */
public class ViewCreature extends ViewTurtle {

  /**
   * Create new ViewCreature object
   *
   * @param id unique identifier for ViewCreature
   */
  public ViewCreature(int id) {
    super(id);
  }

}
