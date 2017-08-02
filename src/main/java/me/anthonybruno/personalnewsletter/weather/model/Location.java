package me.anthonybruno.personalnewsletter.weather.model;

public class Location {

    public static final String NO_NAME = "unnamed";

    private final String name;
    private final double longitude;
    private final double latitude;


    public Location(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location(double latitude, double longitude) {
        this(NO_NAME, latitude, longitude);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
