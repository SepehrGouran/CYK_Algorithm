package sepehr.beans;

import java.util.ArrayList;

/**
 * Created by Sepehr on 7/24/2017.
 */
public class CYK {

    private String grammar;

    public CYK(String grammar) {
        this.grammar = grammar;
    }

    public String getGrammar() {
        return grammar;
    }

    public void setGrammar(String grammar) {
        this.grammar = grammar;
    }

    public String[][] createTable (String input, ArrayList<Production> productions){

        String[][] table = new String[input.length()][input.length()];



        return table;
    }
}
