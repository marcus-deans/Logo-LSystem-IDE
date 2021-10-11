package oolala.model.commands.rotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import oolala.model.ModelTurtle;
import oolala.view.TurtleLinkage;
import org.junit.jupiter.api.Test;

class RotateRightCommandTest {

    ModelTurtle myModelTurtle = new ModelTurtle(0);
    TurtleLinkage turtLink = new TurtleLinkage(0);

    @Test
    void computeDegreesRotationRightPositive() {
        RotateRightModelCommand rrc = new RotateRightModelCommand(turtLink, 100);
//        rrc.setRightRotation();
        assertEquals(myModelTurtle.getDegreesRotation(), 200);
    }

    @Test
    void computeDegreesRotationRightNegative() {
        RotateRightModelCommand rrc = new RotateRightModelCommand(turtLink, -60);
//        rrc.setRightRotation();
        assertEquals(myModelTurtle.getDegreesRotation(), -120);
    }

}