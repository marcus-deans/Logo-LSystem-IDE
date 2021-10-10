package oolala.model.processors;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import oolala.model.instructions.Instruction;

public abstract class GameProcessor {

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
}
