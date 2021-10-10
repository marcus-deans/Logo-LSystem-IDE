package oolala.model.processors;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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

  private final List<String> myHistory;
  private boolean isValidCommand;

  private final Queue<Instruction> myInstructions;

  public Logo() {
    myInstructions = new LinkedList<>();
    doubleCommands = new ArrayList<>(Arrays.asList(FORWARD, BACKWARD, LEFT, RIGHT, TELL));
    singleCommands = new ArrayList<>(Arrays.asList(PENDOWN, PENUP, SHOW_TURTLE, HIDE_TURTLE, HOME, STAMP));
    myHistory = new ArrayList<>();
    isValidCommand = true;
  }

  //TODO: ignore lines that start with # - filter out before splitting inputCommands
  //Method to parse the input
  public void inputParser(String inputStream){
    isValidCommand = true;
    List<String> inputCommands = Arrays.asList(inputStream.split("\\s+")); //split by any space or tab
    for(int i=0; i<inputCommands.size(); i++){
      if(inputCommands.get(i).matches("[a-zA-Z]+") && singleCommands.contains(inputCommands.get(i).toLowerCase())){ //Valid single command
        createSingleCommand(inputCommands.get(i));
      }else if(inputCommands.get(i).matches("[a-zA-Z]+") && doubleCommands.contains(inputCommands.get(i).toLowerCase()) && i < inputCommands.size() && nextCommandIsInt(i, inputCommands)){ //Valid double command (requires a second number)
        createDoubleCommand(inputCommands.get(i), Integer.valueOf(inputCommands.get(i+1)));
        continue;
      }else{ //Not a valid command stream
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

  public void saveHistory(String historyElement) {
    myHistory.add(historyElement);
  }

  public List<String> getHistory() {
    return myHistory;
  }

  public boolean getValidCommand() {
    return isValidCommand;
  }

  public void setValidCommand(Boolean status) {
    isValidCommand = status;
  }

  public Queue<Instruction> getMyInstructions() {
    return myInstructions;
  }
}
