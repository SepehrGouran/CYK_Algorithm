package sepehr.beans;

/**
 * Created by Sepehr on 7/24/2017.
 */
public class Production {

    private String expression;
    private String symbol;

    public Production() {
    }

    public Production(String expression, String symbol) {
        this.expression = expression;
        this.symbol = symbol;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "Production: " + expression + " -> " + symbol;
    }
}
