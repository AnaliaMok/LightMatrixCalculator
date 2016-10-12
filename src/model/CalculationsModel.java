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
     * A Queue of Matrices to Calculate with.
     * This queue will only hold two MatrixModel instances at
     * a time. After each calculation, the matrix should be cleared.
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
     *      The matrices Queue should have a length of two
     * matrixCheck is used to see if the current set of matrices in
     * the Queue is valid for the given calculation
     * @param kw Keyword representing the current calculation
     * @return true if calculation can continue; false otherwise
     */
    public boolean matrixCheck(Keyword kw){
        // Record a reference to the head of matrices
        MatrixModel head = this.matrices.poll();
        //MatrixModel curr = new MatrixModel();

        // Create two 2-element integer arrays to compare
        // after re-assignment
        int[] dims1 = {head.getDims()[0], head.getDims()[1]};
        //int[] dims2 = new int[2];

        // Enqueue head back to the matrices queue
        // Extract and assign the dimensions of the current front
        // of the queue. Remove this current front and enqueue back to queue
        this.matrices.add(head);
        head = this.matrices.poll();
        int[] dims2 = {head.getDims()[0],head.getDims()[1]};
        this.matrices.add(head);

        // Now Compare Dimensions Based on the kw argument
        switch(kw){
            case ADD:
            case SUB:
                if(this.hasSameDims(dims1, dims2)){
                    return true;
                }else{
                    // Added an "ED" so that the message is ADDED or SUBTRACTED
                    System.out.printf("Usage: Matrices with different dimensions cannot be %sED\n",
                            kw.toString());
                    return false;
                }
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
     * Pre-condition: Matrices have the same dimensions.
     *      matrices Queue IS NOT empty
     * Post-condition: answer now holds a MatrixModel object
     *      representing a sum
     * Adds two matrices dequeued from the matrices Queue
     *
     */
    public void add(){
        // Grab first two matrices from Queue
        MatrixModel m1 = this.matrices.poll();
        MatrixModel m2 = this.matrices.poll();

        // Grabbing the total rows & total columns
        int totRows = m1.getDims()[0];
        int totCols = m1.getDims()[1];

        // Finding the total number of elements that will be reviewed
        // Given that the dimensions should be the same, both m1 & m2
        // will have the same number of elements
        int totalElements = totRows*totCols;

        // Initializing MatrixModel answer
        this.answer = Optional.of(new MatrixModel(m1.getDims()));

        // Now Index through and add elements to answer
        for(int i = 0; i < totalElements; i++){
            int row = (int)(Math.floor(i/totRows));
            int col = (int)(i%totCols);

            // Adjusting row and columns based on the number of rows
            // compared to total columns
            if(totRows < totCols){
                if((i != 0) && (i%totRows == 0)){
                    row--;
                }
            }else if(totRows > totCols){
                row = (int)(Math.floor(i/totCols));
            }

            // Add the values at (row,col) in both matrices then insert into
            // answer.
            double sum = m1.valueAt(row, col) + m2.valueAt(row, col);
            this.answer.get().insert(sum, row, col);

        }

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
        // Grab first two matrices from Queue
        MatrixModel m1 = this.matrices.poll();
        MatrixModel m2 = this.matrices.poll();

        // Grabbing the total rows & total columns
        int totRows = m1.getDims()[0];
        int totCols = m1.getDims()[1];

        // Finding the total number of elements that will be reviewed
        // Given that the dimensions should be the same, both m1 & m2
        // will have the same number of elements
        int totalElements = totRows*totCols;

        // Initializing MatrixModel answer
        this.answer = Optional.of(new MatrixModel(m1.getDims()));

        // Now Index through and add elements to answer
        for(int i = 0; i < totalElements; i++){
            int row = (int)(Math.floor(i/totRows));
            int col = (int)(i%totCols);

            // Adjusting row and columns based on the number of rows
            // compared to total columns
            if(totRows < totCols){
                if((i != 0) && (i%totRows == 0)){
                    row--;
                }
            }else if(totRows > totCols){
                row = (int)(Math.floor(i/totCols));
            }

            // Add the values at (row,col) in both matrices then insert into
            // answer.
            double diff = m1.valueAt(row, col) - m2.valueAt(row, col);
            this.answer.get().insert(diff, row, col);

        }

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
