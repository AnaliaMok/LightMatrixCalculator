package gui;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

/**
 * The Graphical User Interface for the Matrix Calculator
 * This class is an observer of the CalculationsModel
 * @author Analia Mok
 */
public class CalculatorGUI extends Application implements Observer{
    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
