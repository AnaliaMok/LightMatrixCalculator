package model;

/**
 * Representation of an m x n matrix
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
     * The underlying representation of a matrix is a 2D array
     */
    private double[][] matrix;

    /**
     * The inverse of the current matrix
     */
    private double[][] inverse;

    /**
     * The identity matrix of current matrix
     */
    private int[][] identity;

    /**
     * The transpose of the current matrix
     */
    private double[][]transpose;


    /**
     * Empty Constructor
     */
    public MatrixModel(){}

    /**
     * Main Constructor of Matrix
     * Will only contain zeroes at this point
     * @param dims A 1D integer array containing the dimensions
     *             of this matrix
     */
    public MatrixModel(int dims[]){
        this.matrix = new double[dims[0]][dims[1]];
        this.dims = new int[2];
        this.dims[0] = dims[0];
        this.dims[1] = dims[1];
    } // End of main constructor


    /**
     * A copy constructor that instantiates a MatrixModel object
     * based on the given MatrixModel m
     * @param m A MatrixModel to deep copy
     */
    public MatrixModel(MatrixModel m){
        // Initializing and defining dimensions array
        this.dims = new int[2];
        this.dims[0] = m.getDims()[0];
        this.dims[1]= m.getDims()[1];

        // Initializing & Defining Matrix
        this.matrix = new double[this.dims[0]][this.dims[1]];
        for(int r = 0; r < this.dims[0]; r++){
            for(int c = 0; c < this.dims[1]; c++){
                this.matrix[r][c] = m.valueAt(r, c);
            }
        }

    } // End of copy constructor


    /**
     * Used to insert values into the matrix (or replace
     * if necessary)
     *
     * @param value A Double to insert into the matrix
     * @param row The row to place the value in
     * @param col the column to place the value in
     */
    public void insert(double value, int row, int col){
        this.matrix[row][col] = value;
    } // End of insert()


    /**
     * Pre-condition: Matrix has been instantiated
     * @param row An integer representing the row to find a value
     * @param col An integer representing the column to find a value
     * @return A double representing the value at (row,col)
     */
    public double valueAt(int row, int col){
        return this.matrix[row][col];
    } // End of valueAt

    /**
     * Getter method for the MatrixModel's dimensions
     * @return this.dims: An array of two integers
     */
    public int[] getDims(){
        return this.dims;
    } // End of getDims


    /**
     * Returns the identity of the current matrix
     * Will create the identity matrix if it has not
     * already
     * @return identity Matrix
     */
    public int[][] getIdentity(){
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
     *      entry will be a one
     * Will initialize the identity matrix
     * Will set all diagonal entries to one
     */
    private void makeIdentity(){

        // Initializing identity matrix
        this.identity = new int[this.dims[0]][this.dims[1]];

        // Iterate Up to the Either the number of rows or columns
        for(int i = 0; i < this.dims[0]; i++){
            this.identity[i][i] = 1;
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
    public double[][] getInverse(){
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
        this.inverse = new double[this.dims[0]][this.dims[1]];
        //TODO: Need to implement methods in CalculationsModel

    } // End of inverse


    /**
     * Returns the transpose of the current matrix
     * Will initialize the transpose field - if it hasn't already
     * @return the transpose field
     */
    public double[][] getTranspose(){
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
        this.transpose = new double[this.dims[1]][this.dims[0]];

        //Occupying transpose
        //Iterating as if the matrix was a one dimensional array
        for(int r = 0; r < (this.dims[0]*this.dims[1]); r++){
            for(int c = 0; c < this.dims[1]; c++) {
                this.transpose[c][r] = this.matrix[r][c];
            }
        }

    } // End of transpose


    @Override
    public String toString(){
        String result = "";

        // Iterating through the matrix as if it were a one dimensional array
        for(int r = 0; r < this.dims[0]; r++){
            for(int c = 0; c < this.dims[1]; c++) {

                // Adding the left vertical bar of the line
                if (c == 0) {
                    result += "|\t";
                    // Adding the current value in matrix to result String
                    result += Double.toString(this.matrix[r][c]) + "\t";
                    continue;
                }

                // Adding the right vertical bar and ending new line character
                if (c == this.dims[1] - 1) {
                    result += Double.toString(this.matrix[r][c]) + "\t" + "|\n";
                    continue;
                }

                // If somewhere in the middle of a row, just add the
                // value to the result String + a tab character
                result += Double.toString(this.matrix[r][c]) + "\t";
            }
        }

        return result;
    }


} // End of Matrix Class
