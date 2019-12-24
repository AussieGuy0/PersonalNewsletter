package dev.anthonybruno.personalnews.connector.weather.darksky;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZoneId;

public class ForecastResponse {

    private final double latitude;
    private final double longitude;
    private final ZoneId timezone;
    private final WeatherResponse currently;
    private final DailyForecastResponse daily;

    @JsonCreator
    public ForecastResponse(@JsonProperty("latitude") double latitude,
                            @JsonProperty("longitude") double longitude,
                            @JsonProperty("timezone") String timezone,
                            @JsonProperty("currently") WeatherResponse currently,
                            @JsonProperty("daily") DailyForecastResponse daily) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = ZoneId.of(timezone);
        this.currently = currently;
        this.daily = daily;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public ZoneId getTimezone() {
        return timezone;
    }

    public WeatherResponse getCurrently() {
        return currently;
    }

    public DailyForecastResponse getDaily() {
        return daily;
    }
}
