package oolala.model.processors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import oolala.model.instructions.Instruction;

public class Logo extends GameProcessor{
  public static final String FORWARD = "fd";
  public static final String BACKWARD = "bk";
  public static final String LEFT = "lt";
  public static final String RIGHT = "rt";
  public static final String TELL = "tell";
  public static final String PENDOWN = "pd";
  public static final String PENUP = "pu";
  public static final String SHOW_TURTLE = "st";
  public static final String HIDE_TURTLE = "ht";
  public static final String HOME = "home";
  public static final String STAMP = "stamp";

  public ArrayList<String> doubleCommands;
  public ArrayList<String> singleCommands;

  private boolean isValidCommand;
  private final LinkedList<Instruction> myInstructions;
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
    Instruction newInstruction = new Instruction(command, number);
    myInstructions.add(newInstruction);
  }

  protected void createSingleCommand(String command) {
    Instruction newInstruction = new Instruction(command);
    myInstructions.add(newInstruction);
  }

  //Method to save the user input commands to a fle
  public void saveCommand(String inputStream, String filename) {
    String path = "data/examples/logo" + filename + ".txt";
    saveCommandGivenPath(inputStream, path);
  }

  public List<String> getHistory() {
    return myHistory;
  }

  public void saveHistory(String historyElement) {
    myHistory.add(historyElement);
  }

  public boolean getValidCommand() {
    return isValidCommand;
  }

  public void setValidCommand(Boolean status) {
    isValidCommand = status;
  }

  public LinkedList<Instruction> getMyInstructions() {
    return myInstructions;
  }

  //expansions by level in Logo instruction format
  public ArrayList<ArrayList<Instruction>> getConvertedInstructionLevels() {
    return new ArrayList<ArrayList<Instruction>>();
  }
}
