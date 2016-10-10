package model;

/**
 * An exception for when an attempt is made
 * to find the inverse or the identity of a
 * non-square matrix
 *      (not a square matrix that happens
 *      to not have an inverse)
 *
 * @author Analia Mok
 */
public class IllegalMatrixCalculationException extends Exception {

    /**
     * Exception constructor just uses the super constructor that
     * takes in a message
     * @param calc The calculation attempted (either inverse or identity)
     */
    public IllegalMatrixCalculationException(String calc){
        super("ERROR: User attempted to find the " + calc + " of a non-square matrix");
    }


}
