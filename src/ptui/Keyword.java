package ptui;

/**
 * Defines the set of Keywords/Commands that a user can
 * enter
 * Each keyword will be associated with a shorthand (or short as possible)
 * phrase - represented as a string
 *
 * Keywords that can be inputted by the user:
 *      ADD - Add Matrices
 *      SMULT - Scalar Multiplication
 *      MULT - Multiple Matrices
 *      SUB - Subtract Matrices
 *      INVERSE - Find the inverse of a matrix
 *      TRANSPOSE - Find the transpose if a matrix
 *      REF - Row reduce the matrix to row echelon form
 *      RREF - Row reduce the matrix to reduced row echelon form
 *      ANS - Use previous answer in calculation
 *      DISPLAY - Displays previous answer
 *      UNKNOWN - Technically not a used command, for cases when an unknown
 *          command is given
 *      HELP - Displays help message
 *      QUIT - Exits program
 *
 * @author Analia Mok
 */
enum Keyword {

    ADD("A"), SMULT("SM"), MULT("M"), SUB("S"), INVERSE("INV"), TRANSPOSE("T"), REF("REF"), RREF("RREF"),
    ANS("ANS"), DISPLAY("D"), HELP("H"), QUIT("Q"), UNKNOWN("U");

    /* THe corresponding shorthand command of a given keyword*/
    String shorthand;

    /* Private constructor to help assign shorthands */
    Keyword(String shorthand) {
        this.shorthand = shorthand;
    }

    /**
     * Overrides the toString method so that the full names
     * of the Keywords are printed out when needed
     *
     * @return the full name of a Keyword as a String
     */
    @Override
    public String toString() {
        switch (this) {
            case ADD:
                return "ADD";
            case SMULT:
                return "SCALAR MULTIPLICATION";
            case MULT:
                return "MULTIPLY";
            case SUB:
                return "SUBTRACT";
            case INVERSE:
                return "INVERSE";
            case TRANSPOSE:
                return "TRANSPOSE";
            case REF:
                return "REF";
            case RREF:
                return "RREF";
            case ANS:
                return "ANSWER";
            case DISPLAY:
                return "DISPLAY";
            case HELP:
                return "HELP";
            case QUIT:
                return "QUIT";
            default:
                return "UNKNOWN";
        }
    }

    /* Takes in a shorthand and returns the corresponding Keyword */
    private static Keyword readShorthand(String sh){
        // NOTE: I don't normally write my switch statements like this
        // but since each case only has one line of code, I scrunched
        // all the lines like this make it easier to read
        switch (sh){
            case "A": return ADD;
            case "SM": return SMULT;
            case "M": return MULT;
            case "S": return SUB;
            case "INV": return INVERSE;
            case "T": return TRANSPOSE;
            case "REF": return REF;
            case "RREF": return RREF;
            case "ANS": return ANS;
            case "D": return DISPLAY;
            case "H": return HELP;
            case "Q": return QUIT;
            default: return UNKNOWN;
        }

    } // End of readShorthand

    /**
     * Precondition: String s is passed in as all uppercase
     * Main getter method to extract a Keyword out of a String
     * @param s a String to read & extract from
     * @return a Keyword
     */
    public static Keyword getKeyword(String s){
        // First attempt to extract a keyword for if s is a shorthand
        Keyword key = readShorthand(s);

        // In case s is not a shorthand, convert the String with default valueOf method
        if(key == UNKNOWN){
            key = Keyword.valueOf(s); // If key was unknown to begin with, then this is just an extraneous step
        }
        return key;
    } // End of getKeyword

} // End of Keyword enum
