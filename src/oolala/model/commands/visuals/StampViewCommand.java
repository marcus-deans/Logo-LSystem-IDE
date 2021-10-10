package oolala.model.commands.visuals;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import oolala.model.commands.Commands;
import oolala.model.commands.ViewCommand;
import oolala.view.ViewTurtle;

public class StampViewCommand extends ViewCommand {

  public StampViewCommand(ViewTurtle viewTurtle, Group root) {
    super(viewTurtle);
    myCommandName = Commands.STAMP;
    stampTurtle(root);
  }

  private void stampTurtle(Group root) {
    ImageView stampTurtleView = myViewTurtle.createTurtleView();
    stampTurtleView.setRotate(myViewTurtle.getMyDegreesRotation());
    root.getChildren().add(stampTurtleView);
  }
}
