package fr.epita.sigl.mepa.core.service;

/**
 * Created by claebo_c on 28/07/16.
 */
public enum Currency {
    DOLLAR("$"),
    EURO("€"),
    POUND("£");

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    private String symbol;

    private Currency(String symbol){
        this.symbol = symbol;
    }

    public String getValue() {
        return name();
    }

    public void setValue(String value) {}


}

