package oolala.model.games;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import oolala.model.instructions.CreatureInstruction;
import oolala.model.instructions.LogoInstruction;

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

    public Game() {
    }

    //Method to save the user input commands to a fle
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

    protected boolean nextCommandIsInt(int index, List<String> inputCommands) {
        boolean nextCommandIsInteger = true;
        try {
            Integer.parseInt(inputCommands.get(index + 1));
        } catch (NumberFormatException e) {
            nextCommandIsInteger = false;
        }
        return nextCommandIsInteger;
    }

    protected void createDoubleCommand(String command, Integer number) {
    } //NOT abstract

    protected void createSingleCommand(String command) {
    } //NOT abstract

    public abstract void inputParser(int levels, int angle, int length, String inputStream);

    public abstract List<String> getHistory();

    public abstract void saveHistory(String historyElement);

    public abstract boolean getValidCommand();

    public abstract void setValidCommand(Boolean status);

    //Method to save the user input commands to a fle
    public abstract boolean saveCommand(String inputStream, String filename);

    public abstract LinkedList<LogoInstruction> getMyInstructions();

    public abstract ArrayList<ArrayList<LogoInstruction>> getConvertedInstructionLevels();

    public ArrayList<CreatureInstruction> getMySpeciesInstructions(int speciesKey) {
        return new ArrayList<>();
    } //not abstract
}
