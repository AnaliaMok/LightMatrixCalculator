package gui;

import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.MatrixModel;


/**
 * Class that is used to represent a matrix (and a scalar quantity).
 * The entire object will be a "matrixIn" containing input fields for
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
     * of the input matrixIn
     */
    private int[] dims;


    /**
     * Main container for the matrixIn GridPane
     * amd HBox dimInput
     */
    private VBox box;


    /**
     * The matrixIn is a GridPane to hold the matrix input,
     * dimension inputs, and "set" button
     */
    private GridPane matrixIn;


    /**
     * Container layout for the dimensional input
     * fields
     */
    private HBox dimInput;


    /**
     * Main Constructor Method that creates the default
     * 3x3 matrix input, the dimension input matrixIn, and
     * the set button.
     */
    public InputBox(){
        // Copying Over Dimensions
        this.dims = new int[2];
        this.dims[0]  = 3;
        this.dims[1] = 3;

        // Initializing VBox box
        this.box = new VBox();
        this.box.setAlignment(Pos.CENTER);

        // Creating matrixIn
        this.matrixIn = new GridPane();
        // Set internal spacing //TODO: May need to adjust value
        this.matrixIn.setHgap(10);
        this.matrixIn.setVgap(10);

        // Giving matrixIn padding
        this.matrixIn.setPadding(new Insets(20));


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
            this.matrixIn.add(input, col, row);
            /*int index = this.matrixIn.getChildren().indexOf(input);
            input.setText(String.valueOf(index));*/
        }

        // Creating & Adding Dimension Input
        // Input Boxes Start in row = this.dims[0]+2, col=1
        Label by = new Label("x"); // The 'x' or 'by' in the dimension
        // LEFT DIMENSION INPUT
        TextField leftDimIn = new TextField(String.valueOf(this.dims[0]));
        leftDimIn.setAlignment(Pos.CENTER);
        // RIGHT DIMENSION INPUT
        TextField rightDimIn = new TextField(String.valueOf(this.dims[1]));
        rightDimIn.setAlignment(Pos.TOP_CENTER);

        // Initializing HBox dimInput field
        this.dimInput = new HBox();
        this.dimInput.getChildren().addAll(leftDimIn, by, rightDimIn);
        GridPane.setColumnSpan(this.dimInput, 3);
        this.dimInput.setAlignment(Pos.CENTER);
        this.dimInput.setSpacing(10);
        this.dimInput.setPadding(new Insets(10, 0, 20, 0));

        // Adding dimInput to GridPane
        /*int currRow = this.dims[0]+2; // Last filled row of matrix input + 2
        this.matrixIn.add(this.dimInput, 0, currRow);*/

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
        /*GridPane.setHalignment(setBtn, HPos.CENTER);
        setBtn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);*/
        setBtn.setPrefWidth(100); //TODO: Change With CSS for percentages

        // Place right under the by button
        // row = currRow+1, col = 2
        //this.matrixIn.add(setBtn, 1, currRow+1);

        // Adding All Individual Components to the VBox
        this.box.getChildren().addAll(this.matrixIn, this.dimInput, setBtn);

    } // End of constructor


    /**
     * Getter method for the Node of GridPane
     * (the InputBox)
     * @return A GridPane
     */
    public VBox getInputBox(){
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
                // New row size is larger
                remove = false;
            }else{
                // New row size is smaller
                remove = true;
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

                //System.out.println("Current Row: " + currRow);
                // Removing Cells from the rightmost column toward the left
                // of a given row
                for(int c = this.dims[1]-1; c >= 0; c--){
                    //System.out.print("Current Column: " + c + "\t");
                    // Current Index = (currRow + total cols) * currCol
                    int currIdx = (currRow * this.dims[1]) + c;

                    if(remove){
                        // Removing text fields

                        this.matrixIn.getChildren().remove(currIdx);
                        //System.out.println("Remove Index " + currIdx);

                        // Decrement current row index for removing
                        // new bottommost row
                        if(c == 0) currRow--;
                    }else {
                        // Adding text fields
                        TextField newTField = new TextField();
                        newTField.setAlignment(Pos.CENTER);
                        this.matrixIn.add(newTField, c, currRow);
                        //System.out.println("Added Index " + currIdx);

                        // Increment current row index for adding
                        // another row later
                        if(c == 0) currRow++;
                    }
                } // End of inner loop

            } // End of outer loop

        } // End of rowDiff check

        // Else do nothing because sizes are same

        // Now changing current row
        // - regardless of value difference
        this.dims[0] = rows;


        // Adjust Column Size
        if(cols != this.dims[1]){
            colDiff = this.dims[1] - cols;
            if(colDiff < 0){
                // New column size is larger
                remove = false;
            }else{
                // New column size is smaller
                remove = true;
            }

            // Use absolute value of column difference
            colDiff = Math.abs(colDiff);

            // Rightmost column
            int currCol = this.dims[1]-1;

            // Adding or removing from the bottommost & rightmost cell upward
            for(int i = 0; i < colDiff; i++){
                // If adding cells, increment current column index
                if(!remove) currCol++;

                for(int r = this.dims[0]-1; r >= 0; r--){
                    // Current Index
                    int currIdx = (r * this.dims[1]) + currCol;

                    if(remove){
                        // Remove text fields
                        this.matrixIn.getChildren().remove(currIdx);

                        // Decrement current column index to remove
                        // next rightmost column
                        if(r == 0) currCol--;

                    }else{
                        // Add text fields
                        TextField newTField = new TextField();
                        newTField.setAlignment(Pos.CENTER);
                        this.matrixIn.add(newTField, currCol, r);

                        // Increment current column index for adding
                        // another column later
                        if(r == 0) currCol++;

                    }

                } // End of inner loop

            } // End of outer loop


        } // End of column difference condition

        // Else do nothing because sizes are same

        // Now changing current column
        // - regardless of value difference
        this.dims[1] = cols;

    } // End of resize


    /**
     * Retrieves data found in matrixIn and constructs
     * a MatrixModel instance to be returned and used later
     * @return A MatrixModel object
     */
    public MatrixModel getMatrix(){
        // TODO
        return null;
    } // End of getMatrix

} // End of InputBox










