package oolala.model.processors;

<<<<<<< HEAD
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import oolala.model.instructions.Instruction;

public class Logo {
=======
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import oolala.model.instructions.Instruction;

public class Logo extends GameProcessor{
>>>>>>> d78dfef445eb4985d0d788eea8cca607f4abc428
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

<<<<<<< HEAD
  private final List<String> myHistory;
  private boolean isValidCommand;

  private final Queue<Instruction> myInstructions;
=======

>>>>>>> d78dfef445eb4985d0d788eea8cca607f4abc428

  public Logo() {
    myInstructions = new LinkedList<>();
    doubleCommands = new ArrayList<>(Arrays.asList(FORWARD, BACKWARD, LEFT, RIGHT, TELL));
    singleCommands = new ArrayList<>(Arrays.asList(PENDOWN, PENUP, SHOW_TURTLE, HIDE_TURTLE, HOME, STAMP));
    myHistory = new ArrayList<>();
    isValidCommand = true;
  }

  //TODO: ignore lines that start with # - filter out before splitting inputCommands
  //Method to parse the input
<<<<<<< HEAD
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
=======
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
        continue;
      } else { //Not a valid command stream
>>>>>>> d78dfef445eb4985d0d788eea8cca607f4abc428
        isValidCommand = false;
        break;
      }
    }
  }

<<<<<<< HEAD
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
=======
  protected void createDoubleCommand(String command, Integer number) {
    Instruction newInstruction = new Instruction(command, number);
    myInstructions.add(newInstruction);
  }

  protected void createSingleCommand(String command) {
    Instruction newInstruction = new Instruction(command);
    myInstructions.add(newInstruction);
>>>>>>> d78dfef445eb4985d0d788eea8cca607f4abc428
  }

  //Method to save the user input commands to a fle
  public void saveCommand(String inputStream, String filename) {
    String path = "data/examples/logo" + filename + ".txt";
<<<<<<< HEAD
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
=======
    saveCommandGivenPath(inputStream, path);
>>>>>>> d78dfef445eb4985d0d788eea8cca607f4abc428
  }
}
