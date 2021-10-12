package oolala.model.processors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import oolala.model.instructions.CreatureInstruction;
import oolala.model.instructions.Instruction;
import oolala.model.instructions.LSystemInstruction;

public class LSystem extends GameProcessor{

    public List<String> validCommands;
    public List<String> doubleAngleCommands;
    public List<String> doubleLengthCommands;
    public List<String> singleCommands;

    public Map<String, String> userRules; //RULE command
    public Map<String, List<String>> commandConversion; //SET command

    public List<String> expansionLevels; //expansions in LSystem language

    public ArrayList<ArrayList<Instruction>> convertedInstructionLevels; //expansions by level in Logo instruction format
    private final LinkedList<Instruction> myInstructions; //TODO: do we need this? prob not

    private final List<String> myHistory;
    private boolean isValidCommand;


    public LSystem() {
        myInstructions = new LinkedList<>();
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

        myInstructions.add(new LSystemInstruction(0, "ht"));
        isValidCommand = true;
    }

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
    //Method to parse the input
    public void inputParser(int levels, int angle, int length, String inputStream) {
        isValidCommand = true;
        List<String> inputCommands = Arrays.asList(inputStream.split("\\s+")); //split by any space or tab
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

    private void convertToLogoCommands(int levels, int angle, int length) {
        for(int i=0; i<expansionLevels.size(); i++){ //each expansion level
            ArrayList<Instruction> thisLevelInstructions = createCommandsFromLSystem(levels, angle,
                length / (i + 1), expansionLevels.get(i));
            convertedInstructionLevels.add(thisLevelInstructions);
        }
    }


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


    private ArrayList<Instruction> createCommandsFromLSystem(int level, int angle, int length,
        String commandStream) {
        String[] commandStreamSplit = commandStream.split("");
        ArrayList<Instruction> instructions = new ArrayList<>();
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

    private Instruction convertLogoCommandToInstruction(int level, int angle, int length, String thisCommand) {
        if(singleCommands.contains(thisCommand)){ //if this is single command
            return new LSystemInstruction(level, thisCommand);
        }else if(doubleAngleCommands.contains(thisCommand)){ //double command
            return new LSystemInstruction(level, thisCommand, angle);
        } else {
            return new LSystemInstruction(level, thisCommand, length);
        }
    }

    public boolean saveCommand(String inputStream, String filename) {
        String path = "data/examples/lsystem" + filename + ".txt";
        return saveCommandGivenPath(inputStream, path);
    }

    @Override
    protected void createDoubleCommand(String command, Integer number) {
        String alpha = command;
        int bravo = number;
    }

    protected void createSingleCommand(String command) {
        String alpha = command;
    }

    public List<String> getHistory() {
        return myHistory;
    }

    @Override
    public void saveHistory(String historyElement) {
        myHistory.add(historyElement);
    }

    @Override
    public boolean getValidCommand() {
        return isValidCommand;
    }

    public void setValidCommand(Boolean status) {
        isValidCommand = status;
    }

    @Override
    public LinkedList<Instruction> getMyInstructions() {
        return myInstructions;
    }

    //expansions by level in Logo instruction format
    public ArrayList<ArrayList<Instruction>> getConvertedInstructionLevels() {
        return convertedInstructionLevels;
    }

    @Override
    public ArrayList<CreatureInstruction> getMySpeciesInstructions(int speciesKey) {
        return null;
    }
}
