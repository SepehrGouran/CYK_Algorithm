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

        for (int row = 0; row < n; row++) {

            for (int column = 0; column < n-row; column++) {

                ArrayList<String> acceptedProductions = new ArrayList<>();
                char c = chars[column];

                // X ij = X ik . X k+1 j
                // X 11 = X 10 . X 11
                int r = table[row][column].getRowIndex();       //row index
                int col = table[row][column].getColumnIndex();    //column index
                X x = new X(col, r);

                if (row == 0) {
                    for (Production production : productions) {

                        char terminal = production.getSymbol().charAt(0);

                        if (terminal == c) {

                            acceptedProductions.add(production.getExpression());
                        }
                    }

                    String accepted = acceptedProductions.toString();
                    table[row][column].setProductions(accepted);

                } else {

                    //System.err.println(x);
                    for (int k = col; k < col+r; k++) {
                        String res = x.product(new X(col, k), new X(k+1, r), table, productions);
                        if (res.replace("null", " ").trim().length() != 0) {
                            acceptedProductions.add(res.replace("null", " ").trim());
                        }
                    }

                    String accepted = acceptedProductions.toString();

                    table[row][column].setProductions(accepted);

                }
            }
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
