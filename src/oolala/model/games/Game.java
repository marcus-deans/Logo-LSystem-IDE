package oolala.model.games;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import oolala.model.instructions.CreatureInstruction;
import oolala.model.instructions.LogoInstruction;

/**
 * @author naylaboorady, marcusdeans Purpose: create abstract class representing all of the games
 * that may be played Assumptions: running display, error-checked inputs Dependencies: None Example
 * Usage: used as superclass for Logo, LSystem, and Darwin backends User Details: Some abstract
 * classes will not be genuinely implemented in each program
 */
public abstract class Game {

    protected static final String FORWARD = "fd";
    protected static final String BACKWARD = "bk";
    protected static final String LEFT = "lt";
    protected static final String RIGHT = "rt";
    protected static final String TELL = "tell";
    protected static final String PENDOWN = "pd";
    protected static final String PENUP = "pu";
    protected static final String SHOW_TURTLE = "st";
    protected static final String HIDE_TURTLE = "ht";
    protected static final String HOME = "home";
    protected static final String STAMP = "stamp";

    /**
     * Create new Game (backend)
     */
    public Game() {
    }

    /**
     * Save user input commands to file
     *
     * @param inputStream command line user input
     * @param path        path that file should be saved to
     * @return return whether command saved succesfully
     */
    public boolean saveCommandGivenPath(String inputStream, String path) {
        File newProgram = new File(path);
        try {
            if (newProgram.createNewFile()) {
                FileWriter writeToFile = new FileWriter(newProgram.getAbsolutePath());
                writeToFile.write(inputStream);
                writeToFile.close();
            } else {
                return false; //on front end, user will be informed of error
            }
        } catch (IOException e) {
            return false; //on front end, user will be informed of error
        }
        return true;
    }

    //determine whether the next command is an integer (different Instruction format)
    protected boolean nextCommandIsInt(int index, List<String> inputCommands) {
        boolean nextCommandIsInteger = true;
        try {
            Integer.parseInt(inputCommands.get(index + 1));
        } catch (NumberFormatException e) {
            nextCommandIsInteger = false;
        }
        return nextCommandIsInteger;
    }

    //create a new double command of String and integer value
    protected void createDoubleCommand(String command, Integer number) {
    } //NOT abstract

    //create a new single command of String
    protected void createSingleCommand(String command) {
    } //NOT abstract

    //abstract method for method that will parse user's input
    public abstract void inputParser(int levels, int angle, int length, String inputStream);

    //get the history of game commands
    public abstract List<String> getHistory();

    //save inputted game commands to the history
    public abstract void saveHistory(String historyElement);

    //determine whether the command was valid
    public abstract boolean getValidCommand();

    //set the command to be valid or invalid
    public abstract void setValidCommand(Boolean status);

    //Method to save the user input commands to a fle
    public abstract boolean saveCommand(String inputStream, String filename);

    //get the list of instructions for the object
    public abstract LinkedList<LogoInstruction> getMyInstructions();

    //get the list of instructions by level for the LSystem object
    public abstract ArrayList<ArrayList<LogoInstruction>> getConvertedInstructionLevels();

    //get the characterizing instructions for the Darwin Creature
    public ArrayList<CreatureInstruction> getMySpeciesInstructions(int speciesKey) {
        return new ArrayList<>();
    } //not abstract
}
