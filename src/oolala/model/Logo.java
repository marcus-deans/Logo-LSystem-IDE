package oolala.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

public class Logo {

  public ArrayList<String> commands;

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

//  private LinkedHashMap<String, Integer> myInstructions;
  private Queue<Instruction> myInstructions;

  public Logo(){
    myInstructions = new LinkedList<>();
    commands = new ArrayList<>(Arrays.asList("fd", "bk", "lt", "rt", "pd", "pu", "st",
        "ht", "home", "stamp", "tell"));
    myHistory = new ArrayList<>();
  }

  //Method to parse the input
  public void inputParser(String inputStream){
    String[] elements = inputStream.split("\n");
    List<String> inputCommands = Arrays.asList(elements); //each command is a line

    for(String command : inputCommands){
      String[] commandElements = command.split(" "); //split commands by space
      if(commandElements.length == 1){
        //check if this is a valid command. if it is, then create a new instruction
        if(commands.contains(commandElements[0])){
          Instruction newInstruction = new Instruction(commandElements[0]);
          myInstructions.add(newInstruction);
        }else{
          //throw error - invalid command
        }
      }else if(commandElements.length == 2){
        //check if first element is a valid command AND second element is a number
        boolean isInt = false;
        try{
          Integer.parseInt(commandElements[1]);
        }catch(NumberFormatException e){
          isInt = true;
        }
        if(commands.contains(commandElements[0]) && isInt){
            Instruction newInstruction = new Instruction(Integer.valueOf(commandElements[1]), commandElements[0]);
            myInstructions.add(newInstruction);
        }
      }else{
        //throw error - command can't have 0 or 3 elements (or more)
      }
    }
  }

  //Method to save the user input commands to a fle
  public void saveCommand(String inputStream, String filename){
    String path = "/Users/naylaboorady/Downloads/oolala_team01/data/examples/logo/" + filename + ".txt";
    File newProgram = new File(path);
    try {
      if (newProgram.createNewFile()) {
        FileWriter writeToFile = new FileWriter(newProgram.getAbsolutePath());
        writeToFile.write(inputStream);
        writeToFile.close();
        System.out.println("File created: " + newProgram.getName());
      } else {
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
