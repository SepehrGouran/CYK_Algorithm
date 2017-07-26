import sepehr.beans.*;

import java.util.ArrayList;

/**
 * Created by Sepehr on 7/24/2017.
 */
public class Main {

    public static void main(String[] args) {

        String input = "baaba";

        // Use '|' to add expression and '*' to separate productions
        // Use '#' for empty (Lambda) production
        String grammar = "S->AB|BC*A->BA|a*B->CC|b*C->AB|a*C->#*B->B";

        CYK cyk = new CYK(grammar);

        // Convert to array of Productions
        ArrayList<Production> productions = ContextFreeGrammarParser.parseGrammar(cyk.getGrammar());
        for (int i = 0; i < productions.size(); i++) {
            System.out.println(productions.get(i).toString());
        }

        System.out.println(ChomskyNormalForm.toChomskyNormalForm(productions));
        System.err.println(ChomskyNormalForm.isCNF(productions));

        /*
        // Convert productions to Chomsky Normal Form
        ArrayList<Production> cnfProductions = ChomskyNormalForm.getChomskyNormalFormProductions(productions);


        // Create 2D array of Elements
        Element[][] triangleTable = cyk.createTable(input, productions);
        for (int i = 0; i < input.length(); i++) {
            for (int j = 0; j < input.length(); j++) {
                System.out.printf("%10s", triangleTable[i][j] + "\t\n");
            }
            System.out.println("");
        }

        // Check if X 1,n is null or not
        ArrayList<Production> lastElement = triangleTable[triangleTable.length-1][0].getProductions();
        if (lastElement.toString().equalsIgnoreCase("[]")) {
            System.err.println("The language is not accepted");
        } else {
            System.err.println("The language is accepted " + lastElement.toString());

        }
        */
    }
}
