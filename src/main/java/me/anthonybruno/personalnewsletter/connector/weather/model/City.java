package me.anthonybruno.personalnewsletter.connector.weather.model;

import java.time.ZoneId;
import java.time.ZoneOffset;

public class City {

    private final String name;
    private final String country;
    private final double longitude;
    private final double latitude;


    public City(String name, String country, double latitude, double longitude) {
        this.name = name;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public City(double latitude, double longitude) {
        this(null, null, latitude, longitude);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public ZoneId getTimeZone() {
        return ZoneOffset.ofHoursMinutes(9, 30); //FIXME: Hardcoded!
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return country;
    }
}
