package me.anthonybruno.personalnewsletter.connector.weather.model;

public class Weather {

    private final int temp;
    private final String type;

    public Weather(int temp, String type) {
        this.temp = temp;
        this.type = type;
    }

    public int getTemp() {
        return temp;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return temp + "C " + type;
    }
}
