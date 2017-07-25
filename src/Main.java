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

        CYK cyk = new CYK("S->AB|BA*A->a");

        ArrayList<Production> productions = ContextFreeGrammarParser.parseGrammar(cyk.getGrammar());
        for (int i = 0; i < productions.size(); i++) {
            System.out.println(productions.get(i).toString());
        }

        Element[][] triangleTable = cyk.createTable(input, productions);
        for (int i = 0; i < input.length(); i++) {
            for (int j = 0; j < input.length(); j++) {
                System.out.printf("%25s", triangleTable[i][j] + "\t");
            }
            System.out.println("");
        }

    }
}
