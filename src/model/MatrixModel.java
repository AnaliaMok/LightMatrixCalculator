package model;

import java.util.Arrays;

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
     * Pre-condition: The identity matrix has not already been initialized
     * Will initialize the identity matrix
     */
    private void makeIdentity(){

        // Initializing identity matrix
        this.identity = new int[this.dims[0]][this.dims[1]];

        // Iterate Based on the # of rows
        for(int i = 0; i < this.dims[0]; i++){

            // If there are more rows than columns, stop inserting
            if(i > this.dims[1]){
                break;
            }

            //Else, insert a one on the diagonal
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
     */
    private void findTranspose(){
        // Initializing transpose
        // NOTE: The dimensions of the rows & columns are reversed
        this.transpose = new double[this.dims[1]][this.dims[0]];

        //Occupying transpose
        //Iterating as if the matrix was a one dimensional array
        for(int i = 0; i < (this.dims[0]*this.dims[1]); i++){
            // Determine the current row and column in this.matrix
            // with division & modulus
            int row = (int)Math.floor(i/dims[0]);
            int col = i%dims[1];

            this.transpose[col][row] = this.matrix[row][col];
        }

    } // End of transpose


    @Override
    public String toString(){
        String result = "";

        // Iterating through the matrix as if it were a one dimensional array
        for(int i = 0; i < (this.dims[0]*this.dims[1]); i++){
            // Determine the current row and column in this.matrix
            // with division & modulus
            int row = (int)Math.floor(i/dims[0]);
            int col = i%dims[1];

            // Adding the left vertical of the line
            if(col == 0){
                result += "|\t";
                // Adding the current value in matrix to result String
                result += Double.toString(this.matrix[row][col]) + "\t";
                continue;
            }

            // Adding the right vertical bar and ending new line character
            if(col == this.dims[1]-1){
                result += Double.toString(this.matrix[row][col]) + "\t" + "|\n";
                continue;
            }

            // If somewhere in the middle of a row, just add the
            // value to the result String + a tab character
            result += Double.toString(this.matrix[row][col]) + "\t";

        }

        return result;
    }


} // End of Matrix Class
