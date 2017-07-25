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

    public String product(X x1, X x2, Element[][] table, ArrayList<Production> productions) {

        String x1Production = getProductionsAt(x1.getI(), x1.getJ(), table);
        String x2Production = getProductionsAt(x2.getI(), x2.getJ(), table);

        StringBuilder stringBuilder = new StringBuilder(getProduction());

        stringBuilder.append(x1Production + " " + x2Production);

        return stringBuilder.toString();
    }

    private String getProductionsAt(int rowIndex, int columnIndex, Element[][] table) {

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
}
