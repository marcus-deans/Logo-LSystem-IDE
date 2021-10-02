package oolala.turtle;

import javafx.application.Application;
import javafx.stage.Stage;
import oolala.model.Instruction;
import oolala.model.Turtle;
import oolala.view.LogoDisplay;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TurtleComputeTests {
  Instruction inst = new Instruction("fd", 50);
//  Turtle turt = new Turtle(0,0,0);'
  Turtle turt = new Turtle(1);

  @Test
  void checkVector45() {
//    LogoDisplay logod = new LogoDisplay();
    Application app = new LogoDisplay();
    LogoDisplay logod = (LogoDisplay) app;
    logod.myTurtle.setDegreesRotation(45);
//    Assertions.assertTrue(logod.myTurtle.checkVectors());
  }

  @Test
  void checkVector225() {
    LogoDisplay logod = new LogoDisplay();
//    Turtle turt = logod.myTurtle;
//    Turtle turt = new Turtle(100,100,1);
    turt.setDegreesRotation(225);
//    Assertions.assertTrue(turt.checkVectors());
  }

  @Test
  void checkVector270() {
    LogoDisplay logod = new LogoDisplay();
    Turtle turt = logod.myTurtle;
    turt.setDegreesRotation(270);
//    Assertions.assertFalse(turt.checkVectors());
  }
}
