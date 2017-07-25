package sepehr.beans;

import java.util.ArrayList;

/**
 * Created by Sepehr on 7/25/2017.
 */
public class Element {

    private int rowIndex;
    private int columnIndex;
    private String productions;

    public Element(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public String getProductions() {
        return productions;
    }

    public void setProductions(String productions) {
        this.productions = productions;
    }

    @Override
    public String toString() {
        return "index: " + rowIndex + " " + columnIndex + " P: " + productions;
    }
}
