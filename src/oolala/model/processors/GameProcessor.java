package oolala.model.processors;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import oolala.model.instructions.Instruction;

public abstract class GameProcessor {

    public GameProcessor() {}

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

    protected abstract void createDoubleCommand(String command, Integer number);

    protected abstract void createSingleCommand(String command);

    public abstract void inputParser(int levels, int angle, int length, String inputStream);

    public abstract List<String> getHistory();

    public abstract void saveHistory(String historyElement);

    public abstract boolean getValidCommand();

    public abstract void setValidCommand(Boolean status);

    //Method to save the user input commands to a fle
    public abstract void saveCommand(String inputStream, String filename);

    public abstract Queue<Instruction> getMyInstructions();
}
