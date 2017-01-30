package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import model.CalculationsModel;
import model.MatrixModel;

import java.io.IOException;
import java.util.LinkedList;
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
     * Queue to hold up to 5 previously calculated answers
     */
    private Queue<MatrixModel> answers;



    /**
     * Used to initialize all components/nodes of the beginning window.
     * @param primaryStage Main window
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        // Loading Calculator First
        Parent root = FXMLLoader.load(getClass().getResource("Calculator.fxml"));

        // Initializing Fields
        this.calcModel = new CalculationsModel();
        this.answers = new LinkedList<MatrixModel>();



        Scene scene = new Scene(root, 600, 600);
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
