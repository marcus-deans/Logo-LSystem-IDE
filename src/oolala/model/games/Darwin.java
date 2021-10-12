package oolala.model.games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import oolala.model.instructions.CreatureInstruction;
import oolala.model.instructions.LogoInstruction;

/**
 * @author naylaboorady
 * <p>
 * Purpose: Create a model that parses user command input on the front-end and uses it to
 * create a creature. Stores a HashMap of existing creatures in the world and their unique ID
 * to be referenced on the front-end
 */
public class Darwin extends Game {

    private static final String MOVE = "move";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";
    private static final String INFECT = "infect";
    private static final String IFEMPTY = "ifempty";
    private static final String IFWALL = "ifwall";
    private static final String IFSAME = "ifsame";
    private static final String IFENEMY = "ifenemy";
    private static final String IFRANDOM = "ifrandom";
    private static final String GO = "go";

    private ArrayList<String> doubleCommands;
    private ArrayList<String> singleCommands;
    private ArrayList<CreatureInstruction> myInstructions;
    private final HashMap<Integer, ArrayList<CreatureInstruction>> mySpeciesInstructions;
    private final List<String> myHistory;
    boolean isValidCommand;

    public Darwin() {
        singleCommands = new ArrayList<>(Arrays.asList(INFECT));
        doubleCommands = new ArrayList<>(
            Arrays.asList(MOVE, LEFT, RIGHT, IFEMPTY, IFWALL, IFSAME, IFENEMY, IFRANDOM, GO));
        myInstructions = new ArrayList<>();
        mySpeciesInstructions = new HashMap<>();
        myHistory = new ArrayList<>();
        isValidCommand = true;
    }

    //TODO: ignore lines that start with #
    public void inputParser(int nearbyThreshold, int speciesIdentifier, int length, String inputStream) {
        myInstructions.clear();
        isValidCommand = true;
        List<String> inputCommands = Arrays.asList(
            inputStream.split("\\s+")); //split by any space or tab
        for (int i = 0; i < inputCommands.size(); i++) {
            if (inputCommands.get(i).matches("[a-zA-Z]+") && singleCommands.contains(
                inputCommands.get(i).toLowerCase())) { //Valid single command
                createSingleCommand(inputCommands.get(i));
            } else if (inputCommands.get(i).matches("[a-zA-Z]+") && doubleCommands.contains(
                inputCommands.get(i).toLowerCase()) && i < inputCommands.size() && nextCommandIsInt(
                i, inputCommands)) { //Valid double command (requires a second number)
                createDoubleCommand(inputCommands.get(i),
                    Integer.valueOf(inputCommands.get(i + 1)));
                break;
            } else { //Not a valid command stream
                isValidCommand = false; //TODO: notify user that input was invalid
                break;
            }
        }
        if (isValidCommand) {
            mySpeciesInstructions.put(speciesIdentifier, myInstructions);
        }
    }

    @Override
    protected void createSingleCommand(String command) {
        CreatureInstruction singleInstruction = new CreatureInstruction(command);
        myInstructions.add(singleInstruction);
    }

    @Override
    protected void createDoubleCommand(String command, Integer valueOf) {
        CreatureInstruction doubleInstruction = new CreatureInstruction(command, valueOf);
        myInstructions.add(doubleInstruction);
    }

    public boolean saveCommand(String inputStream, String filename) {
        StringBuilder sb = new StringBuilder();
        sb.append("data/examples/darwin/");
        sb.append(filename);
        sb.append(".txt");
        return saveCommandGivenPath(inputStream, sb.toString());
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

    public LinkedList<LogoInstruction> getMyInstructions() {
        return new LinkedList<>();
    }

//    public HashMap<Integer, ArrayList<CreatureInstruction>> getMySpeciesInstructions() {
//        return mySpeciesInstructions;
//    }
    public ArrayList<CreatureInstruction> getMySpeciesInstructions(int speciesKey){
        return mySpeciesInstructions.get(speciesKey);
    }

    //expansions by level in Logo instruction format
    public ArrayList<ArrayList<LogoInstruction>> getConvertedInstructionLevels() {
        return new ArrayList<ArrayList<LogoInstruction>>();
    }
}
