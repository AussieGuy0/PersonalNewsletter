package dev.anthonybruno.personalnews.connector.weather.model;

public class Weather {

    private final Temperature temp;
    private final String type;

    public Weather(Temperature temp, String type) {
        this.temp = temp;
        this.type = type;
    }

    public Temperature getTemp() {
        return temp;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return temp +  " " + type;
    }
}
