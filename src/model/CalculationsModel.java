package model;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Optional;
import java.util.Queue;

/**
 * Implementation of Most Possible Matrix Calculations
 *
 * @author Analia Mok
 */
public class CalculationsModel extends Observable {

    /**
     * The answer to the previous/most recent calculation
     * Can be used in the most immediate calculation
     */
    private Optional<MatrixModel> answer;

    /**
     * A Queue of Matrices to Calculate
     */
    private Queue<MatrixModel> matrices;

    /**
     * Main constructor of CalculationsModel
     * Only need to initializing matrices Queue as a
     * LinkedList
     */
    public CalculationsModel(){
        this.matrices = new LinkedList<MatrixModel>();
    }


    /**
     * hasSameDims checks to see if two given 2-elements arrays
     * are EQUAL
     * @param dims1 Integer array representing dimensions of a matrix
     * @param dims2 Integer array representing dimensions of a matrix
     * @return True if dimensions are equal; false otherwise
     */
    private boolean hasSameDims(int[] dims1, int[] dims2){
        return ((dims1[0] == dims2[0]) && (dims1[1] == dims2[1]));
    } // End of hasSameDims


    /**
     * Pre-condition: Keyword kw is a calculation keyword
     * matrixCheck is used to see if the current set of matrices in
     * the Queue is valid for the given calculation
     * @param kw Keyword representing the current calculation
     * @return true if calculation can continue; false otherwise
     */
    public boolean matrixCheck(Keyword kw){
        switch(kw){
            case ADD:
            case SUB:
                //TODO: Look through the queue while also placing the
                //TODO: matrices back into the queue
                break;
            case MULT:
                //TODO: Check inner dimensions
                break;
            case INVERSE:
                //TODO: Check to see if front is a square matrix
                break;
            default:
                return true;
        }
        return true; // Will never be reached, but here to make Java happu
    } // End of matrixCheck


    /**
     * Post-condition: matrices Queue is re-initialized; answer is Empty
     * clear is used to clear all values in the current matrices Queue
     * clear also empties the current;y stored answer
     */
    public void clear(){
        // Garbage collection should handle the previous
        // memory location
        this.matrices = new LinkedList<MatrixModel>();

        // Reset Optional answer to an empty optional
        // Only do if answer is holding something
        if(answer.isPresent()){
           answer = Optional.empty();
        }

    } // End of clear


    /**
     * addAns will enqueue the current answer to the matrices Queue
     * ONLY IF PRESENT
     * @return true is a matrix was enqueued successfully; false otherwise
     */
    public boolean addAns(){
        if(answer.isPresent()){
            addMatrix(answer.get());
            return true;
        }
        return false;
    } // End of addAns


    /**
     * addMatrix takes a MatrixModel object and enqueues it to
     * the matrices Queue
     * @param m A MatrixModel object
     */
    public void addMatrix(MatrixModel m){
        this.matrices.add(m);
    } // End of addMatrix


    /* CALCULATION METHODS */

    /**
     * Pre-condition: Matrices have the same dimensions
     * Post-condition: answer now holds a MatrixModel object
     *      representing a sum
     * Adds two matrices dequeued from the matrices Queue
     *
     */
    public void add(){
        //TODO
        announceChange();
    } // End of add


    /**
     * Post-condition: answer now holds a MatrixModel object representing
     *      a product
     * Multiplies a given matrix by a scalar
     * @param scalar A Real Number to multiply the elements of a
     *               matrix
     */
    public void smult(double scalar){
        //TODO
        announceChange();
    } // End of smult


    /**
     * dotProd takes the dotProduct of two given vectors
     * in the same dimensional space; that is, both vectors
     * have the same amount of the elements
     * @param v1 A vector/double array
     * @param v2 A vector/double array
     * @return A double representing the dot product of v1 & v2
     */
    private double dotProd(double[] v1, double[] v2){
        return 0.0;
    } // End of dotProd


    /**
     * Pre-condition: The "inner" dimensions of the two matrices
     *      are equal
     * Post-condition: answer now holds a MatrixModel object representing
     *      a product
     * Multiplies two matrices dequeued from the matrices Queue
     */
    public void multiply(){
        //TODO
        announceChange();
    } // End of multiply


    /**
     * Pre-condition: Matrices have the same dimensions
     * Post-condition: answer now holds a MatrixModel object
     *      representing a difference
     * Subtracts two matrices dequeued from the matrices Queue
     */
    public void subtract(){
        //TODO
        announceChange();
    } // End of subtract


    /**
     * Pre-condition: Matrix is a square matrix
     * Post-condition: answer now holds a MatrixModel object representing
     *      an inverse of the previous matrix
     * Find the inverse of the matrix in the front of the
     * matrices Queue
     */
    public void inverse(){
        //TODO
        announceChange();
    } // End of inverse


    /**
     * Post-condition: answer now holds a MatrixModel object representing
     *      a transpose
     * transpose will call the transpose getter method of the matrix at the
     * front of the queue and assign the retrieved matrix to answer
     */
    public void transpose(){
        //TODO
        announceChange();
    } // End of transpose


    /**
     * Pre-condition: ref field has not been initialized yet
     * Post-condition: answer now holds a MatrixModel object in REF
     * Row reduces the current matrix to Row Echelon Form(REF)
     */
    public void toREF(MatrixModel m){
        //TODO
    } // End of toREF


    /**
     * Pre-condition: rref field has not been initialized yet
     * Post-condition: answer now holds a MatrixModel object in RREF
     * Row reduces the current ref matrix to Row Reduced Echelon Form
     * Will call toREF if ref field has not been initialized
     * @return rref matrix
     */
    public void toRREF(MatrixModel m){
        //TODO
    } // End of toRREF


    /**
     * Announces changes to model's observers
     */
    private void announceChange(){
        setChanged();
        notifyObservers();
    }


    /**
     * Displays current state of the result matrix
     * @return The current answer as a String or "No answer" message
     */
    public String display(){
        if(answer.isPresent()){
            return answer.get().toString();
        }
        return "No answer available";
    }
}
