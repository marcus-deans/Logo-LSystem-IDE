package oolala.model.commands.visuals;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import oolala.model.commands.Commands;
import oolala.model.commands.ViewCommand;
import oolala.view.ViewTurtle;

public class StampViewCommand extends ViewCommand {

  /**
   * Create a new Stamp command to stamp the turtle object in current position
   *
   * @param viewTurtle the turtle on which the action will be imparted
   * @param root       the JavaFX Group to which the new Turtle (JavaFX Imageview) should be added
   */
  public StampViewCommand(ViewTurtle viewTurtle, Group root) {
    super(viewTurtle);
    myCommandName = Commands.STAMP;
    stampTurtle(root);
  }

  //create the new stamped turtle by making new object and setting correct rotation
  private void stampTurtle(Group root) {
    ImageView stampTurtleView = myViewTurtle.createTurtleView();
    stampTurtleView.setRotate(myViewTurtle.getMyDegreesRotation());
    root.getChildren().add(stampTurtleView);
  }
}
