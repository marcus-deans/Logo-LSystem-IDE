package oolala.model.commands.conditionals;

import java.util.List;
import oolala.model.ModelCreature;
import oolala.view.displays.DarwinDisplay;

public abstract class IfEntityConditionalCommand extends ConditionalCommand{
  public IfEntityConditionalCommand(ModelCreature modelCreature, int nextCommand){
    super(modelCreature, nextCommand);
  }

  protected boolean determineEntityPresentAndNature(boolean checkingSameType){
    //TODO: check whether space nearby ahead of creature is the world's boundary
    List<ModelCreature> allModelCreatures = DarwinDisplay.getAllModelCreatures();
    for(ModelCreature checkModelCreature : allModelCreatures){
      if(compareXWithin(checkModelCreature) && compareYWithin(checkModelCreature)){

      }
    }
  }

  private boolean compareXWithin(ModelCreature checkModelCreature){

  }

  private boolean compareYWithin(ModelCreature checkModelCreature){

  }
  }

}
