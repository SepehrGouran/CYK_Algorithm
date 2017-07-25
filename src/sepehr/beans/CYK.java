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

    public Element[][] createTable (String input, ArrayList<Production> productions){

        Element[][] table = new Element[input.length()][input.length()];

        initTable(table, input.length());

        char[] chars = input.toCharArray();

        int n = input.length();
        int k = 0;

        for (int row = 0; row < input.length(); row++) {

            for (int column = 0; column < n-row; column++) {

                ArrayList<String> acceptedProductions = new ArrayList<>();
                char c = chars[column];

                // X ij = X ik . X k+1 j
                // X 11 = X 10 . X 11
                X x = new X(row, column);

                if (row == 0) {
                    for (Production production : productions) {

                        char terminal = production.getSymbol().charAt(0);

                        if (terminal == c) {

                            acceptedProductions.add(production.getExpression());
                        }
                    }
                } else {

                    x.product(new X(row, k), new X(k+1, column), table, productions);
                    acceptedProductions.add(x.getProduction());
                }



                table[row][column].setProductions(acceptedProductions.toString());
            }

            k++;
        }

        return table;
    }

    private void initTable(Element[][] table, int size) {

        int counter = 0;

        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size-i; j++) {
                table[i][j] = new Element(i+1+counter, j+1);
                counter++;
            }
            counter = 0;
        }
    }
}
