package oolala.view.displays;

import java.io.File;

public class LSystemDisplay extends Display {

    @Override
    protected void handleInputParsing(String text) {

    }

    @Override
    protected File[] getFilesFromPath() {
        return new File("data/examples/lsystem").listFiles();
    }
}
