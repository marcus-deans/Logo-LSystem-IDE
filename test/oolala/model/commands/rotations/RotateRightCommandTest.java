package oolala.model.commands.rotations;

import oolala.model.ModelTurtle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RotateRightCommandTest {

    ModelTurtle myModelTurtle = new ModelTurtle(0);

    @Test
    void computeDegreesRotationRightPositive() {
        RotateRightModelCommand rrc = new RotateRightModelCommand(myModelTurtle, 100);
        rrc.setRightRotation();
        assertEquals(myModelTurtle.getDegreesRotation(), 200);
    }

    @Test
    void computeDegreesRotationRightNegative() {
        RotateRightModelCommand rrc = new RotateRightModelCommand(myModelTurtle, -60);
        rrc.setRightRotation();
        assertEquals(myModelTurtle.getDegreesRotation(), -120);
    }

}