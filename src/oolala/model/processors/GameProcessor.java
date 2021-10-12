package oolala.model.processors;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import oolala.model.instructions.CreatureInstruction;
import oolala.model.instructions.Instruction;

public abstract class GameProcessor {

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

    protected void createDoubleCommand(String command, Integer number){}; //NOT abstract

    protected void createSingleCommand(String command){}; //NOT abstract

    public abstract void inputParser(int levels, int angle, int length, String inputStream);

    public abstract List<String> getHistory();

    public abstract void saveHistory(String historyElement);

    public abstract boolean getValidCommand();

    public abstract void setValidCommand(Boolean status);

    //Method to save the user input commands to a fle
    public abstract void saveCommand(String inputStream, String filename);

    public abstract LinkedList<Instruction> getMyInstructions();

    public abstract ArrayList<ArrayList<Instruction>> getConvertedInstructionLevels();

    public ArrayList<CreatureInstruction> getMySpeciesInstructions(int speciesKey){return new ArrayList<>();}; //not abstract
}
