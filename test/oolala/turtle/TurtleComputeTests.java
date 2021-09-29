package oolala.turtle;

import oolala.model.Instruction;
import oolala.model.Turtle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TurtleComputeTests {
  Instruction inst = new Instruction("fd", 50);


  @Test
  void checkVector45() {
    Turtle turt = new Turtle(0,0,0);
    turt.degreesRotation = 45;
    Assertions.assertTrue(turt.checkVectors());
  }

  @Test
  void checkVector225() {
    Turtle turt = new Turtle(0,0,0);
    turt.degreesRotation = 225;
    Assertions.assertTrue(turt.checkVectors());
  }
}
