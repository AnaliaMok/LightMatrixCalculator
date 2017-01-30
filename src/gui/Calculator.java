package gui;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * Calculator.java - File Contains All of the Application
 *  logic for the GUI. All design is contained in the
 *  calculator fxml file
 *
 * @author Analia Mok
 */
public class Calculator{

    /**
     * The HBox containing the toggle group mode selection
     * Contains radio buttons to switch between modes
     * One mode for displaying a single matrix
     * Another mode for displaying 2 matrices
     */
    @FXML private HBox modeSelector; // TODO: Add listener for selectionModel

    /**
     * HBox to add or remove an inputBox - depending
     * on the current selected mode
     */
    private HBox inputs;

    /**
     * reference to the input box instance
     */
    @FXML private VBox inputBox;

    /**
     * A ListView that will hold up to 5 available past matrices
     */
    @FXML private ListView<String> display; // TODO: Add Listener or bind to Queue in main

    /**
     * VBox containing a label and 3 single matrix calculation buttons
     */
    @FXML private VBox singleMatBtns; // TODO: Add field in main to keep track of what kind of calculation is selected

    /**
     * VBox containing a label and 3 double matrix calculation buttons
     */
    @FXML private VBox doubleMatBtns;


    /**
     * Constructor Class used to add listeners where appropriate
     */
    public Calculator(){

    }


} // End of Calculator
