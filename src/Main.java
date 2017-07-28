import sepehr.beans.*;

import java.util.ArrayList;

/**
 * Created by Sepehr on 7/24/2017.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        // Replace digits with a terminal variable
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add("a+a*a(a*)");
        inputs.add("(a-(a+a)*(a-a))+a");
        // Use '|' to add expression and '$' to separate productions
        // Use '#' for empty (Lambda) production
        // Please do not add useless variable to grammar :D it does not support

        String grammar4 = "S->AB|BC$A->BA|a$B->CC|b$C->AB|a$C->#$B->B";
        String grammar3 = "S->ABa$A->aab$B->Ac$C->#";
        String grammar2 = "S->aBA$A->acC$B->b$C->ac";
        String grammar = "S->SAS|SBS|SCS|SDS|ESF|a$A->+$B->-$C->*$D->/$E->($F->)";

        for (String input : inputs) {
            run(grammar, input);
        }

        // Use run method for testing grammars and languages
        run(grammar4, "baaba");
    }

    private static void run(String grammar, String input) throws InterruptedException {

        System.out.println("Grammar : " + grammar);
        System.out.println("Input language : " + input);

        CYK cyk = new CYK(grammar);

        // Convert to array of Productions
        System.out.println("\n Productions List : \n");
        ArrayList<Production> productions = ContextFreeGrammarParser.parseGrammar(cyk.getGrammar());

        for (int i = 0; i < productions.size(); i++) {
            System.out.println(productions.get(i).toString());
        }

        // Print arrays of productions in Chomsky Normal Form
        System.out.println("\nChomsky Normal Form : \n" +
                ChomskyNormalForm.toChomskyNormalForm(productions));


        // Convert productions to Chomsky Normal Form
        ArrayList<Production> cnfProductions = ChomskyNormalForm.getChomskyNormalFormProductions(productions);


        // Create 2D array of Elements
        System.out.println("\n\t===============================Table===============================\n");

        Element[][] triangleTable = cyk.createTable(input, cnfProductions);
        // Print triangle table
        for (int i = 0; i < input.length(); i++) {
            for (int j = 0; j < input.length(); j++) {
                System.out.printf("\t%10s", triangleTable[i][j] + "\t\n");
            }
            System.out.println("");
        }

        Thread.sleep(500);
        // Check if X 1,n is null or not
        ArrayList<Production> lastElement = triangleTable[triangleTable.length-1][0].getProductions();
        if (lastElement.toString().equalsIgnoreCase("[]")) {
            System.err.println("The language is not accepted");
        } else {
            System.err.println("The language is accepted " + lastElement.toString());

        }
    }
}
