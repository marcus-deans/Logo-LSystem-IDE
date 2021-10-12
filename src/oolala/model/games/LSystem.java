package oolala.model.games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import oolala.model.instructions.CreatureInstruction;
import oolala.model.instructions.LSystemInstruction;
import oolala.model.instructions.LogoInstruction;

/**
 * @author naylaboorady, marcusdeans Purpose: create backend game for Logo Assumptions: running
 * display, error-checked inputs Dependencies: None Example Usage: serves as backend for playing
 * Logo User Details: getMyInstruction(), getmySpeciesInstructions() should not be used
 */
public class LSystem extends Game {

    private final List<String> validCommands;
    private final List<String> doubleAngleCommands;
    private final List<String> doubleLengthCommands;
    private final List<String> singleCommands;

    private final Map<String, String> userRules; //RULE command
    private Map<String, List<String>> commandConversion; //SET command

    private final List<String> expansionLevels; //expansions in LSystem language
    private final ArrayList<ArrayList<LogoInstruction>> convertedInstructionLevels; //expansions by level in Logo instruction format

    private final List<String> myHistory;
    private boolean isValidCommand;

    /**
     * Create new LSystem backend
     */
    public LSystem() {
        myHistory = new ArrayList<>();
        validCommands = new ArrayList<>(
            Arrays.asList("F", "f", "G", "g", "A", "a", "B", "b", "X", "x", "+", "-"));
        doubleAngleCommands = new ArrayList<>(Arrays.asList("lt", "rt"));
        doubleLengthCommands = new ArrayList<>(Arrays.asList("fd", "bk"));
        singleCommands = new ArrayList<>(Arrays.asList("pd", "pu", "st", "ht", "home", "stamp"));
        userRules = new HashMap<>();
        commandConversion = new HashMap<>();
        expansionLevels = new ArrayList<>();
        convertedInstructionLevels = new ArrayList<>();
        initializeCommandConversions();
        isValidCommand = true;
    }

    //setup conversion for pre-defined dictionary as specified in projet details
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

    //TODO: ignore lines that start with #

    /**
     * Create input parser that will parse the command line input of the program
     *
     * @param levels      the number of levels for the program
     * @param angle       the angle to be used when drawing
     * @param length      the number of pixels that line segments should be
     * @param inputStream input from the command line
     */
    public void inputParser(int levels, int angle, int length, String inputStream) {
        isValidCommand = true;
        List<String> inputCommands = Arrays.asList(
            inputStream.split("\\s+")); //split by any space or tab
        int skip = 0;
        for (int i = 0; i < inputCommands.size(); i++) {
            if (skip > 0) {
                skip--;
                continue;
            }
            if (inputCommands.get(i).matches("[a-zA-Z]+") && inputCommands.get(i)
                .equalsIgnoreCase("start")) { //TODO: make string global
                expansionLevels.add(inputCommands.get(i + 1)); //first letter to expand on
                skip++;
            } else if (inputCommands.get(i).matches("[a-zA-Z]+") && inputCommands.get(i)
                .equalsIgnoreCase("rule")) {//TODO: make string global
                userRules.put(inputCommands.get(i + 1), inputCommands.get(i + 2));
                skip += 2;
            } else if (inputCommands.get(i).matches("[a-zA-Z]+") && inputCommands.get(i)
                .equalsIgnoreCase("set")) {//TODO: make string global
                List<String> instructionDefinition = getInstructionsInsideQuotes(i + 2,
                    inputCommands);
                commandConversion.put(inputCommands.get(i + 1), instructionDefinition);
                skip += instructionDefinition.size() + 1; //skip letter and definition
            } else { //TODO: error handling - invalid command stream
                isValidCommand = false;
                break;
            }
        }
        expandLSystemCommands(levels, angle, length); //now, create the expansion
    }

    //Method that fills the expansionLevels ArrayList, which expands levels into LSystem langugae
    private void expandLSystemCommands(int levels, int angle, int length) {
        for(int level=1; level<levels; level++){ //level 0 already added in inputParser with "start" parsing
            List<String> commandsToExpand = Arrays.asList(expansionLevels.get(level-1).split("")); //previous level to expand on
            StringBuilder expansion = new StringBuilder();
            for(int currCommand=0; currCommand<commandsToExpand.size(); currCommand++){
                if (userRules.containsKey(commandsToExpand.get(
                    currCommand))) { //if there is a rule for this character, append the rule
                    expansion.append(userRules.get(commandsToExpand.get(currCommand)));
                } else {//otherwise, append the character
                    expansion.append(commandsToExpand.get(currCommand));
                }
            }
            expansionLevels.add(expansion.toString()); //add this expansion to the index of its level
        }
        convertToLogoCommands(levels, angle, length); //now, convert the LSystem expansion into Logo commands
    }

