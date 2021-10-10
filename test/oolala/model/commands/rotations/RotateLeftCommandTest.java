package oolala.model.commands.rotations;

import oolala.model.ModelTurtle;
import oolala.model.commands.movements.ForwardModelCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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