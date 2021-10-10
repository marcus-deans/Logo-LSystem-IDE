package oolala.model.processors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import oolala.model.instructions.CreatureInstruction;
import oolala.model.instructions.Instruction;

public class Darwin extends GameProcessor{

    public static final String MOVE = "move";
    public static final String LEFT = "left";
    public static final String RIGHT = "right";
    public static final String INFECT = "infect";
    public static final String IFEMPTY = "ifempty";
    public static final String IFWALL = "ifwall";
    public static final String IFSAME = "ifsame";
    public static final String IFENEMY = "ifenemy";
    public static final String IFRANDOM = "ifrandom";
    public static final String GO = "go";

    public ArrayList<String> doubleCommands;
    public ArrayList<String> singleCommands;
    public Queue<Instruction> myInstructions;
    private final List<String> myHistory;
    boolean isValidCommand;

    public Darwin() {
        singleCommands = new ArrayList<>(Arrays.asList(INFECT));
        doubleCommands = new ArrayList<>(Arrays.asList(MOVE, LEFT, RIGHT, IFEMPTY, IFWALL, IFSAME, IFENEMY, IFRANDOM, GO));
        myInstructions = new LinkedList<>();
        myHistory = new ArrayList<>();
        isValidCommand = true;
    }

    //TODO: ignore lines that start with #
    @Override
    public void inputParser(int radius, int angle, int length, String inputStream) {
        isValidCommand = true;
        List<String> inputCommands = Arrays.asList(inputStream.split("\\s+")); //split by any space or tab
        for(int i=0; i<inputCommands.size(); i++){
            if(inputCommands.get(i).matches("[a-zA-Z]+") && singleCommands.contains(inputCommands.get(i).toLowerCase())){ //Valid single command
                createSingleCommand(inputCommands.get(i));
            }else if(inputCommands.get(i).matches("[a-zA-Z]+") && doubleCommands.contains(inputCommands.get(i).toLowerCase()) && i < inputCommands.size() && nextCommandIsInt(i, inputCommands)){ //Valid double command (requires a second number)
                createDoubleCommand(inputCommands.get(i), Integer.valueOf(inputCommands.get(i+1)));
                break;
            }else{ //Not a valid command stream
                isValidCommand = false; //TODO: notify user that input was invalid
                break;
            }
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

    public void saveCommand(String inputStream, String filename) {
        String path = "data/examples/darwin" + filename + ".txt";
        saveCommandGivenPath(inputStream, path);
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

    public Queue<Instruction> getMyInstructions() {
        return myInstructions;
    }
}
