package gui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
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
    @FXML private TilePane matrixIn;

    /**
     * The TextField containing the left dimensional input
     */
    @FXML private TextField leftDimIn;

    /**
     * The TextField containing the right dimensional input
     */
    @FXML private TextField rightDimIn;


    /**
     * Main Constructor Method that creates the default
     * 3x3 matrix input, the dimension input matrixIn, and
     * the set button.
     */
    public InputBox(){

        // Initializing Dimensions
        this.dims = new int[2];
        this.dims[0] = 3; this.dims[1] = 3;


        // Adding Listeners to Dimensional Input
        // Whenever a value is changed, the Matrix Input will be resized
        /*this.leftDimIn.textProperty().addListener((o, oldValue, newValue) -> {
            // Row value change
            try{
                this.dims[0] = Integer.parseInt(newValue);
                this.matrixIn.setPrefRows(this.dims[0]);
                // TODO: call resize

            }catch(NumberFormatException nfe){
                // TODO: Make Alert Box
                System.err.println("USAGE: Please Enter Numeric Values");
            }

        });

        this.rightDimIn.textProperty().addListener((o, oldValue, newValue) -> {
            // Column value change

            try{
                this.dims[1] = Integer.parseInt(newValue);
                this.matrixIn.setPrefColumns(this.dims[1]);
                // TODO: call resize

            }catch(NumberFormatException nfe){
                // TODO: Make Alert Box
                System.err.println("USAGE: Please Enter Numeric Values");
            }

        });*/


    } // End of constructor



    /**
     * resize - method to adjust the size/the amt of TextFields
     *      available for the matrix input
     */
    protected void resize(){
        ObservableList<Node> children = matrixIn.getChildren();
        children.clear();


    } // End of resize


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










