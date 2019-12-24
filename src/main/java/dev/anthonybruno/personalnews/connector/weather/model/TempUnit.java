package dev.anthonybruno.personalnews.connector.weather.model;

public enum TempUnit {

    CELSIUS("°C"), FAHRENHEIT("°F");

    private final String symbol;

    TempUnit(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
