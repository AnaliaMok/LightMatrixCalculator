package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

/**
 * The Graphical User Interface for the Matrix Calculator
 * This class is an observer of the CalculationsModel
 * @author Analia Mok
 */
public class CalculatorGUI extends Application implements Observer{


    /**
     * Used to initialize all components/nodes of the beginning window.
     * @param primaryStage Main window
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane mainLayout = new BorderPane();

        // Creating Single InputBox
        int[] dims = {3 ,3};
        InputBox input1 = new InputBox(dims);

        // Adding InputBox to layout
        mainLayout.setCenter(input1.getInputBox());

        // Scene Creation & Setting
        // Window Displaying
        Scene scene = new Scene(mainLayout, 500, 500);
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
