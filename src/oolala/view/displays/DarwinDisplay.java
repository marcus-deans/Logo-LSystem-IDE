package oolala.view.displays;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import oolala.model.ModelCreature;

public class DarwinDisplay extends Display{
  private static ArrayList<ModelCreature> allModelCreatures;

  public DarwinDisplay(){
    super();
    allModelCreatures = new ArrayList<>();
  }
  public static ArrayList<ModelCreature> getAllModelCreatures(){
    return allModelCreatures;
  }


  @Override
  protected void handleInputParsing(String text) {
    //TODO: send stuff to input parser
  }

  @Override
  protected File[] getFilesFromPath() {
    return new File("data/examples/darwin").listFiles();
  }
}
