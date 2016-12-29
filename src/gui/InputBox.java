package gui;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * Class that is used to represent a matrix (and a scalar quantity).
 * The entire object will be a "box" containing input fields for
 * the matrix, and input fields for the dimensions of the matrix.
 * The object will also contain a "set" button to change the size
 * of the matrix.
 *
 * The inputs need to be able to be dynamically sized, so
 * that's why there is a separate class for the inputs.
 *
 * @author Analia Mok
 */
public class InputBox{

   /**
     * The current dimensions of the input section
     * of the input box
     */
    private int[] dims;


    /**
     * The box is a GridPane to hold the matrix input,
     * dimension inputs, and "set" button
     */
    private GridPane box;


    /**
     * Constructor Method
     * @param dims Dimensions of Matrix Input
     */
    public InputBox(int[] dims){
        // Copying Over Dimensions
        this.dims = new int[2];
        this.dims[0]  = dims[0];
        this.dims[1] = dims[1];

        // Creating box
        this.box = new GridPane();
        // Set internal spacing //TODO: May need to adjust value
        this.box.setHgap(10);
        this.box.setVgap(10);

        // Giving box padding
        this.box.setPadding(new Insets(20));


        // Adding TextFields for Matrix Input
        // TODO: Current adding each individually. May want
        // TODO: to consider using Columns and Rows
        int totalCols = this.dims[1];
        int totalElem = this.dims[0] * totalCols;
        for(int i = 0; i < totalElem; i++){
            int row = i / totalCols;
            int col = i % totalCols;
            TextField input = new TextField();
            input.setAlignment(Pos.CENTER);
            this.box.add(input, col, row);
        }

        // Creating & Adding Dimension Input
        // Input Boxes Start in row = this.dims[0]+2, col=1
        Label by = new Label("x"); // The 'x' in the dimension
        TextField leftDimIn = new TextField(String.valueOf(this.dims[0]));
        leftDimIn.setAlignment(Pos.CENTER);
        TextField rightDimIn = new TextField(String.valueOf(this.dims[1]));
        rightDimIn.setAlignment(Pos.CENTER);

        int currRow = this.dims[0]+2; // Last filled row of matrix input + 2
        this.box.add(leftDimIn, 0, currRow);
        this.box.add(by, 1, currRow);
        // Centering by button
        GridPane.setHalignment(by, HPos.CENTER);
        this.box.add(rightDimIn, 2, currRow);

        // Set button
        Button setBtn = new Button("Set");
        // TODO: Add action
        // Alignment and Size Properties of setBtn
        GridPane.setHalignment(setBtn, HPos.CENTER);
        setBtn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Place right under the by button
        // row = currRow+1, col = 2
        this.box.add(setBtn, 1, currRow+1);

    } // End of constructor


    /**
     * Getter method for the Node of GridPane
     * (the InputBox)
     * @return A GridPane
     */
    public GridPane getInputBox(){
        return this.box;
    }
} // End of InputBox
