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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import oolala.model.games.Game;
import oolala.model.games.Logo;
import oolala.model.instructions.LogoInstruction;
import oolala.view.TurtleLinkage;


/**
 * JavaFX View class
 */
public abstract class Display extends Application {

  //  public static final String TITLE = R.string.program_name;
  protected static final String TITLE = "Display";
  public static final int FRAME_WIDTH = 733;
  public static final int FRAME_HEIGHT = 680;
  protected static final Paint BACKGROUND = Color.WHITE;
  protected static final int FRAMES_PER_SECOND = 7;
  protected static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

  //Top Layout
  protected static final int GAME_TITLE_X = 10;
  protected static final int GAME_TITLE_Y = 17;
  protected static final int GAME_DROPDOWN_X = 100;
  protected static final int GAME_DROPDOWN_Y = 0;
  protected static final int SAVED_TITLE_X = 160; //210
  protected static final int SAVED_TITLE_Y = 17;
  protected static final int SAVED_DROPDOWN_X = 260; //320
  protected static final int SAVED_DROPDOWN_Y = 0;
  protected static final int HISTORY_TITLE_X = 325; //445
  protected static final int HISTORY_TITLE_Y = 17;
  protected static final int HISTORY_DROPDOWN_X = 375; //495
  protected static final int HISTORY_DROPDOWN_Y = 0;
  protected static final int LANGUAGES_TITLE_X = 440;
  protected static final int LANGUAGES_TITLE_Y = 17;
  protected static final int LANGUAGES_DROPDOWN_X = 520;
  protected static final int LANGUAGES_DROPDOWN_Y = 0;
  protected static final int CREATURES_TITLE_X = 620;
  protected static final int CREATURES_TITLE_Y = 17;
  protected static final int CREATURES_DROPDOWN_X = 680;
  protected static final int CREATURES_DROPDOWN_Y = 0;
  protected static final int MAX_DROPDOWN_WIDTH = 50;
  protected static final int OFFSET_X = 10;
  protected static final int OFFSET_Y = 15;
  protected static final int OFFSET_Y_TOP = 40;

  //Bottom Layout
  protected static final int COMMAND_WIDTH = 600;
  public static final int COMMAND_HEIGHT = 130;
  protected static final int COMMAND_X = 10;
  protected static final int COMMAND_Y = 530;
  protected static final int RUN_WIDTH = 100;
  protected static final int RUN_HEIGHT = 30;
  protected static final int RUN_X = 620;
  protected static final int RUN_Y = 530;
  protected static final int SAVE_WIDTH = 100;
  protected static final int SAVE_HEIGHT = 30;
  protected static final int SAVE_X = 620;
  protected static final int SAVE_Y = 565;
  protected static final int CLEAR_WIDTH = 100;
  protected static final int CLEAR_HEIGHT = 30;
  protected static final int CLEAR_X = 620;
  protected static final int CLEAR_Y = 600;

  //Games
  protected final List<String> gameTypes = new ArrayList<>(
      Arrays.asList("Logo", "L-System", "Darwin"));
  //Languages
  protected final List<String> languageTypes = new ArrayList<>(
      Arrays.asList("English", "Spanish", "French"));

  protected Group root = new Group();
  protected Timeline myAnimation;
  protected Scene scene;
  protected Game myGameProcessor;
  protected TextArea commandLine;
  protected ComboBox savedPrograms;
  protected ComboBox historyPrograms;
  protected ComboBox languagesPrograms;
  protected Locale langType;
  protected FileInputStream fis;
  protected ComboBox turtleDropdown;
  protected Text gameSettingTitle;
  protected Text savedTitle;
  protected Text history;
  protected Text languages;
  protected Text creaturesText; //logo and darwin
  protected Text creatureRadiusText; //darwin
  protected Text animationSpeedText; //darwin
  protected Text stepLengthText; //lsystem
  protected Text rotationAngleText; //lsystem
  protected Text totalLevelsText; //lsystem
  protected String runText;
  protected int turtleHomeX;
  protected int turtleHomeY;


