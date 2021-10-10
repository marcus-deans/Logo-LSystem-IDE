package oolala.model.processors;

import oolala.model.instructions.Instruction;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GameProcessor {
    private ArrayList<String> doubleCommands;
    private ArrayList<String> singleCommands;
    private List<String> myHistory;
    private boolean isValidCommand;
    private Queue<Instruction> myInstructions;

    public GameProcessor(){
        myInstructions = new LinkedList<>();
        doubleCommands = new ArrayList<>(Arrays.asList());
        singleCommands = new ArrayList<>(Arrays.asList());
        myHistory = new ArrayList<>();
        isValidCommand = true;
    }

    public void inputParser(int levels, int angle, int length, String inputStream){
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

    public void saveCommand(String inputStream, String filename) {
        String path = "data/examples/lsystem" + filename + ".txt";
        saveCommandGivenPath(inputStream, path);
    }

    //TODO: each new line should be a new level
    //Method to save the user input commands to a fle
    public void saveCommandGivenPath(String inputStream, String path) {
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

    protected boolean nextCommandIsInt(int index, List<String> inputCommands) {
        boolean nextCommandIsInteger = true;
        try{
            Integer.parseInt(inputCommands.get(index+1));
        }catch(NumberFormatException e){
            nextCommandIsInteger = false;
        }
        return nextCommandIsInteger;
    }

    protected void createDoubleCommand(String command, Integer number) {
        Instruction newInstruction = new Instruction(command, number);
    }

    protected void createSingleCommand(String command) {
        Instruction newInstruction = new Instruction(command);
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
