package oolala.model;

import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
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
  private ArrayList<String> history = new ArrayList<>();

//  private LinkedHashMap<String, Integer> myInstructions;
  private Queue<Instruction> myInstructions;

  public Logo(){
    myInstructions = new LinkedList<>();
    commands = new ArrayList<>(Arrays.asList("fd", "bk", "lt", "rt", "pd", "pu", "st",
        "ht", "home", "stamp", "tell"));
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
    File tempDir = new File(System.getProperty("java.io.data"));
    File newProgram = new File(tempDir, filename);
    try {
      FileWriter myWrtier = new FileWriter(filename);
      myWrtier.write(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void saveHistory(String historyElement) {
    history.add(historyElement);
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
