package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.CalculationsModel;
import model.MatrixModel;

import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

/**
 * The Graphical User Interface for the Matrix Calculator
 * This class is an observer of the CalculationsModel
 * @author Analia Mok
 */
public class CalculatorGUI extends Application implements Observer{


    /**
     * CalculationModel instance for executing calculations
     */
    private CalculationsModel calcModel;

    /**
     * VBox to hold the controls for the center portion of the layout
     * Portion of the GUI that accepts user input
     */
    private VBox inputDisplay;

    /**
     * HBox holding the Label and Buttons for
     * the calculations
     */
    private HBox calcButtons;

    /**
     * Queue to hold up to 5 previously calculated answers
     */
    private Queue<MatrixModel> answers;

    /**
     * VBox to display the most recent answer and the previously
     * 5 answers
     */
    private VBox ansDisplay;


    /**
     * Used to initialize all components/nodes of the beginning window.
     * @param primaryStage Main window
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        // INITIALIZING FIELDS
        BorderPane mainLayout = new BorderPane();
        this.calcModel = new CalculationsModel();

        // Creating CENTER of mainLayout
        makeInputDisplay(mainLayout);

        // Creating RIGHT of mainLayout
        // TODO

        // Creating BOTTOM of mainLayout
        makeCalcButtons(mainLayout);

        // Scene Creation & Setting
        // Window Displaying
        Scene scene = new Scene(mainLayout, 600, 600);
        primaryStage.setTitle("Matrix Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();

    } // End of start


    /**
     * Update method will display the answer of the most
     * recent calculation on the right section of the
     * main layout
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {

    } // End of update


    // METHODS FOR CREATING BORDERPANE PORTIONS

    /**
     * makeInputDisplay - method for creating the CENTER of the
     * main layout BorderPane
     *
     * @param layout Reference to the main layout
     */
    private void makeInputDisplay(BorderPane layout){

        // Initializing inputDisplay VBox
        this.inputDisplay = new VBox();
        //this.inputDisplay.setAlignment(Pos.TOP_CENTER);
        this.inputDisplay.setPadding(new Insets(0, 10.0, 0, 0));

        // MODE SELECTOR CREATION
        HBox modeSelector = new HBox();
        modeSelector.setAlignment(Pos.CENTER);
        modeSelector.setSpacing(10.0);
        modeSelector.setPadding(new Insets(5.0, 10.0, 5.0, 10.0));

        Label modeTitle = new Label("Select Mode:");
        modeTitle.setPadding(new Insets(5.0)); // TODO: adjust as necessary

        ToggleGroup modes = new ToggleGroup();

        RadioButton singleMode = new RadioButton("Single");
        singleMode.setToggleGroup(modes);
        singleMode.setSelected(true); // Single Mode is the default mode when application is first opened

        RadioButton doubleMode = new RadioButton("Double");
        doubleMode.setToggleGroup(modes);

        modeSelector.getChildren().addAll(modeTitle, singleMode, doubleMode);

        // INPUT CONTROLS
        HBox inputContainer = new HBox();
        inputContainer.setAlignment(Pos.CENTER);

        // TextField for entering a scalar
        TextField scalarInput = new TextField();
        scalarInput.setPromptText("Enter scalar value"); // TODO: Add listener to grab Number
        // By default, input is not visible. Becomes visible when SMULT buttons is pressed
        scalarInput.setVisible(false);

        // Creating Matrix Input Box
        InputBox inputOne = new InputBox();

        Button equalsBtn = new Button("=");
        equalsBtn.setPadding(new Insets(10.0));
        equalsBtn.setMinSize(Button.USE_PREF_SIZE * 3, Button.USE_PREF_SIZE);

        // TODO: Add event handler

        // ADDING ELEMENTS TO CONTAINERS
        inputContainer.getChildren().addAll(scalarInput, inputOne.getInputBox(), equalsBtn);
        this.inputDisplay.getChildren().addAll(modeSelector, inputContainer);

        // Setting layout center
        layout.setCenter(this.inputDisplay);

    } // End of makeInputDisplay


    /**
     * makeCalcButtons - method for creating the BOTTOM of the
     *      main layout. Contains the labels and buttons for selecting
     *      calculations
     *
     * @param layout A reference to the main layout BorderPane
     */
    private void makeCalcButtons(BorderPane layout){

        // Container node for labels & Buttons
        this.calcButtons = new HBox();
        this.calcButtons.setSpacing(5.0);
        this.calcButtons.setPadding(new Insets(0, 10, 10, 10));

        // Single Matrix VBox
        VBox singleBtns = new VBox();
        singleBtns.setSpacing(10.0);
        Label singleMatIn = new Label("Single Matrix Calculations: ");

        // Buttons for single matrix calculation
        Button smultBtn = new Button("SMULT"); // TODO: Add event
        Button transBtn = new Button("TRANS"); // TODO: Add event
        Button invBtn = new Button("INV"); // TODO: Add event

        // TODO: Implement calculation in Calculation Model & Add Event
        //Button powBtn = new Button("POW");

        singleBtns.getChildren().addAll(singleMatIn, smultBtn, transBtn, invBtn); // TODO: Consider creating own custom button

        // Double Matrix VBox
        VBox doubleBtns = new VBox();
        doubleBtns.setSpacing(10.0);
        Label doubleMatIn = new Label("Double Matrix Calculations: ");

        // Buttons for double matrix calculations
        Button mmultBtn = new Button("MMULT");
        Button addBtn = new Button("ADD"); // TODO: Add event
        Button subBtn = new Button("SUB"); // TODO: Add event

        doubleBtns.getChildren().addAll(doubleMatIn,  addBtn, subBtn, mmultBtn);

        // Combining Containers
        this.calcButtons.getChildren().addAll(singleBtns, doubleBtns);

        layout.setBottom(this.calcButtons);

    } // End of makeCalcButtons


} // End of CalculatorGUI class
