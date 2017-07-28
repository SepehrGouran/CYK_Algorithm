package sepehr.beans;

import java.util.ArrayList;

/**
 * Created by Sepehr on 7/24/2017.
 */
public class X {

    private int i;
    private int j;
    private String production;

    public X(int i, int j) {
        this.i = i;
        this.j = j;
        this.production = "";
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    @Override
    public String toString() {
        return "X{" +
                "i=" + i +
                ", j=" + j +
                '}';
    }

    public ArrayList<Production> product(X x1, X x2, Element[][] table, ArrayList<Production> productions) {

        ArrayList<Production> result = new ArrayList<>();

        ArrayList<String> expressions = new ArrayList<>();

        ArrayList<Production> x1Production = getProductionsAt(x1.getJ(), x1.getI(), table);
        ArrayList<Production> x2Production = getProductionsAt(x2.getJ(), x2.getI(), table);

        if (x1Production != null && x2Production != null) {
            for (Production p1 : x1Production) {

                for (Production p2 : x2Production) {

                    String left = p1.getExpression();
                    String right = p2.getExpression();

                    int kLen = 0;
                    int lLen = 0;

                    if (containDigit(left)) {
                        kLen = left.length() - 1;
                    } else {
                        kLen = left.length();
                    }

                    if (containDigit(right)) {
                        lLen = right.length() - 1;
                    } else {
                        lLen = right.length();
                    }

                    for (int k = 0; k < kLen; k++) {

                        for (int l = 0; l < lLen; l++) {

                            if (left.length() > 1 && Character.isDigit(left.charAt(1))
                                    || right.length() > 1 && Character.isDigit(right.charAt(1))) {

                                String leftExpression = left.substring(k, k+1);

                                if (left.length() > 1 && Character.isDigit(left.charAt(1))) {
                                    leftExpression = left.substring(k, k+2);
                                }

                                String rightExpression = right.substring(l, l+1);

                                if (right.length() > 1 && Character.isDigit(right.charAt(1))) {

                                    rightExpression = right.substring(l, l+2);
                                }

                                expressions.add(leftExpression + rightExpression);
                            } else {
                                String expression = left.substring(k, k + 1) + right.substring(l, l + 1);
                                expressions.add(expression);
                            }
                        }
                    }

                }
            }
        }

        for (Production p : productions) {
            for (String exp : expressions) {
                if (p.getSymbol().endsWith(exp)) {
                    Production pro = new Production(p.getExpression(), p.getSymbol());
                    result.add(pro);
                }
            }
        }

        return result;
    }

    private ArrayList<Production> getProductionsAt(int rowIndex, int columnIndex, Element[][] table) {

        for (int k = 0; k < table.length; k++) {
            for (int l = 0; l < table.length - k; l++) {
                if (table[k][l].getColumnIndex() == columnIndex
                        && table[k][l].getRowIndex() == rowIndex) {
                    return table[k][l].getProductions();
                }
            }
        }

        return null;
    }

    private boolean containDigit (String s) {

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }

        return false;
    }
}
