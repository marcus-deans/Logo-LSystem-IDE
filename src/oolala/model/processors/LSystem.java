package oolala.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class LSystem {

    public ArrayList<String> validCommands;
    public ArrayList<String> doubleAngleCommands;
    public ArrayList<String> doubleLengthCommands;
    public ArrayList<String> singleCommands;
    public Map<String, List<String>> commandConversion;

    public Instruction myCurrentInstruction;
    private final List<String> myHistory;
    private boolean isValidCommand;

    private final Queue<Instruction> myInstructions;

    public LSystem() {
        myInstructions = new LinkedList<>();
        myHistory = new ArrayList<>();
        validCommands = new ArrayList<>(Arrays.asList("F", "f", "G", "g", "A", "a", "B", "b", "X", "x", "+", "-"));
        doubleAngleCommands = new ArrayList<>(Arrays.asList("lt", "rt"));
        doubleLengthCommands = new ArrayList<>(Arrays.asList("fd", "bk"));
        singleCommands = new ArrayList<>(Arrays.asList("pd", "pu", "st", "ht", "home", "stamp"));
        initializeCommandConversions();
        isValidCommand = true;
    }

    //TODO: change the commands to enum values? not sure how to do that
    private void initializeCommandConversions() {
        commandConversion = new HashMap<>();
        commandConversion.put("F", Arrays.asList("pd", "fd"));
        commandConversion.put("G", Arrays.asList("pu", "fd"));
        commandConversion.put("A", Arrays.asList("pu", "bk"));
        commandConversion.put("B", Arrays.asList("pd", "bk"));
        commandConversion.put("X", Arrays.asList("stamp"));
        commandConversion.put("+", Arrays.asList("rt"));
        commandConversion.put("-", Arrays.asList("lt"));
    }

    //Method to parse the input
    public void inputParser(int levels, int angle, int length, List<String> inputStreams){
        int level=0;
        for(String thisLevelStream : inputStreams){
            List<String> inputCommands = Arrays.asList(thisLevelStream.split("")); //split by every character
            isValidCommand = true;
            for(String command : inputCommands){
                if(validCommands.contains(command)){
                    createCommand(level, command, angle, length);
                }else{
                    //TODO: error handling - warn user
                    isValidCommand = false;
                    break;
                }
            }
            level++;
        }
    }

    private void createCommand(int level, String command, int angle, int length) {
        List<String> commands;
        if(command.matches("[a-zA-Z]+")){ //Handles case sensitivity for alphabetic LSystem commands
            commands = commandConversion.get(command.toUpperCase());
        }else{
            commands = commandConversion.get(command);
        }
        for(String thisCommand : commands){
            if(singleCommands.contains(thisCommand)){ //if this is single command
                Instruction singleInst = new Instruction(level, thisCommand);
                myInstructions.add(singleInst);
            }else if(doubleAngleCommands.contains(thisCommand)){ //double command
                Instruction doubleAngleInst = new Instruction(level, thisCommand, angle);
                myInstructions.add(doubleAngleInst);
            }else{
                Instruction doubleLenInst = new Instruction(level, thisCommand, length);
                myInstructions.add(doubleLenInst);
            }
        }
    }


    //Method to save the user input commands to a fle
    public void saveCommand(String inputStream, String filename) {
        String path = "data/examples/lsystem" + filename + ".txt";
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

    public Queue<Instruction> getMyInstructions() {
        return myInstructions;
    }
}

