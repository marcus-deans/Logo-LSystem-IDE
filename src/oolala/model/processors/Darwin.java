package oolala.model.processors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import oolala.model.instructions.CreatureInstruction;
import oolala.model.instructions.Instruction;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Darwin {

    public static final String MOVE = "move";
    public static final String LEFT = "left";
    public static final String RIGHT = "right";
    public static final String INFECT = "infect";
    public static final String IFEMPTY = "ifempty";
    public static final String IFWALL = "ifwall";
    public static final String IFSAME = "ifsame";
    public static final String IFENEMY = "ifenemy";
    public static final String IFRANDOM = "ifrandom";
    public static final String GO = "go";

    public ArrayList<String> doubleCommands;
    public ArrayList<String> singleCommands;
    public LinkedList<Instruction> myInstructions;
    private final List<String> myHistory;
    boolean isValidCommand;

    public Darwin() {
        singleCommands = new ArrayList<>(Arrays.asList(INFECT));
        doubleCommands = new ArrayList<>(Arrays.asList(MOVE, LEFT, RIGHT, IFEMPTY, IFWALL, IFSAME, IFENEMY, IFRANDOM, GO));
        myInstructions = new LinkedList<>();
        myHistory = new ArrayList<>();
        isValidCommand = true;
    }

    //TODO: ignore lines that start with #
    public void inputParser(int radius, int angle, int length, String inputStream) {
        isValidCommand = true;
        List<String> inputCommands = Arrays.asList(inputStream.split("\\s+")); //split by any space or tab
        for(int i=0; i<inputCommands.size(); i++){
            if(inputCommands.get(i).matches("[a-zA-Z]+") && singleCommands.contains(inputCommands.get(i).toLowerCase())){ //Valid single command
                createSingleCommand(inputCommands.get(i));
            }else if(inputCommands.get(i).matches("[a-zA-Z]+") && doubleCommands.contains(inputCommands.get(i).toLowerCase()) && i < inputCommands.size() && nextCommandIsInt(i, inputCommands)){ //Valid double command (requires a second number)
                createDoubleCommand(inputCommands.get(i), Integer.valueOf(inputCommands.get(i+1)));
                break;
            }else{ //Not a valid command stream
                isValidCommand = false; //TODO: notify user that input was invalid
                break;
            }
        }
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

    private void createSingleCommand(String command) {
        CreatureInstruction singleInstruction = new CreatureInstruction(command);
        myInstructions.add(singleInstruction);
    }

    private void createDoubleCommand(String command, Integer valueOf) {
        CreatureInstruction doubleInstruction = new CreatureInstruction(command, valueOf);
        myInstructions.add(doubleInstruction);
    }

    //Method to save the user input commands to a fle
    public void saveCommand(String inputStream, String filename) {
        String path = "data/examples/darwin" + filename + ".txt";
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

    public LinkedList<Instruction> getMyInstructions() {
        return myInstructions;
    }

}
