package oolala.model.games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import oolala.model.instructions.LogoInstruction;

/**
 * @author naylaboorady, marcusdeans Purpose: create backend game for Logo Assumptions: running
 * display, error-checked inputs Dependencies: None Example Usage: serves as backend for playing
 * Logo User Details: getConvertInstructionLevels(), get mySpeciesInstructions should not be used
 */
public class Logo extends Game {

  private boolean isValidCommand;

  private final LinkedList<LogoInstruction> myInstructions;
  private final List<String> myHistory;
  private final ArrayList<String> doubleCommands;
  private final ArrayList<String> singleCommands;

  /**
   * Create new Logo backend
   */
  public Logo() {
    super();
    isValidCommand = true;
    myInstructions = new LinkedList<>();
    myHistory = new ArrayList<>();
    doubleCommands = new ArrayList<>(Arrays.asList(FORWARD, BACKWARD, LEFT, RIGHT, TELL));
    singleCommands = new ArrayList<>(
        Arrays.asList(PENDOWN, PENUP, SHOW_TURTLE, HIDE_TURTLE, HOME, STAMP));
  }

  //TODO: ignore lines that start with # - filter out before splitting inputCommands
  //Method to parse the input

  /**
   * Create input parser that will parse the command line input of the program
   *
   * @param dead1       defunct number, used in other programs
   * @param dead2       defunct number, used in other programs
   * @param dead3       defunct number, used in other programs
   * @param inputStream input from the command line
   */
  public void inputParser(int dead1, int dead2, int dead3, String inputStream) {
    isValidCommand = true;
    List<String> inputCommands = Arrays.asList(
        inputStream.split("\\s+")); //split by any space or tab
    for (int i = 0; i < inputCommands.size(); i++) {
      if (inputCommands.get(i).matches("[a-zA-Z]+") && singleCommands.contains(
          inputCommands.get(i).toLowerCase())) { //Valid single command
        createSingleCommand(inputCommands.get(i));
      } else if (inputCommands.get(i).matches("[a-zA-Z]+") && doubleCommands.contains(
          inputCommands.get(i).toLowerCase()) && i < inputCommands.size() && nextCommandIsInt(i,
          inputCommands)) { //Valid double command (requires a second number)
        createDoubleCommand(inputCommands.get(i), Integer.valueOf(inputCommands.get(i + 1)));
        i++;
      } else { //Not a valid command stream
        isValidCommand = false;
        break;
      }
    }
  }

  //create a command that consists of a word and an integer value
  protected void createDoubleCommand(String command, Integer number) {
    LogoInstruction newInstruction = new LogoInstruction(command, number);
    myInstructions.add(newInstruction);
  }

  //create a command that consists of a single word
  protected void createSingleCommand(String command) {
    LogoInstruction newInstruction = new LogoInstruction(command);
    myInstructions.add(newInstruction);
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
    sb.append("data/examples/logo/");
    sb.append(filename);
    sb.append(".txt");
    return saveCommandGivenPath(inputStream, sb.toString());
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
   * Get history of commands
   *
   * @return list of command strings
   */
  public List<String> getHistory() {
    return myHistory;
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
   * Get instructions that have been parsed
   *
   * @return instructions for the turtle to execute
   */
  public LinkedList<LogoInstruction> getMyInstructions() {
    return myInstructions;
  }
}
