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

    public void product(X x1, X x2, Element[][] table, ArrayList<Production> productions) {


    }
}
