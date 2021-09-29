package oolala.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

public class Logo {

  public ArrayList<String> doubleCommands;
  public ArrayList<String> singleCommands;

  public int currentX;
  public int currentY;
  public int goalX;
  public int goalY;
  public int drawX;
  public int drawY;

  public Instruction myCurrentInstruction;
  private Instruction myInstructionsPointer;
  private String currentUserCommand;
  private int currentUserPixels;
  private List<String> myHistory;
  private boolean isValidCommand;

//  private LinkedHashMap<String, Integer> myInstructions;
  private Queue<Instruction> myInstructions;

  public Logo(){
    myInstructions = new LinkedList<>();
    doubleCommands = new ArrayList<>(Arrays.asList("fd", "bk", "lt", "rt", "tell"));
    singleCommands = new ArrayList<>(Arrays.asList("pd", "pu", "st", "ht", "home", "stamp"));
    myHistory = new ArrayList<>();
    isValidCommand = true;
  }

  //Method to parse the input
  public void inputParser(String inputStream){
    List<String> inputCommands = Arrays.asList(inputStream.split("\\s+")); //split by any space or tab
    isValidCommand = true;
    boolean isIntegerAndPreviousWasCommand = false;
    int index = 0;
    for(String command : inputCommands){
      if(singleCommands.contains(command.toLowerCase())){ //Valid single command
        Instruction newInstruction = new Instruction(command);
        myInstructions.add(newInstruction);
      }else if(doubleCommands.contains(command.toLowerCase())){ //Valid double command (requires a second number)
        if(index < inputCommands.size()){
          boolean nextCommandIsInteger = true;
          try{
            Integer.parseInt(inputCommands.get(index+1));
          }catch(NumberFormatException e){
            nextCommandIsInteger = false;
          }
          if(nextCommandIsInteger){ //First command is valid AND second command is valid number
            Instruction newInstruction = new Instruction(command, Integer.valueOf(inputCommands.get(index+1)));
            myInstructions.add(newInstruction);
            isIntegerAndPreviousWasCommand = true;
          }
        }
      }else if(isIntegerAndPreviousWasCommand){
        isIntegerAndPreviousWasCommand = false;
      }else{ //Not a valid command stream
        isValidCommand = false;
        break;
      }
      index++;
    }
  }

  //Method to save the user input commands to a fle
  public void saveCommand(String inputStream, String filename){
    //TODO: don't use absolute path, figure out general path
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

  public List<String> getHistory(){
    return myHistory;
  }

  public boolean getValidCommand(){return isValidCommand;}
  public void setValidCommand(Boolean status){isValidCommand = status;}


  private void getNextInstruction(){
    for(Instruction instructionIterator : myInstructions){
      myCurrentInstruction = instructionIterator;
      //Turtle.updateCoordinates(myCurrentInstruction);
    }
//    Instruction currentInstruction = myInstructions.peek();
//    currentUserPixels = currentInstruction.pixels;
//    currentUserCommand = currentInstruction.command;
  }

  public Queue<Instruction> getMyInstructions(){
    return myInstructions;
  }
}
