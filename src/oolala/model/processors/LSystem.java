package oolala.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class LSystem {

    public ArrayList<String> validCommands;

    public Instruction myCurrentInstruction;
    private final List<String> myHistory;
    private boolean isValidCommand;

    private final Queue<Instruction> myInstructions;

    public LSystem() {
        myInstructions = new LinkedList<>();
        myHistory = new ArrayList<>();
        validCommands = new ArrayList<>(Arrays.asList("F", "f", "G", "g", "A", "a", "B", "b", "X", "x", "+", "-"));
        Map<String, String> commandConversion = new HashMap<>();
        isValidCommand = true;
    }

    //Method to parse the input
    public void inputParser(int levels, int angle, int length, List<String> inputStreams){
        int level=0;
        for(String thisLevelStream : inputStreams){
            List<String> inputCommands = Arrays.asList(thisLevelStream.split("\\s+")); //split by any space or tab
            isValidCommand = true;
            for(String command : inputCommands){
                if(validCommands.contains(command)){
                    createCommand(level, command, angle, length);
                }else{
                    //TODO: error handling - warn user
                }
            }
            level++;
        }
    }

    //TODO: don't use a switch case - use a map
    private void createCommand(int level, String command, int angle, int length) {
        switch(command){
            case "f" : case "F" : {
                Instruction penDown = new Instruction(level, "pd");
                myInstructions.add(penDown);
                Instruction forward = new Instruction(level, "fd", length);
                myInstructions.add(forward);
            } case "g" : case "G":{
                Instruction penUp = new Instruction(level, "pu");
                myInstructions.add(penUp);
                Instruction forward = new Instruction(level, "fd", length);
                myInstructions.add(forward);
            } case "a" : case "A" : {
                Instruction penUp = new Instruction(level, "pu");
                myInstructions.add(penUp);
                Instruction back = new Instruction(level, "bk", length);
                myInstructions.add(back);
            } case "b" : case "B" : {
                Instruction penDown = new Instruction(level, "pd");
                myInstructions.add(penDown);
                Instruction back = new Instruction(level, "bk", length);
                myInstructions.add(back);
            } case "+": {
                Instruction right = new Instruction(level, "rt", angle);
                myInstructions.add(right);
            } case "-":{
                Instruction left = new Instruction(level, "lt", angle);
                myInstructions.add(left);
            } case "X":{
                Instruction stamp = new Instruction(level, "stamp");
                myInstructions.add(stamp);
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

