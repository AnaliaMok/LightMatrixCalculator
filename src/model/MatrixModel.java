package model;
import java.util.ArrayList;

/**
 * Representation of a m x n matrix - That can only contain
 * numbers - not Strings. Chars can be passed in, but will
 * be converted to their corresponding ASCII decimal values
 * Includes its dimensions, its inverse - when necessary
 * and its identity matrix - when necessary
 *
 * @author Analia Mok
 */
public class MatrixModel {

    /**
     * A one dimensional array that holds the dimensions of
     * a given matrix
     */
    private int[] dims;

    /**
     * The underlying representation of a single matrix
     * is an ArrayList of Numbers
     * Using the Number Class to avoid a Generic Class Declaration
     * and restrict to only Matrices of type Number - and not String
     */
    private ArrayList<Number> matrix;

    /**
     * The inverse of the current matrix
     */
    private ArrayList<Number> inverse;

    /**
     * The identity matrix of current matrix
     */
    private ArrayList<Number> identity;

    /**
     * The transpose of the current matrix
     */
    private ArrayList<Number> transpose;

    /**
     * Main Constructor of Matrix
     * Will only contain zeroes at this point
     * @param dims A 1D integer array containing the dimensions
     *             of this matrix
     */
    public MatrixModel(int dims[]){
        // Initial capacity set to total number of values possible
        // with the given dimensions
        this.matrix = new ArrayList<Number>(dims[0]*dims[1]);

        // Copying Over Dimensions
        this.dims = new int[2];
        this.dims[0] = dims[0];
        this.dims[1] = dims[1];
    } // End of main constructor


    /**
     * A Copy Constructor that instantiates a MatrixModel object
     * based on the given MatrixModel m
     * @param m A MatrixModel to deep copy
     */
    public MatrixModel(MatrixModel m){
        // Initializing and defining dimensions array
        this.dims = new int[2];
        this.dims[0] = m.getDims()[0];
        this.dims[1]= m.getDims()[1];

        // Initializing Current Matrix
        this.matrix = new ArrayList<Number>(this.dims[0]*this.dims[1]);
        this.matrix.addAll(m.getMatrix());

    } // End of copy constructor


    /**
     * Pre-condition: Row and column are zero-based
     * Used to insert values into the matrix (or replace
     * if necessary)
     *
     * @param value A Double to insert into the matrix
     * @param row The row to place the value in
     * @param col the column to place the value in
     */
    public void insert(double value, int row, int col){
        // Current Index = currRow * totalColumns + currColumns
        int currIdx = (row * this.dims[1]) + col;
        this.matrix[currIdx] = value;
    } // End of insert()


    /**
     * Pre-condition: Matrix has been instantiated
     *      Values are 0-based
     * @param row An integer representing the row to find a value
     * @param col An integer representing the column to find a value
     * @return A double representing the value at (row,col)
     */
    public double valueAt(int row, int col){
        return this.matrix[(row*this.dims[1])+col];
    } // End of valueAt


    /**
     * colAt retrieves and returns the values at the specified column
     * @param col An Integer representing what column to retrieve
     * @return A double array
     */
    public double[] colAt(int col){
        // NOTE: The number of elements in a column are equal to the number
        // of rows in a matrix
        double[] column = new double[this.dims[0]];

        // Retrieving values at column col
        for(int r = 0; r < this.dims[0]; r++){
            int currIdx = (r * this.dims[1] + col);
            column[r] = this.matrix[currIdx];
        }

        return column;
    } // End of colAt


    /**
     * rowAt retrieves and returns the values at the specified row
     * @param row An Integer representing what row to retrieve
     * @return A double array
     */
    public double[] rowAt(int row){
        // NOTE: The number of elements in a row are equals to the number of
        // columns in a matrix
        double[] result = new double[this.dims[1]];

        // Retrieving values at row row
        for(int c = 0; c < this.dims[1]; c++){
            int currIdx = (row * this.dims[1] + c);
            result[c] = this.matrix[currIdx];
        }

        return result;
    } // End of rowAt


    /**
     * Getter method for the MatrixModel's dimensions
     * @return this.dims: An array of two integers
     */
    public int[] getDims(){
        return this.dims;
    } // End of getDims


    /**
     * Getter method to retrieve the matrix's internals
     * Only to be accessed by the Model package classes
     * @return An ArrayList of Numbers
     */
    protected ArrayList<Number> getMatrix(){
        return this.matrix;
    } // End of getMatrix


    /**
     * Returns the identity of the current matrix
     * Will create the identity matrix if it has not
     * already
     * @return identity Matrix
     */
    public int[] getIdentity(){
        if(this.identity == null){
            makeIdentity();
        }
        return this.identity;
    } // End of getIdentity


    /**
     * Pre-conditions:
     *      The identity matrix has not already been initialized
     *      The current matrix is a square matrix
     * Post-condition:
     *      The identity matrix will be initialized and each diagonal
     *      entry will be a one. All remaining entries will be a one
     * Will initialize the identity matrix
     * Will set all diagonal entries to one
     */
    private void makeIdentity(){

        // Initializing identity matrix
        this.identity = new int[this.dims[0]*this.dims[1]];

        // Iterate Based on the Number of Rows
        for(int row = 0; row < this.dims[0]; row++){
            // Current Index = currRow * totalColumns + currCol
            // (which is equal to the currRow)
            int currIdx = (row * this.dims[1]) + row;
            this.identity[currIdx] = 1;
        }
    } // End of makeIdentity


    /**
     * Pre-condition: The inverse matrix has not been initialized
     *      The current matrix is a square matrix
     *
     * Returns the inverse of the current matrix (if valid)
     * Will create the inverse matrix - if not done so already
     *
     * @return inverse Matrix
     */
    public double[] getInverse(){
        if(this.inverse == null){
            findInverse();
        }
        return this.inverse;
    } // End of getInverse


    /**
     * Pre-condition: the inverse matrix has not been initialized
     * Creates the inverse matrix through Row Reduction
     */
    private void findInverse(){
        // Initializing inverse matrix
        this.inverse = new double[this.dims[0]*this.dims[1]];
        //TODO: Need to implement methods in CalculationsModel

    } // End of inverse


    /**
     * Returns the transpose of the current matrix
     * Will initialize the transpose field - if it hasn't already
     * @return the transpose field
     */
    public double[] getTranspose(){
        if(this.transpose == null){
            findTranspose();
        }
        return this.transpose;
    } // End of getTranspose


    /**
     * Pre-condition: transpose field has not been initialized yet
     * Finds the transpose of the current matrix
     * NOTE: The transpose of a matrix M contains columns that are created
     * from M's rows
     */
    private void findTranspose(){
        // Initializing transpose
        // NOTE: The dimensions of the rows & columns are reversed
        this.transpose = new double[this.dims[1]*this.dims[0]];

        for(int i = 0; i < this.dims[1]*this.dims[0]; i++){
            int row = i / this.dims[1];
            int col = i % this.dims[1];
            int transIdx = (col * this.dims[1]) + row;
            transpose[transIdx] = this.matrix[i];
        }

    } // End of transpose


    @Override
    public String toString(){
        String result = "";

        // Iterating through the matrix as if it were a one dimensional array
        for(int i = 0; i < this.dims[0]+this.dims[1]; i++){
            // Calculating Current Column
            int col = i % this.dims[1];
            if(col == this.dims[1]-1){
                result += Double.toString(this.matrix[i]) + "\n";
                continue;
            }

            result += Double.toString(this.matrix[i]) + "\t";

        }

        return result;
    }


} // End of Matrix Class
