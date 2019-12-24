package dev.anthonybruno.personalnews.connector.weather.model;

public class Temperature {

    private final double temp;
    private final TempUnit tempUnit;

    public Temperature(double temp, TempUnit tempUnit) {
        this.temp = temp;
        this.tempUnit = tempUnit;
    }

    public static Temperature ofCelsius(double temp) {
        return new Temperature(temp, TempUnit.CELSIUS) ;
    }

    @Override
    public String toString() {
        return "" + temp + tempUnit;
    }
}
