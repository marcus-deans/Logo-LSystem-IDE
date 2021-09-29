package oolala.model;

public class Instruction {
    public int pixels;
    public String command;
    public Commands order;

    public Instruction(String inCommand, int inPixels) {
        this(inCommand);
        this.pixels = inPixels;
    }

    public Instruction(String inCommand) {
        this.command = inCommand;
        computeCommands();
    }

    private void computeCommands() {
        switch (this.command) {
            case "fd":
                this.order = Commands.FORWARD;
                break;
            case "bk":
                this.order = Commands.BACKWARD;
                break;
            case "rt":
                this.order = Commands.RIGHT;
                break;
            case "lt":
                this.order = Commands.LEFT;
                break;
            case "home":
                this.order = Commands.HOME;
                break;
            case "ht":
                this.order = Commands.HIDE;
                break;
            case "st":
                this.order = Commands.SHOW;
                break;
            case "stamp":
                this.order = Commands.STAMP;
                break;
            default:
                this.order = Commands.OOPS;
        }
    }

}
