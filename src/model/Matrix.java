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
    private int[][] matrix;

    /**
     * The inverse of the current matrix
     */
    private int[][] inverse;

    /**
     * The identity matrix of current matrix
     */
    private int[][] identity;


    /**
     * Empty Constructor
     */
    public Matrix(){

    }



}
