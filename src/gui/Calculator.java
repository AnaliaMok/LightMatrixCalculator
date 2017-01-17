package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

/**
 * Calculator.java - File Contains All of the Application
 *  logic for the GUI. All design is contained in the
 *  calculator fxml file
 *
 * @author Analia Mok
 */
public class Calculator{

    @FXML
    private InputBox embeddedInputBox;

    public void initialize(){
        System.out.println(embeddedInputBox);
    }


} // End of Calculator
