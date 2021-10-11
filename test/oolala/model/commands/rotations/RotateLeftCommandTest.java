package oolala.model.commands.rotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import oolala.model.ModelTurtle;
import org.junit.jupiter.api.Test;

class RotateLeftCommandTest {

    ModelTurtle myModelTurtle = new ModelTurtle(0);

    @Test
    void computeDegreesRotationLeftPositive() {
        RotateLeftModelCommand rlc = new RotateLeftModelCommand(myModelTurtle, 50);
        rlc.setLeftRotation();
        assertEquals(myModelTurtle.getDegreesRotation(), -100);
    }

    @Test
    void computeDegreesRotationLeftNegative() {
        RotateLeftModelCommand rlc = new RotateLeftModelCommand(myModelTurtle, -75);
        rlc.setLeftRotation();
        assertEquals(myModelTurtle.getDegreesRotation(), 150);
    }

}