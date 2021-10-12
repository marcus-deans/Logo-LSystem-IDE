package oolala.model.commands.rotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import oolala.model.ModelTurtle;
import oolala.view.TurtleLinkage;
import org.junit.jupiter.api.Test;

class RotateRightCommandTest {

    ModelTurtle myModelTurtle = new ModelTurtle(0);

    @Test
    void computeDegreesRotationRightPositive() {
        RotateRightModelCommand rrc = new RotateRightModelCommand(myModelTurtle, 100);
        assertEquals(myModelTurtle.getDegreesRotation(), 100);
    }

    @Test
    void computeDegreesRotationRightNegative() {
        RotateRightModelCommand rrc = new RotateRightModelCommand(myModelTurtle, -60);
        assertEquals(myModelTurtle.getDegreesRotation(), 300);
    }

}