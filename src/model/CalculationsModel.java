package model;

import java.util.Observable;
import java.util.Optional;

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
     * Pre-condition: ref field has not been initialized yet
     * Row reduces the current matrix to Row Echelon Form(REF)
     */
    public void toREF(MatrixModel m){
        //TODO
    } // End of toREF


    /**
     * Pre-condition: rref field has not been initialized yet
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
     * @return
     */
    public String display(){
        return "";
    }
}
