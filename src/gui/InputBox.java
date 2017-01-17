package gui;

import java.io.IOException;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.MatrixModel;


/**
 * Calculator Class for InputBox.fxml
 * InputBox is a Custom Control whose basis is a VBox
 *      - Accepts matrix input
 *      - Accepts dimensional changes
 *      - Changes total available text inputs for matrix input
 *
 * @author Analia Mok
 */
public class InputBox extends VBox{

   /**
     * The current dimensions of the input section
     * of the input matrixIn
     */
    private int[] dims;

    /**
     * The VBox containing all contents of the InputBox control
     */
    @FXML private VBox box;

    /**
     * The GridPane representing the input interface for
     * matrices
     */
    @FXML private GridPane matrixIn;

    /**
     * the HBox containing the text fields that accept
     * Matrix dimensions changes. By default, each TextField
     * starts with text="3"
     */
    @FXML private HBox dimIn;


    /**
     * Main Constructor Method that creates the default
     * 3x3 matrix input, the dimension input matrixIn, and
     * the set button.
     */
    public InputBox(){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InputBox.fxml"));
        //fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try{
            fxmlLoader.load();
        }catch(IOException ioe){
            System.err.println("ERROR: InputBox Controller | fxmlLoader failed to load");
            throw new RuntimeException(ioe);
        }

    } // End of constructor



    /**
     * resize - method to adjust the size/the amt of TextFields
     *      available for the matrix input
     *
     * @param rows New total row number
     * @param cols New total column number
     */
    @FXML
    private void resize(int rows, int cols){

        if(!(this.dims[0] == rows && this.dims[1] == cols)){
            // Do nothing if dimensions are exactly the same

            // Re-assigning dimensions
            this.dims[0] = rows;
            this.dims[1] = cols;

            // Clearing Observable list of GridPane
            this.matrixIn.getChildren().clear();

            // Adding TextFields for Matrix Input
            int totalElem = rows * cols;
            for (int i = 0; i < totalElem; i++) {
                int row = i / cols;
                int col = i % cols;
                TextField input = new TextField();
                input.setAlignment(Pos.CENTER);
                this.matrixIn.add(input, col, row);
            }

        }

    } // End of resize


    /**
     * Getter method for the InputBox
     * (Technically returns the VBox holding all the components
     * of the InputBox)
     *
     * @return The VBox holding the matrix input, dimension input &
     *      set button
     */
    public VBox getInputBox(){ return this.box; }


    /**
     * Retrieves data found in matrixIn and constructs
     * a MatrixModel instance to be returned and used later
     * @return A MatrixModel object
     */
    public MatrixModel getMatrix(){

        MatrixModel matrix = new MatrixModel(this.dims);

        ObservableList<Node> gridChildren = this.matrixIn.getChildren();

        int totalElem = this.dims[0] * this.dims[1];
        for(int i = 0; i < totalElem; i++){

            int row = i / this.dims[1];
            int col = i % this.dims[1];

            // Grabbing the text from the textfield at index i of the matrix input gridpane
            // Then Converting to an integer
            double currVal = Double.parseDouble(((TextField)gridChildren.get(i)).getText());
            matrix.insert(currVal, row, col);

        }

        return matrix;
    } // End of getMatrix

} // End of InputBox










