package ptui;

import model.CalculationsModel;
import model.MatrixModel;

import java.util.Scanner;

/**
 * Controller Class handles all input for the plain-text view
 * @author Analia Mok
 */
public class Controller {

    /**
     * Instance of the model class
     */
    private CalculationsModel model;

    /**
     * Main constructor of Controller
     * @param model An instance of the model currently being worked with
     */
    public Controller(CalculationsModel model){
        this.model = model;
    }

    /**
     * Message that is printed on initial start up of
     * program
     */
    private void startMsg(){
        System.out.println("--------------- Matrix Calculator ----------------\n");
        helpCmd();
        System.out.println("***To use previous answer in current calculation, type ANS keyword");
        System.out.println("when entering dimensions of a matrix.");
    }


    /**
     * Prints a help message stating the available commands
     * and their descriptions
     * Is printed whenever an unknown command is typed in
     * or whenever a user calls the help command directly
     */
    private void helpCmd(){
        System.out.println("Available Commands With Shorthands in Parenthesis:");
        //System.out.println("--------------------------------------------------");
        System.out.println("==================================================");
        System.out.println("add(a)\t\t|\tAdds the inputted matrices");
        System.out.println("sub(s)\t\t|\tSubtracts the inputted matrices");
        System.out.println("smult(sm)\t|\tMultiply a matrix by a scalar");
        System.out.println("mult(m)\t\t|\tMultiplies the inputted matrices");
        System.out.println("inverse(inv)|\tFind the inverse of a matrix");
        System.out.println("transpose(t)|\tFind the transpose of a matrix");
        System.out.println("ref(ref)\t|\tFind the row echelon form of a matrix");
        System.out.println("rref(rref)\t|\tFind the reduced row echelon form of a matrix");
        System.out.println("ans(ans)\t|\tUse the previous answer in the calculation");
        System.out.println("display(d)\t|\tDisplay the previous answer");
        System.out.println("help(h)\t\t|\tDisplay this help message");
        System.out.println("clear(c)\t\t|\tClears the previous answer and currently inputted matrices");
        System.out.println("quit(q)\t\t|\tExit the program");
        System.out.println(""); // Separation line
    } // End of helpCmd


    /**
     * Executes quit command
     * Exits program
     */
    private void quit(){
        System.out.printf("Good-bye.");
        System.exit(-1);
    } // End of quit


    /**
     * acceptMatrix asks for the dimensions and the elements
     * of a given matrix
     * @param in A Scanner object to accept user input
     * @return A MatrixModel object holding inputted values
     */
    private MatrixModel acceptMatrix(Scanner in){
        // Accept dimensions
        int[] dims = new int[2];
        System.out.print("Enter the dimensions of a matrix(separated with a space): ");

        // Split the input into an array of Strings
        String[] input = (in.nextLine().trim()).split(" ");

        // In case user enters nothing
        while(input.length < 1){
            System.out.print("Please enter at least one integer (i.e. 5): ");
            input = (in.nextLine().trim()).split(" ");
        }

        // If only one value was inputted, make a square matrix
        if(input.length == 1){
            dims[0] = dims[1] = Integer.parseInt(input[0]);
        }else{
            // Else, just take the first two values
            dims[0] = Integer.parseInt(input[0]);
            dims[1] = Integer.parseInt(input[1]);
        }

        // Instantiate a MatrixModel object with dima
        MatrixModel newM = new MatrixModel(dims);

        // Accept User Input & Insert into newM
        // If too many elements are given, just don't accept excess
        // If too little are given, have user re-enter line
        for(int row = 0; row < dims[0]; row++){
            //TODO: Might want to start counting rows from 1 instead of zero
            //TODO: Some users may not be comfortable counting from zero
            System.out.printf("Enter values for row %d separated by spaces: ", row);
            input = (in.nextLine().trim()).split(" ");

            // If not enough values were given, start back at the top
            if(input.length < dims[1]){
                System.out.println("USAGE: Not enough values entered");
                row--;
                continue;
            }
            // If too many values given, just take dims[1] values, where dims[1] = # columns
            // TODO: Find a more efficient way of doing this
            for(int col = 0; col < dims[1]; col++){
                newM.insert(Double.parseDouble(input[col]), row, col);
            }

        }
        System.out.println(newM.toString());
        return newM;
    } // End of acceptMatrix


    /**
     * Appropriately calls handles keywords
     * Calls method(s) to accept matrices (or a matrix) to execute
     * calculations on.
     * @param kw A Keyword to handle
     * @param in Current scanner object being used to take in input
     * @return true if calculation successful; false otherwise
     */
    private boolean commandHandle(Keyword kw, Scanner in){
        switch(kw){
            case ADD:
                acceptMatrix(in);
                break;
            case SMULT:
                System.out.print("Please enter a scalar: ");
                int scalar = Integer.parseInt(in.nextLine().trim());
                //TODO: Accept a matrix
                break;
            case HELP:
                helpCmd();
                break;
            case QUIT:
                quit();
                break; // Unnecessary, but here out of habit
            default:
                return false;
        }
        return true;
    } // End of command handle

    /**
     * Runs interactive mode and calls appropriate functions in model
     */
    public void run() {
        // Manually creating a matrix to test MatrixModel
        startMsg();
        Scanner in = new Scanner(System.in);
        String currLine = "";

        // Loop Until user enters Quit(q) command
        while((currLine = in.nextLine().trim()) != null){
            Keyword kw = Keyword.getKeyword(currLine);
            commandHandle(kw, in);
        }//*/

        in.close();
    }

} // End of Controller class
