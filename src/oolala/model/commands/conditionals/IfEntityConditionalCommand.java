package oolala.model.commands.conditionals;

import java.util.ArrayList;
import oolala.model.ModelCreature;
import oolala.view.displays.DarwinDisplay;

public abstract class IfEntityConditionalCommand extends ConditionalCommand {

  protected final int halfFieldOfView = 20;
  protected int mySpeciesIdentifier;

  public IfEntityConditionalCommand(ModelCreature modelCreature, int nextCommand) {
    super(modelCreature, nextCommand);
    this.mySpeciesIdentifier = modelCreature.getMySpeciesIdentifier();
  }

  protected boolean determineEntityPresentAndNature(boolean checkingSameType) {
    ArrayList<ModelCreature> allModelCreatures = DarwinDisplay.getAllModelCreatures();
    for (ModelCreature checkModelCreature : allModelCreatures) {
      if (compareWithinCircle(checkModelCreature) && compareWithinFieldOfView(checkModelCreature)) {
        if (checkingSameType
            && mySpeciesIdentifier == checkModelCreature.getMySpeciesIdentifier()) {
          return true;
        } else if (!checkingSameType
            && mySpeciesIdentifier != checkModelCreature.getMySpeciesIdentifier()) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean compareWithinCircle(ModelCreature checkModelCreature) {
    int checkModelCreatureX = checkModelCreature.getTurtleCoordinates().turtleNewX;
    int checkModelCreatureY = checkModelCreature.getTurtleCoordinates().turtleNewY;
<<<<<<< HEAD
    int xDifference = (int) Math.pow(checkModelCreatureX - myModelCreatureX, 2);
    int yDifference = (int) Math.pow(checkModelCreatureY - myModelCreatureY, 2);
    int radiusDistance = (int) Math.pow(myNearbyThreshold, 2);
    return xDifference + yDifference <= radiusDistance;
=======
    int xDifferenceSquared = (int) Math.pow(checkModelCreatureX - myModelCreatureX, 2);
    int yDifferenceSquared = (int) Math.pow(checkModelCreatureY - myModelCreatureY, 2);
    int radiusDistanceSquared = (int) Math.pow(myNearbyThreshold, 2);
    return xDifferenceSquared + yDifferenceSquared <= radiusDistanceSquared;
>>>>>>> d78dfef445eb4985d0d788eea8cca607f4abc428
  }

  private boolean compareWithinFieldOfView(ModelCreature checkModelCreature) {
    int checkModelCreatureX = checkModelCreature.getTurtleCoordinates().turtleNewX;
    int checkModelCreatureY = checkModelCreature.getTurtleCoordinates().turtleNewY;
    //https://stackoverflow.com/questions/6270785/how-to-determine-whether-a-point-x-y-is-contained-within-an-arc-section-of-a-c
    //https://math.stackexchange.com/questions/1596513/find-the-bearing-angle-between-two-points-in-a-2d-space
    double startAngle = ((myDegreesRotation + FULL_CIRCLE) - halfFieldOfView) % FULL_CIRCLE;
    double endAngle = (myDegreesRotation + halfFieldOfView) % FULL_CIRCLE;
    double checkAngle = Math.atan2(checkModelCreatureY - myModelCreatureY,
        checkModelCreatureX - myModelCreatureX);
    if (startAngle < endAngle) {
      return ((startAngle < checkAngle) && (checkAngle < endAngle));
    } else if (startAngle > endAngle) { //explanatory
      return ((checkAngle > startAngle) || (checkAngle < endAngle));
    }
    return false;
  }
}
