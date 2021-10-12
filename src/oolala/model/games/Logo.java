package oolala.model.games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import oolala.model.instructions.CreatureInstruction;
import oolala.model.instructions.LogoInstruction;

public class Logo extends Game {

  private ArrayList<String> doubleCommands;
  private ArrayList<String> singleCommands;

  private boolean isValidCommand;
  private final LinkedList<LogoInstruction> myInstructions;
  private final List<String> myHistory;

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

  protected void createDoubleCommand(String command, Integer number) {
    LogoInstruction newInstruction = new LogoInstruction(command, number);
    myInstructions.add(newInstruction);
  }

  protected void createSingleCommand(String command) {
    LogoInstruction newInstruction = new LogoInstruction(command);
    myInstructions.add(newInstruction);
  }

  //Method to save the user input commands to a fle
  public boolean saveCommand(String inputStream, String filename) {
    StringBuilder sb = new StringBuilder();
    sb.append("data/examples/logo/");
    sb.append(filename);
    sb.append(".txt");
    return saveCommandGivenPath(inputStream, sb.toString());
  }

  @Override
  public List<String> getHistory() {
    return myHistory;
  }

  @Override
  public void saveHistory(String historyElement) {
    myHistory.add(historyElement);
  }

  @Override
  public boolean getValidCommand() {
    return isValidCommand;
  }

  @Override
  public void setValidCommand(Boolean status) {
    isValidCommand = status;
  }

  public LinkedList<LogoInstruction> getMyInstructions() {
    return myInstructions;
  }

  //expansions by level in Logo instruction format
  public ArrayList<ArrayList<LogoInstruction>> getConvertedInstructionLevels() {
    return new ArrayList<ArrayList<LogoInstruction>>();
  }

  @Override
  public ArrayList<CreatureInstruction> getMySpeciesInstructions(int speciesKey) {
    return null;
  }
}
