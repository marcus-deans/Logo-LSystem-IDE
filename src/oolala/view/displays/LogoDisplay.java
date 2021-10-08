package oolala.view.displays;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import oolala.model.Coordinates;
import oolala.model.Instruction;
import oolala.model.ModelTurtle;
import oolala.model.commands.Commands;
import oolala.model.processors.InstructionProcessor;
import oolala.model.processors.Logo;
import oolala.view.Language;
import oolala.view.TurtleLinkage;
import oolala.view.ViewTurtle;


/**
 * JavaFX View class
 */
public class LogoDisplay extends Application {

  //  public static final String TITLE = R.string.program_name;
  public static final String TITLE = "LogoDisplay";
  public static final int FRAME_WIDTH = 733;
  public static final int FRAME_HEIGHT = 680;
  public static final Paint BACKGROUND = Color.WHITE;
  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

  //Top Layout
  public static final int GAME_TITLE_X = 10;
  public static final int GAME_TITLE_Y = 17;
  public static final int GAME_DROPDOWN_X = 100;
  public static final int GAME_DROPDOWN_Y = 0;
  public static final int SAVED_TITLE_X = 160; //210
  public static final int SAVED_TITLE_Y = 17;
  public static final int SAVED_DROPDOWN_X = 260; //320
  public static final int SAVED_DROPDOWN_Y = 0;
  public static final int HISTORY_TITLE_X = 325; //445
  public static final int HISTORY_TITLE_Y = 17;
  public static final int HISTORY_DROPDOWN_X = 375; //495
  public static final int HISTORY_DROPDOWN_Y = 0;
  public static final int LANGUAGES_TITLE_X = 440;
  public static final int LANGUAGES_TITLE_Y = 17;
  public static final int LANGUAGES_DROPDOWN_X = 520;
  public static final int LANGUAGES_DROPDOWN_Y = 0;
  public static final int TURTLES_TITLE_X = 620;
  public static final int TURTLES_TITLE_Y = 17;
  public static final int TURTLES_DROPDOWN_X = 680;
  public static final int TURTLES_DROPDOWN_Y = 0;
  public static final int MAX_DROPDOWN_WIDTH = 50;
  public static final int OFFSET_X = 10;
  public static final int OFFSET_Y = 15;
  public static final int OFFSET_Y_TOP = 26;

  //Bottom Layout
  public static final int COMMAND_WIDTH = 600;
  public static final int COMMAND_HEIGHT = 130;
  public static final int COMMAND_X = 10;
  public static final int COMMAND_Y = 530;
  public static final int RUN_WIDTH = 100;
  public static final int RUN_HEIGHT = 30;
  public static final int RUN_X = 620;
  public static final int RUN_Y = 530;
  public static final int SAVE_WIDTH = 100;
  public static final int SAVE_HEIGHT = 30;
  public static final int SAVE_X = 620;
  public static final int SAVE_Y = 565;
  public static final int CLEAR_WIDTH = 100;
  public static final int CLEAR_HEIGHT = 30;
  public static final int CLEAR_X = 620;
  public static final int CLEAR_Y = 600;

  //Line drawings
  public static final double LINE_WIDTH = 2.0;
  public static final double FULL_OPACITY = 100.0;
  public static final double NO_OPACITY = 0.0;

  //Games
  private final List<String> gameTypes = new ArrayList<>(
      Arrays.asList("Logo", "L-System", "Darwin"));
  //Languages
  private final List<String> languageTypes = new ArrayList<>(
      Arrays.asList("English", "Spanish", "French"));
  //Turtles
  private final List<TurtleLinkage> allTurtleLinkages = new ArrayList<>();
  private final List<ModelTurtle> allModelTurtles = new ArrayList<>();
  private final List<ViewTurtle> allViewTurtles = new ArrayList<>();

  private TurtleLinkage myTurtleLinkage;
  private ModelTurtle myModelTurtle;
  private ViewTurtle myViewTurtle;


  private Group root;
  private Group lineRoot;
  private Logo myLogo;
  private TextArea commandLine;
  private ComboBox savedPrograms;
  private ComboBox historyPrograms;
  private ComboBox languagesPrograms;
  private Locale langType;
  private FileInputStream fis;
  private ComboBox turtleDropdown;
  private final double penOpacity = FULL_OPACITY;
  private Text gameSettingTitle;
  private Text savedTitle;
  private Text history;
  private Text languages;
  private Text turtles;
  private String runText;
  private int turtleHomeX;
  private int turtleHomeY;


