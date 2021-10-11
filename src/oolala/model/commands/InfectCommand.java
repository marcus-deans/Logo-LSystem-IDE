package oolala.model.commands;

import oolala.model.ModelCreature;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Create a command that will cause infection of another ModelCreature Assumptions:
 * Functioning ModelCreature; other values error-checked Dependencies: ModelCreature Example Usage:
 * used for 'infect' command User Details: None, usage exactly as dictated by command entry pattern
 * (error-checked)
 */
public class InfectCommand extends ModelCommand {

  private final ModelCreature infectingModelCreature;
  private ModelCreature infectedModelCreature;

  /**
   * Create new InfectCommand to infect another model creature
   * @param modelCreature the ModelCreature which will do the infecting
   */
  public InfectCommand(ModelCreature modelCreature) {
    super(modelCreature);
    infectingModelCreature = modelCreature;
    determineInfectedCreature();
    infectOtherCreature();
  }

  //identify which creature should be infected within the targeted range
  private void determineInfectedCreature() {
    //TODO: determine which creature to infect
    infectedModelCreature = new ModelCreature(0, 4, 3);
  }

  //change the parameters of the other creature to indicate that it has been infected
  private void infectOtherCreature() {
    //TODO: access the other creature and convert it
    infectedModelCreature.setMySpeciesIdentifier(infectingModelCreature.getMySpeciesIdentifier());
    //TODO: need to signal (or constantly refresh) creature in step() to update characteristics
  }

}
