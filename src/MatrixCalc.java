/**
 * Main Class of LightMatrixCalculator Project
 * The "runner" of the overall project
 *
 * @author Analia Mok
 */
public class MatrixCalc {

    /**
     * An enumerated type for the two different views
     */
    private enum Modes{ PTUI, GUI, UNKNOWN }


    /**
     * Prints the welcome message - including available functions
     * Launches the appropriate view
     * @param args
     */
    public static void main(String[] args) {
        Modes mode = Modes.valueOf(args[0]);
        switch(mode){
            case PTUI:
                // Create a controller
                // Create Ptui

                break;
            case GUI:
                break;
            default:
                System.err.println("Unknown mode: " + args[0]);
                System.exit(-1);
        }
    } // End of main

}
