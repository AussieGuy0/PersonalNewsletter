package dev.anthonybruno.personalnews.connector.weather.model;

import java.time.ZoneId;
import java.time.ZoneOffset;

public class Location {

    private final String name;
    private final String country;
    private final double longitude;
    private final double latitude;
    private final ZoneId timezone;


    public Location(String name, String country, double latitude, double longitude, ZoneId timezone) {
        this.name = name;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
    }

    public static Location of(double latitude, double longitude) {
        return new Location(null, null, latitude, longitude, ZoneOffset.UTC);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return country;
    }

    public ZoneId getTimezone() {
        return timezone;
    }
}
