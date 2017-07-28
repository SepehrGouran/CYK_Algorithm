package sepehr.beans;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by Sepehr on 7/24/2017.
 */
public class ContextFreeGrammarParser {

    public static ArrayList<Production> parseGrammar(String grammar) {

        ArrayList<Production> productionsArray = new ArrayList<>();

        String[] productions = grammar.split("\\$");
        for (int i = 0; i < productions.length; i++) {
            String[] production = productions[i].split("->");
            String expression = production[0];
            String symbol = production[1];
            if (symbol.contains("|")){
                String[] symbols = symbol.split("\\|");
                for (int j = 0; j < symbols.length; j++) {
                    Production p = new Production(expression, symbols[j]);
                    productionsArray.add(p);
                }
            } else {
                Production p = new Production(expression, symbol);
                productionsArray.add(p);
            }
        }

        return productionsArray;
    }
}
