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
    private CalculationsModel model = new CalculationsModel();


    /**
     * Constructor of ptui
     */
    public Ptui(){
        this.model.addObserver(this);
    }


    @Override
    public void update(Observable o, Object arg){
        // Just re-displays the results, for now
        System.out.println("Result:");
        this.model.display();
    }
}
