package oolala.model.processors;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import oolala.model.instructions.Instruction;

public class Logo extends GameProcessor{

  public ArrayList<String> doubleCommands;
  public ArrayList<String> singleCommands;

  private final List<String> myHistory;
  private boolean isValidCommand;

  private final Queue<Instruction> myInstructions;

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

  private void createDoubleCommand(String command, Integer number) {
    Instruction newInstruction = new Instruction(command, number);
    myInstructions.add(newInstruction);
  }

  private void createSingleCommand(String command) {
    Instruction newInstruction = new Instruction(command);
    myInstructions.add(newInstruction);
  }

  private boolean nextCommandIsInt(int index, List<String> inputCommands) {
    boolean nextCommandIsInteger = true;
    try{
      Integer.parseInt(inputCommands.get(index+1));
    }catch(NumberFormatException e){
      nextCommandIsInteger = false;
    }
    return nextCommandIsInteger;
  }

  //Method to save the user input commands to a fle
  public void saveCommand(String inputStream, String filename) {
    String path = "data/examples/logo" + filename + ".txt";
    File newProgram = new File(path);
    try {
      if (newProgram.createNewFile()) {
        FileWriter writeToFile = new FileWriter(newProgram.getAbsolutePath());
        writeToFile.write(inputStream);
        writeToFile.close();
      } else { //TODO: error handling instead of a sys.out.print statement
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
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

  public LinkedList<Instruction> getMyInstructions() {
    return myInstructions;
  }

  //expansions by level in Logo instruction format
  public ArrayList<ArrayList<Instruction>> getConvertedInstructionLevels() {
    return new ArrayList<ArrayList<Instruction>>();
  }
}
