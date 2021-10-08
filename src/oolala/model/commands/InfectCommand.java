package oolala.model.commands;

import oolala.model.ModelCreature;

public class InfectCommand extends ModelCommand {

  private final ModelCreature infectingModelCreature;
  private ModelCreature infectedModelCreature;

  public InfectCommand(ModelCreature modelCreature) {
    super(modelCreature);
    infectingModelCreature = modelCreature;
    determineInfectedCreature();
    infectOtherCreature();
  }

  private void determineInfectedCreature() {
    //TODO: determine which creature to infect
    infectedModelCreature = new ModelCreature(0, 4, 3);
  }

  private void infectOtherCreature() {
    //TODO: access the other creature and convert it
    infectedModelCreature.setMySpeciesIdentifier(infectingModelCreature.getMySpeciesIdentifier());
    //TODO: need to signal (or constantly refresh) creature in step() to update characteristics
  }

}
