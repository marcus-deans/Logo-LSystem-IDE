package oolala.model.commands.visuals;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import oolala.model.Turtle;
import oolala.model.commands.Commands;

public class StampCommand extends VisualCommand {

  public StampCommand(Turtle myTurtle, Group root) {
    super(myTurtle);
    myCommandName = Commands.STAMP;
    stampTurtle(root);
  }

  private void stampTurtle(Group root) {
    ImageView stampTurtleView = myTurtle.createTurtleView();
    stampTurtleView.setRotate(myTurtle.getDegreesRotation());
    root.getChildren().add(stampTurtleView);
  }
}
