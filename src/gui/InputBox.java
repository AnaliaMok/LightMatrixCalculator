package gui;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
     * Container layout for the dimensional input
     * fields
     */
    private HBox dimInput;


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
        Label by = new Label("x"); // The 'x' or 'by' in the dimension
        // LEFT DIMENSION INPUT
        TextField leftDimIn = new TextField(String.valueOf(this.dims[0]));
        leftDimIn.setAlignment(Pos.CENTER);
        // RIGHT DIMENSION INPUT
        TextField rightDimIn = new TextField(String.valueOf(this.dims[1]));
        rightDimIn.setAlignment(Pos.CENTER);

        // Initializing HBox dimInput field
        this.dimInput = new HBox();
        this.dimInput.getChildren().addAll(leftDimIn, by, rightDimIn);
        GridPane.setColumnSpan(this.dimInput, 3);
        this.dimInput.setAlignment(Pos.CENTER);
        this.dimInput.setSpacing(10);

        // Adding dimInput to GridPane
        int currRow = this.dims[0]+2; // Last filled row of matrix input + 2
        this.box.add(this.dimInput, 0, currRow);

        // Set button
        Button setBtn = new Button("Set");
        setBtn.setOnAction(e -> {
            // Grabbing Text From Left and Right
            String leftDim = leftDimIn.getText();
            String rightDim = rightDimIn.getText();

            try{
                // Attempt to convert String
                int rows = Integer.parseInt(leftDim);
                int cols = Integer.parseInt(rightDim);

                // Resize Matrix Input based on rows and cols
                resize(rows, cols);

            }catch (NumberFormatException nfe){
                // If non-numeric values inputted
                // TODO: Create an Alert Box
            }

        });

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


    /**
     * resize - method to adjust the size/the amt of TextFields
     *      available for the matrix input
     *
     * @param rows New total row number
     * @param cols New total column number
     */
    private void resize(int rows, int cols){

        // Row and column difference between the current
        // dimensions and the new dimensions
        int rowDiff = 0, colDiff = 0;
        // Boolean value to determine whether or not rows and/or
        // columns need to be removed
        boolean remove = false;

        // Adjust rows
        if(rows != this.dims[0]){
            rowDiff = this.dims[0] - rows;
            if(rowDiff < 0){
                remove = true;
            }else if(rowDiff > 0){
                remove = false;
            }

            int currRow = this.dims[0]-1; // Last row in matrix input

            // Using absolute value of difference for iteration
            rowDiff = Math.abs(rowDiff);
            for(int i = 0; i < rowDiff; i++){
                // Iterating for number of rows to either
                // add or delete
                if(!remove){
                    // If not removing nodes, then need to start adding
                    // at row currRow+1 which is == this.dims[0]
                    currRow++;
                }

                for(int c = 0; c < this.dims[1]; c++){
                    if(!remove){
                        // Adding text fields
                        TextField newTField = new TextField();
                        newTField.setAlignment(Pos.CENTER);
                        this.box.add(newTField, c, currRow);
                    }else {
                        // Removing text fields
                        // Current Index = (currRow * total cols) + currCol
                        int currIdx = (currRow * this.dims[0]) + c;
                        this.box.getChildren().remove(currIdx);
                    }
                } // End of inner loop

            } // End of outer loop

        } // End of rowDiff check

        // Else do nothing because sizes are same

        // Adjust Column Size
        if(cols > this.dims[1]){
            // If new column size is greater than
            // current column size
            // TODO
        }else if(cols < this.dims[1]){
            // TODO
        }
        // Else do nothing because sizes are same

    } // End of resize

} // End of InputBox










