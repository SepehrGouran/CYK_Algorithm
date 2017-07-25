import sepehr.beans.CYK;
import sepehr.beans.ContextFreeGrammarParser;
import sepehr.beans.Element;
import sepehr.beans.Production;

import java.util.ArrayList;

/**
 * Created by Sepehr on 7/24/2017.
 */
public class Main {

    public static void main(String[] args) {

        String input = "baaba";

        int[][] table = new int[input.length()][input.length()];

        // Use '|' to add expression and '*' to separate productions
        CYK cyk = new CYK("S->AB|BC*A->BA|a*B->CC|b*C->AB|a");

        ArrayList<Production> productions = ContextFreeGrammarParser.parseGrammar(cyk.getGrammar());
        for (int i = 0; i < productions.size(); i++) {
            System.out.println(productions.get(i).toString());
        }

        Element[][] triangleTable = cyk.createTable(input, productions);
        for (int i = 0; i < input.length(); i++) {
            for (int j = 0; j < input.length(); j++) {
                System.out.printf("%10s", triangleTable[i][j] + "\t\n");
            }
            System.out.println("");
        }

        ArrayList<Production> lastElement = triangleTable[triangleTable.length-1][0].getProductions();
        if (lastElement.toString().equalsIgnoreCase("[]")) {
            System.err.println("The language is not accepted");
        } else {
            System.err.println("The language is accepted " + lastElement.toString());

        }

    }
}
