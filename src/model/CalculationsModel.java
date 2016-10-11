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
        //TODO
        return true;
    } // End of addAns

    /* CALCULATION METHODS*/

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
