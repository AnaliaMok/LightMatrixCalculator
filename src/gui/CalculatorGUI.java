package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jdk.internal.util.xml.impl.Input;
import model.CalculationsModel;

import java.util.Observable;
import java.util.Observer;

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
     * Used to initialize all components/nodes of the beginning window.
     * @param primaryStage Main window
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        // Main Layout
        BorderPane mainLayout = new BorderPane();

        // Initializing
        this.calcModel = new CalculationsModel();

        VBox center = new VBox();
        center.setAlignment(Pos.TOP_CENTER);

        // VBox holding the label and radio buttons for
        // selecting between single matrix input and
        // two matrix input
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

        // Creating Matrix Input Box
        InputBox inputOne = new InputBox();

        center.getChildren().addAll(modeSelector, inputOne.getInputBox());

        mainLayout.setCenter(center);

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


} // End of CalculatorGUI class
