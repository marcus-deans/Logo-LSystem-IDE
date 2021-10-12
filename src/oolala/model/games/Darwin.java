package oolala.model.games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import oolala.model.instructions.CreatureInstruction;
import oolala.model.instructions.LogoInstruction;

/**
 * @author marcusdeans Purpose: create backend game for Darwin Assumptions: running display,
 * error-checked inputs Dependencies: None Example Usage: serves as backend for playing Darwin User
 * Details: getConvertInstructionLevels() should not be used
 */
public class Darwin extends Game {

  protected static final String RIGHT = "right";
  protected static final String INFECT = "infect";
  protected static final String IFEMPTY = "ifempty";
  protected static final String IFWALL = "ifwall";
  protected static final String IFSAME = "ifsame";
  protected static final String IFENEMY = "ifenemy";
  protected static final String IFRANDOM = "ifrandom";
  protected static final String GO = "go";
  protected static final String MOVE = "move";
  protected static final String LEFT = "left";

  private final HashMap<Integer, ArrayList<CreatureInstruction>> mySpeciesInstructions;
  private final List<String> myHistory;
  private final ArrayList<String> doubleCommands;
  private final ArrayList<String> singleCommands;
  private final ArrayList<CreatureInstruction> myInstructions;

  boolean isValidCommand;

  /**
   * Create new Darwin backend
   */
  public Darwin() {
    singleCommands = new ArrayList<>(Arrays.asList(INFECT));
    doubleCommands = new ArrayList<>(
        Arrays.asList(MOVE, LEFT, RIGHT, IFEMPTY, IFWALL, IFSAME, IFENEMY, IFRANDOM, GO));
    myInstructions = new ArrayList<>();
    mySpeciesInstructions = new HashMap<>();
    myHistory = new ArrayList<>();
    isValidCommand = true;
  }

  //TODO: ignore lines that start with #

  /**
   * Create input parser that will parse the command line input of the program
   *
   * @param nearbyThreshold   radius around Creatures that defines 'nearby'
   * @param speciesIdentifier the unique identifier for each species
   * @param length            the length of movement operations
   * @param inputStream       input from the command line
   */
  public void inputParser(int nearbyThreshold, int speciesIdentifier, int length,
      String inputStream) {
    myInstructions.clear();
    isValidCommand = true;
    List<String> inputCommands = Arrays.asList(
        inputStream.split("\\s+")); //split by any space or tab
    for (int i = 0; i < inputCommands.size(); i++) {
      if (inputCommands.get(i).matches("[a-zA-Z]+") && singleCommands.contains(
          inputCommands.get(i).toLowerCase())) { //Valid single command
        createSingleCommand(inputCommands.get(i));
      } else if (inputCommands.get(i).matches("[a-zA-Z]+") && doubleCommands.contains(
          inputCommands.get(i).toLowerCase()) && i < inputCommands.size() && nextCommandIsInt(
          i, inputCommands)) { //Valid double command (requires a second number)
        createDoubleCommand(inputCommands.get(i),
            Integer.valueOf(inputCommands.get(i + 1)));
        break;
      } else { //Not a valid command stream
        isValidCommand = false; //TODO: notify user that input was invalid
        break;
      }
    }
    if (isValidCommand) {
      mySpeciesInstructions.put(speciesIdentifier, myInstructions);
    }
  }

  //create a command that consists of a single word
  @Override
  protected void createSingleCommand(String command) {
    CreatureInstruction singleInstruction = new CreatureInstruction(command);
    myInstructions.add(singleInstruction);
  }

  //create a command that consists of a word and an integer value
  @Override
  protected void createDoubleCommand(String command, Integer valueOf) {
    CreatureInstruction doubleInstruction = new CreatureInstruction(command, valueOf);
    myInstructions.add(doubleInstruction);
  }

  /**
   * Save the input to a text file
   *
   * @param inputStream command line input from Display
   * @param filename    String filename of file to be saved
   * @return confirmation that files was saved
   */
  public boolean saveCommand(String inputStream, String filename) {
    StringBuilder sb = new StringBuilder();
    sb.append("data/examples/darwin/");
    sb.append(filename);
    sb.append(".txt");
    return saveCommandGivenPath(inputStream, sb.toString());
  }

  /**
   * Get instructions that have been parsed, defunct method
   *
   * @return empty linkedlist as not applicable here
   */
  public LinkedList<LogoInstruction> getMyInstructions() {
    return new LinkedList<>();
  }


  /**
   * expansions by level in Logo instruction format, defunct method
   *
   * @return empty arraylist as not applicable here
   */
  public ArrayList<ArrayList<LogoInstruction>> getConvertedInstructionLevels() {
    return new ArrayList<ArrayList<LogoInstruction>>();
  }

  /**
   * Save a specific string to the history of the file
   *
   * @param historyElement element to be added to historu
   */
  public void saveHistory(String historyElement) {
    myHistory.add(historyElement);
  }

  /**
   * Get history of commands
   *
   * @return list of command strings
   */
  public List<String> getHistory() {
    return myHistory;
  }

  /**
   * Determine whether the command was valid
   *
   * @return boolean whether valid
   */
  public boolean getValidCommand() {
    return isValidCommand;
  }

  /**
   * Set the status of valid command
   *
   * @param status status that valid command should be set to
   */
  public void setValidCommand(Boolean status) {
    isValidCommand = status;
  }

  /**
   * Get the commands that characterize a species
   *
   * @param speciesKey the species of interest
   * @return the instructions corresponding to the species
   */
  public ArrayList<CreatureInstruction> getMySpeciesInstructions(int speciesKey) {
    return mySpeciesInstructions.get(speciesKey);
  }


}
