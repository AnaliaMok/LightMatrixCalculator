package ptui;

import model.CalculationsModel;

import java.util.Observable;
import java.util.Observer;

/**
 * Plain-text view of Light Matrix Calculator
 *
 * @author Analia Mok
 */
public class Ptui implements Observer{

    /**
     * Instance of current model object
     */
    private CalculationsModel model;


    /**
     * Constructor of ptui
     */
    public Ptui(){
        this.model = new CalculationsModel();
        this.model.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg){
        // Just re-displays the results, for now
        System.out.println("Result:");
        this.model.display();
    }

    /**
     * Getter method for the model defined during creation of PTUI
     * @return the model held defined in the Ptui
     */
    public CalculationsModel getModel(){
        return this.model;
    } // End of getModel

} // End of Ptui class