    //iterate through levels and call conversion to logo commands
    private void convertToLogoCommands(int levels, int angle, int length) {
        for(int i=0; i<expansionLevels.size(); i++){ //each expansion level
            ArrayList<LogoInstruction> thisLevelInstructions = createCommandsFromLSystem(levels,
                angle,
                length / (i + 1), expansionLevels.get(i));
            convertedInstructionLevels.add(thisLevelInstructions);
        }
    }

    //obtain instructions that are inside double quotes
    private List<String> getInstructionsInsideQuotes(int startingIndex, List<String> inputCommands) {
        List<String> instructions = new ArrayList<>();
        for(int i=startingIndex; i<inputCommands.size(); i++){
            if(inputCommands.get(i).startsWith("\"")){
                instructions.add(inputCommands.get(i).substring(1));
            }else if(inputCommands.get(i).endsWith("\"")){
                instructions.add(inputCommands.get(i)); //TODO: make sure this properly indexes
                break;
            }else{
                instructions.add(inputCommands.get(i));
            }
        }
        return instructions;
    }

    //convert all of the expanded instructions to existing Logo commands that can be executed
    private ArrayList<LogoInstruction> createCommandsFromLSystem(int level, int angle, int length,
        String commandStream) {
        String[] commandStreamSplit = commandStream.split("");
        ArrayList<LogoInstruction> instructions = new ArrayList<>();
        for (String currentLSystemCommand : commandStreamSplit) {
            List<String> logoCommands; //equivalent logo commands for this LSystem character
            if (currentLSystemCommand.matches(
                "[a-zA-Z]+")) { //Handles case sensitivity for alphabetic LSystem commands
                logoCommands = commandConversion.get(currentLSystemCommand.toUpperCase());
            } else {
                logoCommands = commandConversion.get(currentLSystemCommand);
            }
            for (String currentLogoCommand : logoCommands) {
                instructions.add(convertLogoCommandToInstruction(level, angle, length, currentLogoCommand));
            }
        }
        return instructions;
    }

    //convert each Logo command to an executable instruction
    private LogoInstruction convertLogoCommandToInstruction(int level, int angle, int length,
        String thisCommand) {
        if (singleCommands.contains(thisCommand)) { //if this is single command
            return new LSystemInstruction(level, thisCommand);
        } else if (doubleAngleCommands.contains(thisCommand)) { //double command
            return new LSystemInstruction(level, thisCommand, angle);
        } else {
            return new LSystemInstruction(level, thisCommand, length);
        }
    }

    /**
     * Save the input to a text file
     *
     * @param inputStream command line input from Display
     * @param filename    String filename of file to be saved
     * @return confirmation that files was saved
     */
    public boolean saveCommand(String inputStream, String filename) {
        StringBuilder sb = new StringBuilder();
        sb.append("data/examples/lsystem/");
        sb.append(filename);
        sb.append(".txt");
        return saveCommandGivenPath(inputStream, sb.toString());
    }

    //create a command that consists of a word and an integer value
    @Override
    protected void createDoubleCommand(String command, Integer number) {
        String alpha = command;
        int bravo = number;
    }

    //create a command that consists of a single word
    protected void createSingleCommand(String command) {
        String alpha = command;
    }

    /**
     * Get history of commands
     *
     * @return list of command strings
     */
    public List<String> getHistory() {
        return myHistory;
    }

    /**
     * Save a specific string to the history of the file
     *
     * @param historyElement element to be added to historu
     */
    @Override
    public void saveHistory(String historyElement) {
        myHistory.add(historyElement);
    }

    /**
     * Get the commands that characterize a species, defunct command
     *
     * @param speciesKey the species of interest
     * @return empty ArrayList as method is not to be used
     */
    @Override
    public ArrayList<CreatureInstruction> getMySpeciesInstructions(int speciesKey) {
        return new ArrayList<CreatureInstruction>();
    }

    /**
     * Get instructions that have been parsed, defunct command
     *
     * @return empty LinkedList as method is not to be used
     */
    @Override
    public LinkedList<LogoInstruction> getMyInstructions() {
        return new LinkedList<LogoInstruction>();
    }


    /**
     * Determine whether the command was valid
     *
     * @return boolean whether valid
     */
    @Override
    public boolean getValidCommand() {
        return isValidCommand;
    }

    /**
     * Set the status of valid command
     *
     * @param status status that valid command should be set to
     */
    public void setValidCommand(Boolean status) {
        isValidCommand = status;
    }


    /**
     * expansions by level in Logo instruction format
     *
     * @return ArrayList of each level, with each level containg LogoInstructions
     */
    public ArrayList<ArrayList<LogoInstruction>> getConvertedInstructionLevels() {
        return convertedInstructionLevels;
    }
}
