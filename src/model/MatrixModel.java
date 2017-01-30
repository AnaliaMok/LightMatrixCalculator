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
    private ArrayList<Double> matrix;

    /**
     * The inverse of the current matrix
     */
    private ArrayList<Double> inverse;

    /**
     * The identity matrix of current matrix
     */
    private ArrayList<Double> identity;

    /**
     * The transpose of the current matrix
     */
    private ArrayList<Double> transpose;

    /**
     * The determinant of the current matrix
     */
    private Double determinant;


    /**
     * Main Constructor of Matrix
     * Will only contain zeroes at this point
     * @param dims A 1D integer array containing the dimensions
     *             of this matrix
     */
    public MatrixModel(int dims[]){

        // Initial capacity set to total number of values possible
        // with the given dimensions
        int size = dims[0] * dims[1];
        this.matrix = new ArrayList<Double>(size);

        // Initializing ArrayList with zeroes
        for(int i = 0; i < size; i++){
            this.matrix.add(0.0);
        }

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
        this.matrix = new ArrayList<Double>(this.dims[0]*this.dims[1]);
        this.matrix.addAll(m.getMatrix());

    } // End of copy constructor


    /**
     * Pre-condition: Row and column are zero-based
     * Used to insert values into the matrix (or replace
     * if necessary)
     *
     * @param value A double to insert into the matrix
     * @param row The row to place the value in
     * @param col the column to place the value in
     */
    public void insert(Double value, int row, int col){
        // Current Index = currRow * totalColumns + currColumns
        int currIdx = (row * this.dims[1]) + col;

        // Removing Element currently at the index
        this.matrix.remove(currIdx);

        // Then replacing with the necessary value
        this.matrix.add(currIdx, value);
    } // End of insert()


    /**
     * Pre-condition: Matrix has been instantiated
     *      Values are 0-based
     * @param row An integer representing the row to find a value
     * @param col An integer representing the column to find a value
     * @return A Double representing the value at (row,col)
     */
    Double valueAt(int row, int col){
        return this.matrix.get((row*this.dims[1])+col);
    } // End of valueAt


    /**
     * colAt retrieves and returns the values at the specified column
     * @param col An Integer representing what column to retrieve
     * @return A double array
     */
    ArrayList<Double> colAt(int col){

        // NOTE: The number of elements in a column are equal to the number
        // of rows in a matrix
        ArrayList<Double> column = new ArrayList<Double>(this.dims[0]);

        // Retrieving values at column col
        for(int r = 0; r < this.dims[0]; r++){
            //int currIdx = (r * this.dims[1] + col);

            // Then adding element at (r, col) in matrix
            // to column at currIdx
            column.add(this.valueAt(r, col));
        }

        return column;
    } // End of colAt


    /**
     * rowAt retrieves and returns the values at the specified row
     * @param row An Integer representing what row to retrieve
     * @return A double array
     */
    ArrayList<Double> rowAt(int row){

        int totalCols = this.dims[1];

        // NOTE: The number of elements in a row are equal
        // to the number of columns in a matrix
        ArrayList<Double> result = new ArrayList<Double>(totalCols);

        // Retrieving values at row row
        for(int c = 0; c < totalCols; c++){
            //int currIdx = (row * totalCols + c);

            // Then add element at (row, c) in matrix to
            // result at currIdx
            result.add(this.valueAt(row, c));
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
     * @return An ArrayList of Numbers
     */
    public ArrayList<Double> getMatrix(){
        return this.matrix;
    } // End of getMatrix


    /**
     * Returns the identity of the current matrix
     * Will create the identity matrix if it has not
     * already
     * @return identity Matrix
     */
    protected ArrayList<Double> getIdentity(){
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
        this.identity = new ArrayList<Double>(this.dims[0]*this.dims[1]);

        int totalElem = this.dims[0]*this.dims[1];
        for(int i = 0; i < totalElem; i++){
            int currRow = i / this.dims[1];
            int currCol = i % this.dims[1];

            if(currRow == currCol){
                // Add a One on a diagonal entry
                this.identity.add(1.0);
            }else{
                // Otherwise add a zero
                this.identity.add(0.0);
            }
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
    protected ArrayList<Double> getInverse(){
        if(this.inverse == null){
            findInverse();
        }
        return this.inverse;
    } // End of getInverse


    /**
     * Pre-condition: Current matrix is not 2x2.
     *      - row and col are 0-based
     *
     * getMinorMatrix - returns the (row, col)-minor matrix of
     *      this MatrixModel instance. The (row,col)-minor matrix
     *      is the current MatrixModel's internal matrix, but without
     *      the (row)th row and the (col)th column.
     *
     * @param row The current row (row to remove)
     * @param col The current column (column to remove)
     * @return A MatrixModel without the (row)th row and the (col)th oolumn
     */
    private MatrixModel getMinorMatrix(int row, int col){

        // Creating new matrix with one less row and column
        int[] newDims = {this.dims[0]-1, this.dims[1]-1};
        MatrixModel minor = new MatrixModel(newDims);

        // Current Row and columns of the minor matrix
        int currMinRow = 0, currMinCol = 0;

        // Adding elements
        int totalElems = this.dims[0]*this.dims[1];
        for(int i = 0; i < totalElems; i++){
            int currRow = i / this.dims[1];
            int currCol = i % this.dims[1];

            // Skipping All elements in the (row)th row & (col)th column
            // in this matrix
            if(currRow == row || currCol == col){
                continue;
            }

            if(currMinCol == newDims[1]){
                currMinCol = 0; // Reset Column Index
                currMinRow++; // Increment Row Index
            }
            minor.insert(this.valueAt(currRow, currCol), currMinRow, currMinCol++);

        }

        return minor;
    } // End of getMinor Matrix


    /**
     * Pre-Condition: The elements of m are all of type Number
     * Recursive Function that takes a single matrix and
     * finds its determinant - which will be used in the inverse function
     * This method will use co-factor expansion on the first row of the
     * matrix and will make recursive calls to each matrix that is larger
     * than a 2x2 matrix.
     * The determinant of 2x2 matrices can be easily calculated.
     *
     * NOTE: This method will be used to in the matrix check
     *
     * @return A Double representing the determinant of current matrix
     */
    Double getDeterminant(){

        // if determinant has already been calculated
        if(this.determinant == null){
            return determinant;
        }

        determinant = 0.0;

        // Checking if current matrix is a 2x2 matrix
        // NOTE: Method is only ever called if Matrix m has
        // already been deemed invertible. Therefore, only
        // need to check the value of either the total rows
        // or total columns
        //
        // Formula for a 2x2 Matrix:
        // | a b |
        // | c d |
        // det = ad - bc
        if(this.dims[0] == 2){
            // If matrix is 2x2, then can assume the positions of elements
            Double a = this.valueAt(0, 0);
            Double b = this.valueAt(0, 1);
            Double c = this.valueAt(1, 0);
            Double d = this.valueAt(1, 1);

            determinant = (a*d)-(b*c);
            return determinant;
        }else{
            // NOTE TO SELF: Remember that co-factor expansion formula is one-based
            // Formula: det(A) = (-1)^(i+j)(aij)(Cij)
            //      where i = row, j = column, aij = values at (i,j)
            //          and Cij = the ij minor matrix of m

            // List of ij minor matrices
            ArrayList<MatrixModel> minors = new ArrayList<MatrixModel>();
            int totalCols = this.dims[1]; // Total Columns = Total Elements Per Row
            for(int i = 0; i < totalCols; i++){
                int currRow = i / totalCols;
                int currCol = i % totalCols;

                Double value = this.valueAt(currRow, currCol);

                // The negative one raised to the sum of the currRow and currCol
                // (1-based)
                int negation = (int)Math.pow(-1, ((currRow+1)+(currCol+1)));

                // Equation: determinant += negation * value * det(currRow, currCol minor matrix)
                /*determinant = new BigDecimal(determinant.toString())
                        .add(new BigDecimal(negation)
                                .multiply(new BigDecimal(value.toString()))
                                .multiply(new BigDecimal(getDeterminant(m.getMinorMatrix(currRow, currCol)).toString())));*/

                determinant += negation * value * (getMinorMatrix(currRow, currCol).getDeterminant());
            }

            return this.determinant;
        }

    } // End of getDeterminant

    /**
     * Pre-condition: the inverse matrix has not been initialized
     * Creates the inverse matrix through Co-factor expansion
     */
    private void findInverse(){

        // Initializing Inverse
        this.inverse = new ArrayList<Double>();

        // Constructing a cofactor matrix
        MatrixModel cofactors = new MatrixModel(this.dims);
        int totalCols = this.dims[1];
        int totalElem = this.dims[0]*totalCols;

        for(int i = 0; i < totalElem; i++){
            int row = i / totalCols;
            int col = i % totalCols;
            // Determinant of row,col-minor matrix
            Double minorDet = (this.getMinorMatrix(row, col).getDeterminant());

            // Cofactor of element at (row, col) =
            // (-1)^(row+col) * det((row,col)-minor matrix)
            /*Number cofactor = new BigDecimal(Math.pow(-1, row+col))
                    .multiply(new BigDecimal(minorDet.toString()));*/
            Double cofactor = Math.pow(-1, row+col) * minorDet;
            cofactors.insert(cofactor, row, col);
        }

        // Getting determinant of matrix using cofactor expansion
        Double det = 0.0;

        // Expanding on the first row, so need to iterate based
        // on total number of columns

        for(int i = 0; i < totalCols; i++){
            int row = i / totalCols;
            int col = i % totalCols;

            // Equivalent of : det += this.valueAt(row, col) * cofactors.valueAt(row,col)
            det += this.valueAt(row, col) * cofactors.valueAt(row, col);
        }

        // Adjugate Matrix = the transpose of the cofactor matrix
        ArrayList<Double> adjugate = cofactors.getTranspose();

        // Multiplying each element in the adjugate by the reciprocal
        // of the determinant and assigning this value to the corresponding
        // location in the inverse "matrix"
        //BigDecimal detReciprocal = new BigDecimal(1).divide(det);
        Double detReciprocal = 1/det;

        for(Double d : adjugate){
            //Number val = new BigDecimal(detReciprocal).multiply(new BigDecimal(n.toString()));
            Double val = detReciprocal * d;
            this.inverse.add(val);
        }

    } // End of inverse


    /**
     * Returns the transpose of the current matrix
     * Will initialize the transpose field - if it hasn't already
     * @return the transpose field
     */
    protected ArrayList<Double> getTranspose(){
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
        this.transpose = new ArrayList<Double>();

        // The order of the dimensions is reversed as a reminder
        // that the dimensions of the transpose is the reverse of
        // the dimensions of the original
        int totalElem = this.dims[1]*this.dims[0];

        // Initializing Transpose Array
        for(int i = 0; i < totalElem; i++) this.transpose.add(0.0);

        for(int i = 0; i < totalElem; i++){
            int row = i / this.dims[1];
            int col = i % this.dims[1];
            int transIdx = (col * this.dims[1]) + row;

            this.transpose.remove(transIdx);
            this.transpose.add(transIdx, this.valueAt(row, col));
        }

    } // End of transpose


    @Override
    public String toString(){
        String result = "";

        // Iterating through entire matrix
        int totalElem = this.dims[0]*this.dims[1];
        for(int i = 0; i < totalElem; i++){
            // Calculating Current Dimensions
            int row = i / this.dims[1];
            int col = i % this.dims[1];
            double num = this.valueAt(row, col);

            if(col == 0){
                // Tabbing over the entire result so
                // need to add a tab character at the beginning
                // of each line
                result += "|\t";
            }

            if(col == this.dims[1]-1){
                // Need to start new line
                // Also checking to see whether to print num
                // as an integer or some decimal value
                if(num % 1 == 0){
                    result += "" + String.format("%d", (int)num) + "\t|\n";
                }else {
                    result += "" + String.format("%.4f", num) + "\t|\n";
                }
                continue;
            }

            // Checking to see whether to print num as an integer or
            // some decimal value
            if(num % 1 == 0){
                result += "" + String.format("%d", num) + "\t";
            }else {
                result += "" + String.format("%.4f", num) + "\t";
            }

        }

        return result;
    }


} // End of Matrix Class
