package model;

/**
 * Representation of an m x n matrix
 * Includes its dimensions, its inverse - when necessary
 * and its identity matrix - when necessary
 *
 * @author Analia Mok
 */
public class Matrix {

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
    public Matrix(){}

    /**
     * Main Constructor of Matrix
     * Will only contain zeroes at this point
     * @param dims A 1D integer array containing the dimensions
     *             of this matrix
     */
    public Matrix(int dims[]){
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
    public Matrix getIdentity(){
        //TODO
        return null;
    } // End of getIdentity


    /**
     * Pre-condition: The identity matrix has not already been initialized
     * Will initialize the identity matrix
     */
    private void makeIdentity(){
        //TODO
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
    public Matrix getInverse(){
        //TODO
        return null;
    } // End of getInverse


    /**
     * Pre-condition: the inverse matrix has not been initialized
     * Creates the inverse matrix through Row Reduction
     */
    private void findInverse(){
        //TODO
    } // End of inverse


    /**
     * Returns the transpose of the current matrix
     * Will initialize the transpose field - if it hasn't already
     * @return the transpose field
     */
    public Matrix getTranspose(){
        //TODO
        return null;
    } // End of getTranspose


    /**
     * Pre-condition: transpose field has not been initialized yet
     * Finds the transpose of the current matrix
     */
    private void findTranspose(){
        //TODO
    } // End of transpose


    @Override
    public String toString(){
        return "";
    }


} // End of Matrix Class