  public void start(Stage stage) {
    //Variables
    scene = setupGame(FRAME_WIDTH, FRAME_HEIGHT, BACKGROUND);
    stage.setScene(scene);
    stage.setTitle(TITLE);
    stage.show();
    myAnimation = new Timeline();
    myAnimation.setCycleCount(Timeline.INDEFINITE);
    myAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step()));
    myAnimation.play();
  }

  protected Scene setupGame(int width, int height, Paint background) {
    //Initialize the view classes
    myGameProcessor = new Logo();
    this.root = new Group();
    performInitialSetup();
    //Set the scene
    scene = new Scene(root, width, height, background);
    scene.getStylesheets().add(Display.class.getResource("Display.css").toExternalForm());
    return scene;
  }

  protected void performInitialSetup() {
    gameTitle();
    initializeGameSetting(); //game type dropdown
    savedTitle();
    initializeSavedPrograms(); //saved programs dropdown
    historyTitle();
    initializeRunButton(runTitle()); //initialize the program run button
    initializeHistory(); //program history dropdown
    languagesTitle();
    initializeLanguages();
    initializeCommandLine(); //initialize the command line
    initializeSaveButton(); //initializes the program save button
    initializeClearScreen();
    initializeBoundaries(); // sets up program boundaries for where the turtle will move
  }

  protected void initializeBoundaries() {
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

  protected void gameTitle() {
    gameSettingTitle = new Text(getWord("game_setting_title"));
    gameSettingTitle.setLayoutX(GAME_TITLE_X);
    gameSettingTitle.setLayoutY(GAME_TITLE_Y);
    this.root.getChildren().add(gameSettingTitle);
  }

  protected void initializeGameSetting() {
    ComboBox gameSetting = new ComboBox<>(FXCollections.observableList(gameTypes));
    gameSetting.setLayoutX(GAME_DROPDOWN_X);
    gameSetting.setLayoutY(GAME_DROPDOWN_Y);
    gameSetting.setMaxWidth(MAX_DROPDOWN_WIDTH);
    gameSetting.setOnAction((event) -> { //TODO: make sure this works to switch the game
      String game = gameSetting.getSelectionModel().getSelectedItem().toString();
      if(game.equals(gameTypes.get(0))){
        LogoDisplay logo = new LogoDisplay();
        logo.start(new Stage());
      }else if(game.equals(gameTypes.get(1))){
        LSystemDisplay lsystem = new LSystemDisplay();
        lsystem.start(new Stage());
      }else if(game.equals(gameTypes.get(2))){
        DarwinDisplay darwin = new DarwinDisplay();
        darwin.start(new Stage());
      }
    });
    root.getChildren().add(gameSetting);
  }

  protected void savedTitle() {
    savedTitle = new Text(getWord("saved_program_title"));
    savedTitle.setLayoutX(SAVED_TITLE_X);
    savedTitle.setLayoutY(SAVED_TITLE_Y);
    root.getChildren().add(savedTitle);
  }

  protected void initializeSavedPrograms() {
    savedPrograms = new ComboBox();
    savedPrograms.setLayoutX(SAVED_DROPDOWN_X);
    savedPrograms.setLayoutY(SAVED_DROPDOWN_Y);
    savedPrograms.setMaxWidth(MAX_DROPDOWN_WIDTH);
    populateFileNames();
    savedPrograms.setOnAction((event) -> {
      if(savedPrograms.getSelectionModel().getSelectedItem() != null){
        getContentFromFilename(savedPrograms.getSelectionModel().getSelectedItem().toString());
      }
    });
    root.getChildren().add(savedPrograms);
  }

  protected void updateSavedDropdown() {
    savedPrograms.getItems().clear();
    populateFileNames();
  }

  protected void getContentFromFilename(String filename) {
    File[] files = getFilesFromPath();
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
          commandLine.clear();
          commandLine.setText(contents.toString());
        } catch (FileNotFoundException e) {
            sendAlert("File not parseable");
        }
      }
    }
  }

  protected void sendAlert(String alertMessage){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setContentText(alertMessage);
    alert.show();
  }

  protected abstract File [] getFilesFromPath();

  protected void populateFileNames() {
    File[] files = new File("data/examples/logo").listFiles();
    for (File file : files) {
      if (file.isFile()) {
        savedPrograms.getItems().add(file.getName());
      }
    }
  }

  protected void historyTitle() {
    history = new Text(getWord("history_text"));
    history.setId("dropdown-label");
    history.setLayoutX(HISTORY_TITLE_X);
    history.setLayoutY(HISTORY_TITLE_Y);
    root.getChildren().add(history);
  }

  protected void initializeHistory() {
    historyPrograms = new ComboBox();
    historyPrograms.setOnAction((event) -> {
      commandLine.setText(historyPrograms.getSelectionModel().getSelectedItem().toString());
    });
    historyPrograms.setLayoutX(HISTORY_DROPDOWN_X);
    historyPrograms.setLayoutY(HISTORY_DROPDOWN_Y);
    historyPrograms.setMaxWidth(MAX_DROPDOWN_WIDTH);
    root.getChildren().add(historyPrograms);
  }

  protected void languagesTitle() {
    languages = new Text(getWord("language_text"));
    languages.setId("dropdown-label");
    languages.setLayoutX(LANGUAGES_TITLE_X);
    languages.setLayoutY(LANGUAGES_TITLE_Y);
    root.getChildren().add(languages);
  }

  private void initializeLanguages() {
    languagesPrograms = new ComboBox(FXCollections.observableList(languageTypes));
    languagesPrograms.setOnAction((event) -> {
      String lang = (String)languagesPrograms.getValue();
      switch (lang) {
        case "English":
          Locale.setDefault(new Locale("en"));
          updateLanguage();
          break;
        case "Spanish":
          Locale.setDefault(new Locale("es"));
          updateLanguage();
          break;
        case "French":
          Locale.setDefault(new Locale("fr"));
          updateLanguage();
          break;
      }
    });
    languagesPrograms.setLayoutX(LANGUAGES_DROPDOWN_X);
    languagesPrograms.setLayoutY(LANGUAGES_DROPDOWN_Y);
    languagesPrograms.setMaxWidth(MAX_DROPDOWN_WIDTH);
    root.getChildren().add(languagesPrograms);
  }

  protected String getWord(String key) {
    ResourceBundle words = ResourceBundle.getBundle("words");
    String value = words.getString(key);
    return value;
  }

  protected void clearText() {
    gameSettingTitle.setText("");
    savedTitle.setText("");
    history.setText("");
    languages.setText("");
    creaturesText.setText("");
    runText = "";
  }

  protected void updateLanguage() {
    clearText();
    gameTitle();
    savedTitle();
    historyTitle();
    languagesTitle();
    runTitle();
  }

  protected void initializeCommandLine() {
    commandLine = new TextArea();
    commandLine.setPrefWidth(COMMAND_WIDTH);
    commandLine.setPrefHeight(COMMAND_HEIGHT);
    commandLine.setLayoutX(COMMAND_X);
    commandLine.setLayoutY(COMMAND_Y);
    root.getChildren().add(commandLine);
  }

  protected void initializeRunButton(String runTitle) {
    Button runCommands = new Button(runTitle);
    runCommands.setPrefWidth(RUN_WIDTH);
    runCommands.setPrefHeight(RUN_HEIGHT);
    runCommands.setLayoutX(RUN_X);
    runCommands.setLayoutY(RUN_Y);
    root.getChildren().add(runCommands);
    runCommands.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        handleInputParsing(commandLine.getText());
        //        myGameProcessor.inputParser(0, 0, 0, commandLine.getText());
        validateCommandStream();
        myGameProcessor.saveHistory(commandLine.getText());
        updateHistoryDropdown();
      }
    });
  }

  protected abstract void handleInputParsing(String text);

  protected String runTitle() {
    return runText = getWord("run_text");
  }

  protected void validateCommandStream() {
    Boolean valid = myGameProcessor.getValidCommand();
    if (!valid) { //TODO: make sure popup works
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setContentText("Invalid command stream!");
      alert.show();
      myGameProcessor.setValidCommand(true);
    }
  }

  protected void initializeSaveButton() {
    Button saveCommands = new Button(getWord("save_text"));
    saveCommands.setPrefWidth(SAVE_WIDTH);
    saveCommands.setPrefHeight(SAVE_HEIGHT);
    saveCommands.setLayoutX(SAVE_X);
    saveCommands.setLayoutY(SAVE_Y);
    root.getChildren().add(saveCommands);
    saveCommands.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        String filename = getUserFileName(getWord("get_user_filename"));
        if(myGameProcessor.saveCommand(commandLine.getText(), filename)){
          updateSavedDropdown();
        }else{
          sendAlert("Error saving program!");
        }
      }
    });
  }

  protected void initializeClearScreen() {
    Button clearScreen = new Button(getWord("clear_text"));
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
        clearSpecificGameDropdowns();
      }
    });
  }

  protected void creaturesTitle(String title) {
    creaturesText = new Text(title);
    creaturesText.setLayoutX(CREATURES_TITLE_X);
    creaturesText.setLayoutY(CREATURES_TITLE_Y);
    root.getChildren().add(creaturesText);
  }

  protected void initializeCreatureDropdown() {
  } //DON'T MAKE ABSTRACT

  //TODO: override this method in each game, make it clear specific dropdowns
  protected abstract void clearSpecificGameDropdowns();

  protected String getUserFileName(String message) {
    TextInputDialog getUserInput = new TextInputDialog();
    getUserInput.setHeaderText(message);
    String fileName = getUserInput.showAndWait().toString();
    if (validateStringFilenameUsingIO(fileName)) {
      return fileName;
    }
    return getUserFileName(message); //TODO: test to make sure this gives users another chance if they submit an invalid filename
  }

  protected boolean validateStringFilenameUsingIO(String filename) {
    File file = new File(filename);
    boolean created = false;
    try {
      created = file.createNewFile();
      return created;
    } catch (IOException e) {
      sendAlert("Invalid filename!");
    } finally {
      if (created) {
        file.delete();
      }
    }
    return false;
  }


  //Create method that passes in queue of commands to Logo
  protected void step() {
    //If an instruction has been sent to myLogo, run it
    Queue<LogoInstruction> instructions = myGameProcessor.getMyInstructions();
    if (!instructions.isEmpty()) {

    }
  }

  protected void updateHistoryDropdown() { //TODO: make sure history is specific to current game model
    historyPrograms.getItems().clear();
    for (String element : myGameProcessor.getHistory()) {
      historyPrograms.getItems().add(element);
    }
  }

  protected void executeInstruction(LogoInstruction currentInstruction, TurtleLinkage turtleLinkage,
      Group root) {
  }

}