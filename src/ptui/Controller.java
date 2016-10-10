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
     * Runs interactive mode and calls appropriate functions in model
     */
    public void run() {
        // Manually creating a matrix to test MatrixModel
        /*int[] dims = {2,2};
        MatrixModel mmodel = new MatrixModel(dims);
        mmodel.insert(1.0, 0, 0);
        mmodel.insert(2.0, 0, 1);
        mmodel.insert(3.0, 1, 0);
        mmodel.insert(4.0, 1, 1);
        System.out.println(mmodel.toString());

        double[][] identity = mmodel.getTranspose();
        System.out.println(identity);*/
        Scanner in = new Scanner(System.in);


        in.close();
    }

} // End of Controller class
