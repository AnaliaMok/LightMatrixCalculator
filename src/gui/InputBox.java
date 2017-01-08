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

                // Reassign Index of children
                /*int totalEl = this.dims[0]*this.dims[1];
                for(int i = 0; i < totalEl; i++){
                    int currRow = i / this.dims[1];
                    int currCol = i % this.dims[1];
                    TextField curr = (TextField)this.matrixIn.getChildren().get(i);
                    curr.setText(String.valueOf(this.matrixIn.getChildren().indexOf(curr)));
                }*/

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
    private void resize(int rows, int cols){ // TODO: Consider Clearing entire grid & re-adding elements

        // Row and column difference between the current
        // dimensions and the new dimensions
        int rowDiff = this.dims[0] - rows;
        int colDiff = this.dims[1] - cols;

        // Initializing Current row and column indexes
        int currRow = this.dims[0]-1, currCol = this.dims[1]-1;

        // Boolean value to determine whether or not rows and/or
        // columns need to be removed
        // Initial value depends on row difference since rows
        // are adjusted first
        boolean remove = (rowDiff > 0);

        System.out.printf("Current Dims: [%d, %d]\n", this.dims[0], this.dims[1]);
        System.out.println("Size of matrixIn list: " + this.matrixIn.getChildren().size());

        // Adjusting rows first
        if(rowDiff < 0){
            // Need to take the absolute value before looping
            rowDiff = Math.abs(rowDiff);
            // need to increment currRow to start adding nodes to the GridPane
            currRow++;
        }

        if(rowDiff != 0) { // Skip if no change was designated

            for (int i = 0; i < rowDiff; i++) {

                if(remove){
                    // Start index = current row * totalCols + 0 <- Column 0
                    // End index = current row * totalCols + currCol <- Last Column
                    int startIdx = (currRow * this.dims[1]);
                    int endIdx = (currRow * this.dims[1]) + currCol;

                    // Removes all nodes in last row
                    this.matrixIn.getChildren().remove(startIdx, endIdx + 1);
                    currRow--; // Now one less available row
                }else{
                    // Adding nodes
                    // NOTE: Can't simply add to matrixIn's Observable list because
                    // of the dimension input and set button
                    for (int c = 0; c < this.dims[1]; c++) {
                        //int currIdx = (currRow * this.dims[1]) + c;
                        // Creating new TextField with center alignment
                        TextField input = new TextField();
                        input.setAlignment(Pos.CENTER);
                        //this.matrixIn.add(input, c, currRow);
                        this.matrixIn.addRow(currRow, input);
                    }

                    currRow++; // Move next row index for any additional rows
                }
            }
        } // End of rowDiff conditional

        // Updating row dimension
        this.dims[0] = rows;

        // Adjusting Columns
        remove = (colDiff > 0);

        if(colDiff < 0){
            // Need to take absolute value before looping
            colDiff = Math.abs(colDiff);
            // Need to increment current column index
            // before adding cells
            currCol++;
        }

        if(colDiff != 0){ // Skip if no change was designated

            for(int i = 0; i < colDiff; i++){

                for(int r = this.dims[0]-1; r >= 0; r--){
                    // Incrementing backwards for removing cells

                    if(remove){
                        // Remove cells
                        // Current Index = (current row * totalCols) + current column
                        int currIdx = (r * this.dims[1]) + currCol;
                        System.out.println("Index: " + currIdx);

                        Node n = this.matrixIn.getChildren().get(currIdx);
                        this.matrixIn.getChildren().remove(n);

                        // Decrement after removing last cell in currCol
                        if(r == 0) currCol--;

                    }else{
                        // Add cells
                        // Creating new TextField with center alignment
                        TextField input = new TextField();
                        input.setAlignment(Pos.CENTER);
                        this.matrixIn.addColumn(currCol, input);

                        // Increment after adding last cell in currCol
                        if(r == 0) currCol++;

                    }

                }
            }
        } // End of colDiff conditional

        // Adjusting column dimension
        this.dims[1] = cols;

        System.out.printf("New Dims: [%d, %d]\n", this.dims[0], this.dims[1]);
        System.out.println("Size of matrixIn list: " + this.matrixIn.getChildren().size());

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