  public void start(Stage stage) {
    //Variables
    Scene scene = setupGame(FRAME_WIDTH, FRAME_HEIGHT, BACKGROUND);
    stage.setScene(scene);
    stage.setTitle(TITLE);
    stage.show();
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step()));
    animation.play();
  }

  private Scene setupGame(int width, int height, Paint background) {
    //Initialize the view classes
    myLogo = new Logo();
    spawnTurtle(0);
    gameTitle();
    initializeGameSetting(); //game type dropdown
    savedTitle();
    initializeSavedPrograms(); //saved programs dropdown
    historyTitle();
    initializeHistory(); //program history dropdown
    languagesTitle();
    initializeLanguages();
    turtleTitle();
    initializeTurtleOptions(); //dropdown of all turtles and current running turtle
    initializeCommandLine(); //initialize the command line
    initializeRunButton(); //initialize the program run button
    initializeSaveButton(); //initializes the program save button
    initializeClearScreen();
    initializeBoundaries(); // sets up program boundaries for where the turtle will move
    //Set the scene
    Scene scene = new Scene(root, width, height, background);

    //https://docs.oracle.com/javafx/2/get_started/css.htm
    scene.getStylesheets().add
        (LogoDisplay.class.getResource("LogoDisplay.css").toExternalForm());
    //https://tomsondev.bestsolution.at/2013/08/07/using-less-in-javafx/
//    LessCSSLoader ls = new LessCSSLoader();
//    scene.getStylesheets().add(ls.loadLess(getClass().getResource("logo.less")).toExternalForm());
    return scene;
  }

  private void spawnTurtle(int id) {
    myTurtleLinkage = new TurtleLinkage(id);

    myModelTurtle = myTurtleLinkage.myModelTurtle;
    myViewTurtle = myTurtleLinkage.myViewTurtle;

    allTurtleLinkages.add(myTurtleLinkage);
    allModelTurtles.add(myModelTurtle);
    allViewTurtles.add(myViewTurtle);
  }

  private void initializeBoundaries() {
    Line topLine = new Line(OFFSET_X, OFFSET_Y_TOP, FRAME_WIDTH - OFFSET_X, OFFSET_Y_TOP);
    Line leftLine = new Line(OFFSET_X, OFFSET_Y_TOP, OFFSET_X, COMMAND_Y - OFFSET_Y);
    Line rightLine = new Line(FRAME_WIDTH - OFFSET_X, OFFSET_Y_TOP, FRAME_WIDTH - OFFSET_X,
        COMMAND_Y - OFFSET_Y);
    Line bottomLine = new Line(OFFSET_X, COMMAND_Y - OFFSET_Y, FRAME_WIDTH - OFFSET_X,
        COMMAND_Y - OFFSET_Y);
    root.getChildren().add(topLine);
    root.getChildren().add(leftLine);
    root.getChildren().add(rightLine);
    root.getChildren().add(bottomLine);
  }

  private void gameTitle() {
    gameSettingTitle = new Text(getWord("game_setting_title"));
    gameSettingTitle.setLayoutX(GAME_TITLE_X);
    gameSettingTitle.setLayoutY(GAME_TITLE_Y);
    root.getChildren().add(gameSettingTitle);
  }

  private void initializeGameSetting() {
    ComboBox gameSetting = new ComboBox<>(FXCollections.observableList(gameTypes));
    gameSetting.setLayoutX(GAME_DROPDOWN_X);
    gameSetting.setLayoutY(GAME_DROPDOWN_Y);
    gameSetting.setMaxWidth(MAX_DROPDOWN_WIDTH);
    gameSetting.setOnAction((event) -> {
      //TODO: run a different program based on user selection
    });
    root.getChildren().add(gameSetting);
  }

  private void savedTitle() {
    savedTitle = new Text(getWord("saved_program_title"));
    savedTitle.setLayoutX(SAVED_TITLE_X);
    savedTitle.setLayoutY(SAVED_TITLE_Y);
    root.getChildren().add(savedTitle);
  }

  private void initializeSavedPrograms() {
    savedPrograms = new ComboBox();
    savedPrograms.setLayoutX(SAVED_DROPDOWN_X);
    savedPrograms.setLayoutY(SAVED_DROPDOWN_Y);
    savedPrograms.setMaxWidth(MAX_DROPDOWN_WIDTH);
    populateFileNames();
    savedPrograms.setOnAction((event) -> {
      getContentFromFilename();
    });
    root.getChildren().add(savedPrograms);
  }

  private void updateSavedDropdown() {
    savedPrograms.getItems().clear();
    populateFileNames();
  }

  private void getContentFromFilename() {
    String filename = savedPrograms.getSelectionModel().getSelectedItem().toString();
    File[] files = new File("data/examples/logo").listFiles();
    for (File file : files) {
      if (file.isFile() && file.getName().equals(filename)) {
        try {
          Scanner scanner = new Scanner(file);
          String input;
          StringBuffer contents = new StringBuffer();
          while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            if (!Arrays.asList(input.split("")).contains("#")) {
              contents.append(input + " ");
            }
          }
          commandLine.setText(contents.toString());
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private void populateFileNames() {
    File[] files = new File("data/examples/logo").listFiles();
    for (File file : files) {
      if (file.isFile()) {
        savedPrograms.getItems().add(file.getName());
      }
    }
  }

  private void historyTitle() {
    history = new Text(getWord("history_text"));
    history.setLayoutX(HISTORY_TITLE_X);
    history.setLayoutY(HISTORY_TITLE_Y);
    root.getChildren().add(history);
  }

  private void initializeHistory() {
    historyPrograms = new ComboBox();
    historyPrograms.setOnAction((event) -> {
      commandLine.setText(historyPrograms.getSelectionModel().getSelectedItem().toString());
    });
    historyPrograms.setLayoutX(HISTORY_DROPDOWN_X);
    historyPrograms.setLayoutY(HISTORY_DROPDOWN_Y);
    historyPrograms.setMaxWidth(MAX_DROPDOWN_WIDTH);
    root.getChildren().add(historyPrograms);
  }

  private void updateHistoryDropdown() { //TODO: make sure history is specific to current game model
    historyPrograms.getItems().clear();
    for (String element : myLogo.getHistory()) {
      historyPrograms.getItems().add(element);
    }
  }

  private void languagesTitle() {
    languages = new Text(getWord("language_text"));
    languages.setLayoutX(LANGUAGES_TITLE_X);
    languages.setLayoutY(LANGUAGES_TITLE_Y);
    root.getChildren().add(languages);
  }

  private void initializeLanguages() {
    languagesPrograms = new ComboBox(FXCollections.observableList(languageTypes));
    languagesPrograms.setOnAction((event) -> {
//      String lang = (String) languagesPrograms.getValue();
//      switch (lang) {
//        case "English":
//          Locale.setDefault(new Locale("en"));
//          updateLanguage();
//          break;
//        case "Spanish":
//          Locale.setDefault(new Locale("es"));
//          updateLanguage();
//          break;
//        case "French":
//          Locale.setDefault(new Locale("fr"));
//          updateLanguage();
//          break;
//      }
//    });
      Language lang = (Language) languagesPrograms.getValue();
      switch (lang) {
        case ENGLISH -> Locale.setDefault(new Locale("en"));
        case SPANISH -> Locale.setDefault(new Locale("es"));
        case FRENCH -> Locale.setDefault(new Locale("fr"));
        default -> throw new IllegalStateException("Unexpected value: " + lang);
      }
      updateLanguage();
    });
    languagesPrograms.setLayoutX(LANGUAGES_DROPDOWN_X);
    languagesPrograms.setLayoutY(LANGUAGES_DROPDOWN_Y);
    languagesPrograms.setMaxWidth(MAX_DROPDOWN_WIDTH);
    root.getChildren().add(languagesPrograms);
  }

  private String getWord(String key) {
    ResourceBundle words = ResourceBundle.getBundle("words");
    String value = words.getString(key);
    return value;
  }

  private void clearText() {
    gameSettingTitle.setText("");
    savedTitle.setText("");
    history.setText("");
    languages.setText("");
    turtles.setText("");
    runText = "";
  }

  private void updateLanguage() {
    clearText();
    gameTitle();
    savedTitle();
    historyTitle();
    languagesTitle();
    turtleTitle();
    runTitle();
  }

  private void turtleTitle() {
    turtles = new Text(getWord("turtles_text"));
    turtles.setLayoutX(TURTLES_TITLE_X);
    turtles.setLayoutY(TURTLES_TITLE_Y);
    root.getChildren().add(turtles);
  }

  private void initializeTurtleOptions() {
    turtleDropdown = new ComboBox();
    turtleDropdown.setOnAction((event) -> {
      //TODO: switch to this turtle
      int id = Integer.parseInt(historyPrograms.getSelectionModel().getSelectedItem().toString());
//      for (ModelTurtle modelTurtle : allModelTurtles) {
//        if (modelTurtle.myTurtleId == id) {
//          myModelTurtle = modelTurtle;
//        }
//      }
      for (TurtleLinkage turtleLinkage : allTurtleLinkages) {
        if (turtleLinkage.myID == id) {
          switchTurtleLinkage(turtleLinkage);
        }
      }
      //TODO: clear previous lines?
    });
    turtleDropdown.setLayoutX(TURTLES_DROPDOWN_X);
    turtleDropdown.setLayoutY(TURTLES_DROPDOWN_Y);
    turtleDropdown.setMaxWidth(MAX_DROPDOWN_WIDTH);
    root.getChildren().add(turtleDropdown);
  }

  private void initializeCommandLine() {
    commandLine = new TextArea();
    commandLine.setPrefWidth(COMMAND_WIDTH);
    commandLine.setPrefHeight(COMMAND_HEIGHT);
    commandLine.setLayoutX(COMMAND_X);
    commandLine.setLayoutY(COMMAND_Y);
    root.getChildren().add(commandLine);
  }

  private String runTitle() {
    return runText = getWord("run_text");
  }

  private void initializeRunButton() {
    Button runCommands = new Button(runTitle());
    runCommands.setPrefWidth(RUN_WIDTH);
    runCommands.setPrefHeight(RUN_HEIGHT);
    runCommands.setLayoutX(RUN_X);
    runCommands.setLayoutY(RUN_Y);
    root.getChildren().add(runCommands);
    runCommands.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        myLogo.inputParser(commandLine.getText());
        validateCommandStream();
        myLogo.saveHistory(commandLine.getText());
        updateHistoryDropdown();
      }
    });
  }

  private void validateCommandStream() {
    Boolean valid = myLogo.getValidCommand();
    if (!valid) { //TODO: popup doesn't work, change to alert
      Popup popup = new Popup();
      popup.setAutoFix(true);
      popup.setAutoHide(true);
      popup.setHideOnEscape(true);
      Label label = new Label("Invalid Command!");
      label.setOnMouseReleased(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
          popup.hide();
        }
      });
      popup.getContent().add(label);
      myLogo.setValidCommand(true);
    }
  }

  private void initializeSaveButton() {
    Button saveCommands = new Button("Save");
    saveCommands.setPrefWidth(SAVE_WIDTH);
    saveCommands.setPrefHeight(SAVE_HEIGHT);
    saveCommands.setLayoutX(SAVE_X);
    saveCommands.setLayoutY(SAVE_Y);
    root.getChildren().add(saveCommands);
    saveCommands.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        String filename = getUserFileName();
        myLogo.saveCommand(commandLine.getText(), filename);
        updateSavedDropdown();
      }
    });
  }

  private void initializeClearScreen() {
    Button clearScreen = new Button("Clear");
    clearScreen.setPrefWidth(CLEAR_WIDTH);
    clearScreen.setPrefHeight(CLEAR_HEIGHT);
    clearScreen.setLayoutX(CLEAR_X);
    clearScreen.setLayoutY(CLEAR_Y);
    root.getChildren().add(clearScreen);
    clearScreen.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        //TODO: call turtle.home method, delete lines
        commandLine.clear();
        historyPrograms.getItems().clear();
        turtleDropdown.getItems().clear();
//        myTurtle = new Turtle(turtleHomeX, turtleHomeY, 0); //TODO: make sure this spawns correctly, should reset program
        myModelTurtle = new ModelTurtle(0);
      }
    });
  }

  private String getUserFileName() {
    TextInputDialog getUserInput = new TextInputDialog();
    getUserInput.setHeaderText("Enter a filename:");
    String fileName = getUserInput.showAndWait().toString();
    if (validateStringFilenameUsingIO(fileName)) {
      return fileName;
    } else {
      //TODO: throw error - invalid filename, make user try again
      return "";
    }
  }

  private boolean validateStringFilenameUsingIO(String filename) {
    File file = new File(filename);
    boolean created = false;
    try {
      created = file.createNewFile();
      return created;
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (created) {
        file.delete();
      }
    }
    return false;
  }

  private void tellTurtle(int id) {
    //loop through allTurtles - if ID exists, switch to this turtle
    boolean exists = false;
//    for (ModelTurtle modelTurtle : allModelTurtles) {
//      if (modelTurtle.myTurtleId == id) {
//        myModelTurtle = modelTurtle; //switch current turtle to this turtle
//        exists = true;
//        turtleDropdown.setValue(
//            modelTurtle.myTurtleId); //TODO: make sure this works, java being laggy
//        turtleDropdown.setValue(
//            modelTurtle.myTurtleId); //TODO: make sure this works, java being laggy
//      }
//    }
//    if (!exists) {//id doesn't exist - create new turtle with this ID
////        myTurtle = new Turtle(turtleHomeX,turtleHomeY,id);
//      myModelTurtle = new ModelTurtle(id);
//      allModelTurtles.add(myModelTurtle);
//      turtleDropdown.getItems().add(myModelTurtle.myTurtleId);
//      turtleDropdown.setValue(
//          myModelTurtle.myTurtleId); //TODO: make sure this works, java being laggy
//      //TODO: inform user that a new turtle has been spawned
//    }
    for (TurtleLinkage turtleLinkage : allTurtleLinkages) {
      if (turtleLinkage.myID == id) {
        switchTurtleLinkage(turtleLinkage);
        exists = true;
        turtleDropdown.setValue(
            turtleLinkage.myID); //TODO: make sure this works, java being laggy
        turtleDropdown.setValue(
            turtleLinkage.myID); //TODO: make sure this works, java being laggy
      }
    }
    if (!exists) {//id doesn't exist - create new turtle with this ID
//        myTurtle = new Turtle(turtleHomeX,turtleHomeY,id);
//      myModelTurtle = new ModelTurtle(id);
      spawnTurtle(id);
      turtleDropdown.getItems().add(myTurtleLinkage.myID);
      turtleDropdown.setValue(
          myTurtleLinkage.myID); //TODO: make sure this works, java being laggy
      //TODO: inform user that a new turtle has been spawned
    }
  }

  private void switchTurtleLinkage(TurtleLinkage turtleLinkage) {
    myTurtleLinkage = turtleLinkage;
    myModelTurtle = turtleLinkage.myModelTurtle;
    myViewTurtle = turtleLinkage.myViewTurtle;
  }

  //Create method that passes in queue of commands to Logo
  private void step() {
    //If an instruction has been sent to myLogo, run it
    Queue<Instruction> instructions = myLogo.getMyInstructions();
    if (!instructions.isEmpty()) {
      Instruction currentInstruction = instructions.poll(); //pop a single instruction, FIFO
      executeInstruction(currentInstruction, myTurtleLinkage, root);
//      myTurtleLinkage.update();
      //TODO: create map (possibly global) ->
      drawTurtleLine();
      myModelTurtle.updateCoordinates();
    }
  }

  private void executeInstruction(Instruction currentInstruction, TurtleLinkage turtleLinkage,
      Group root) {
    if (currentInstruction.order == Commands.TELL) {
      tellTurtle(turtleLinkage.myID);
    }
    InstructionProcessor instructionProcessor = new InstructionProcessor(currentInstruction,
        turtleLinkage, root);
  }


  private void drawTurtleLine() {
    Coordinates turtleCoordinates = myModelTurtle.getTurtleCoordinates();
    Rectangle connector = new Rectangle(turtleCoordinates.turtleOldX, turtleCoordinates.turtleNewY,
        turtleCoordinates.turtleNewX, turtleCoordinates.turtleNewY);
    connector.setOpacity(penOpacity);
    connector.setFill(Color.RED);
    connector.setWidth(LINE_WIDTH);
    root.getChildren().add(connector);
  }

}