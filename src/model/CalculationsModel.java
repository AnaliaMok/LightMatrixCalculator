package model;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Optional;
import java.util.Queue;
import java.util.ArrayList;


/**
 * Implementation of Most Possible Non-boolean Matrix Calculations
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
        MatrixModel head = this.matrices.peek();

        // Create two 2-element integer arrays to compare
        // after re-assignment
        int[] dims1 = {head.getDims()[0], head.getDims()[1]};
        int[] dims2 = new int[2];

        // If kw corresponds to a calculation requiring more than one
        // matrix, then execute the following code block
        // Else, just look at the head of the Queue
        if(kw != Keyword.INVERSE && kw != Keyword.REF &&
                kw != Keyword.RREF && kw != Keyword.TRANSPOSE){

            // Remove front
            head = this.matrices.poll();

            // Enqueue head back to the matrices queue
            // Extract the current front & assign values to dims2
            // Remove this current front and enqueue again
            this.matrices.add(head);
            head = this.matrices.poll();
            dims2[0] = head.getDims()[0];
            dims2[1] = head.getDims()[1];
            this.matrices.add(head);
        }

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
                // Are the number of columns of matrix one equal
                // to the number of rows in matrix two?
                if(dims1[1] != dims2[0]){
                    System.out.println("Usage: Matrices with different inner dimensions cannot be MULTIPLIED");
                    return false;
                }
                return true;
            case INVERSE:
                // Is this matrix square?
                if(dims1[0] != dims1[1]){
                    System.out.println("Usage: Only square matrices are INVERTIBLE");
                    return false;
                }

                // Matrices with a determinant of zero are not invertible
                Number det = MatrixModel.getDeterminant(head);

                if(det.doubleValue() == 0){
                    System.out.printf("Matrix is not invertible. Determinant = %8.3f\n", det);
                    return false;
                }
                return true;
            default:
                return true;
        }

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
            int row = i / totCols;
            int col = i % totCols;

            // Add the values at (row,col) in both matrices then insert into
            // answer.
            double m1Val = m1.valueAt(row, col);
            double m2Val = m2.valueAt(row, col);
            this.answer.get().insert(m1Val+m2Val, row, col);

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
        // First, extract a matrix from Queue
        MatrixModel m = this.matrices.poll();

        // Extracting dimensions
        int totalRows = m.getDims()[0];
        int totalCols = m.getDims()[1];
        int totalElements = totalRows * totalCols;

        // Initialized answer
        this.answer = Optional.of(new MatrixModel(m.getDims()));

        // Iterate Based on number of elements in matrix
        for(int i = 0; i < totalElements; i++){
            int row = i / totalCols;
            int col = i % totalCols;

            // Multiply the value at (row, col) by the scalar quantity
            double product = scalar * m.valueAt(row, col);

            if(this.answer.isPresent()){
                this.answer.get().insert(product, row, col);
            }
        }

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
    private Double dotProd(ArrayList<Double> v1, ArrayList<Double> v2){
        double result = 0;

        // The length of v1 and v2 should be the same length
        // so it doesn't matter which I increment to
        for(int i = 0; i < v1.size(); i++){
            result += (v1.get(i) * v2.get(i));
        }

        return result;
    } // End of dotProd


    /**
     * Pre-condition: The "inner" dimensions of the two matrices
     *      are equal
     * Post-condition: answer now holds a MatrixModel object representing
     *      a product
     * Multiplies two matrices dequeued from the matrices Queue
     */
    public void multiply(){
        // Grab first two matrices from Queue
        MatrixModel m1 = this.matrices.poll();
        MatrixModel m2 = this.matrices.poll();

        // The resulting matrix will have dimensions where the the # of rows
        // are equal to the 1st matrix's # of rows & the # of columns will
        // equal the number of columns of the 2nd matrix.
        int[] ansDims = {m1.getDims()[0], m2.getDims()[1]};
        this.answer = Optional.of(new MatrixModel(ansDims));

        // Dot product each row vector in m1 with each
        // column in m2

        // Iterating rows based on the first matrix
        for(int r = 0; r < m1.getDims()[0]; r++){
            // Iterating columns based on the second matrix
            for(int c = 0; c < m2.getDims()[1]; c++){
                double product = dotProd(m1.rowAt(r), m2.colAt(c));
                if(this.answer.isPresent()){
                    this.answer.get().insert(product, r, c);
                }
            }
        }

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
            int row = i / totCols;
            int col = i % totCols;

            // Add the values at (row,col) in both matrices then insert into
            // answer.
            double m1Val = m1.valueAt(row, col);
            double m2Val = m2.valueAt(row, col);

            if(this.answer.isPresent()){
                this.answer.get().insert((m1Val - m2Val), row, col);
            }
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

        // Remove front of queue
        MatrixModel m = this.matrices.poll();
        ArrayList<Double> inv = m.getInverse();

        // Grabbing Dimensions of inv(same as m) and
        // calculating total amt of elements
        int[] invDims = m.getDims();
        int totalElem = invDims[0] * invDims[1];

        // Initializing answer
        this.answer = Optional.of(new MatrixModel(invDims));

        // Copying Elements of inv to answer matrix
        for(int i = 0; i < totalElem; i++){
            int row = i/invDims[1];
            int col = i%invDims[1];
            //int invIdx = (row*invDims[1]) + col;

            // Insert element at transpose(row,col) into answer(row,col)
            if(this.answer.isPresent()){
                this.answer.get().insert(inv.get(i), row, col);
            }
        }

        announceChange();
    } // End of inverse


    /**
     * Post-condition: answer now holds a MatrixModel object representing
     *      a transpose
     * transpose will call the transpose getter method of the matrix at the
     * front of the queue and assign the retrieved matrix to answer
     */
    public void transpose(){

        // Remove the front of the queue & retrieve it's transpose
        MatrixModel m = this.matrices.poll();
        ArrayList<Double> transpose = m.getTranspose();

        // NOTE: Dimensions of a transpose are equal to the swapped
        // dimensions of the its original matrix
        int[] transDims = {m.getDims()[1], m.getDims()[0]};
        int totalElement = transDims[0] * transDims[1];

        // Initialize answer with the transpose's dimensions
        this.answer = Optional.of(new MatrixModel(transDims));

        // Copy values from transpose to answer
        for(int i = 0; i < totalElement; i++){
            int row = i/transDims[1];
            int col = i%transDims[1];
            int transIdx = (row*transDims[1]) + col;

            // Insert element at transpose(row,col) into answer(row,col)
            if(this.answer.isPresent()){
                this.answer.get().insert(transpose.get(transIdx), row, col);
            }
        }

        announceChange();
    } // End of transpose


    /**
     * Pre-condition: ref field has not been initialized yet
     * Post-condition: answer now holds a MatrixModel object in REF
     * Row reduces the current matrix to Row Echelon Form(REF)
     */
    public void toREF(MatrixModel m){
        //TODO: Need to implement multiplication first
    } // End of toREF


    /**
     * Pre-condition: rref field has not been initialized yet
     * Post-condition: answer now holds a MatrixModel object in RREF
     * Row reduces the current ref matrix to Row Reduced Echelon Form
     * Will call toREF if ref field has not been initialized
     */
    public void toRREF(MatrixModel m){
        //TODO: Need to implement multiplication & toREF first
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
