import ptui.Controller;
import ptui.Ptui;

/**
 * Main Class of LightMatrixCalculator Project
 * The "runner" of the overall project
 *
 * This program is meant to execute non-boolean calculations
 *
 * @author Analia Mok
 */
public class MatrixCalc {

    /**
     * An enumerated type for the two different views
     * The UNKNOWN value is never used, but here to show
     * that it can be implied that an UNKNOWN value exists
     */
    private enum Modes{ PTUI, GUI, UNKNOWN }


    /**
     * Prints the welcome message - including available functions
     * Launches the appropriate view
     * @param args
     */
    public static void main(String[] args) {

        if(args.length > 0){
            Modes mode = Modes.valueOf(args[0].toUpperCase());
            switch(mode){
                case PTUI:
                    // Create Ptui
                    Ptui ptui = new Ptui();

                    // Create a controller
                    Controller ctrl = new Controller(ptui.getModel());

                    // Run controller
                    ctrl.run();
                    break;
                case GUI:
                    break;
                default:
                    System.err.println("Unknown mode: " + args[0]);
                    System.exit(-1);
            }

        }else{
            System.err.println("Usage: No argument provided");
            System.exit(-1);
        }

    } // End of main

}
