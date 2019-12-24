package dev.anthonybruno.personalnews.connector.weather.darksky;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class WeatherResponse {

    private final Instant time;
    private final String summary;
    private final String icon;
    private final double temperature;

    @JsonCreator
    public WeatherResponse(@JsonProperty("time") long time,
                           @JsonProperty("summary") String summary,
                           @JsonProperty("icon") String icon,
                           @JsonProperty("temperature") double temperature) {
        this.time = Instant.ofEpochSecond(time);
        this.summary = summary;
        this.icon = icon;
        this.temperature = temperature;
    }

    public Instant getTime() {
        return time;
    }

    public String getSummary() {
        return summary;
    }

    public String getIcon() {
        return icon;
    }

    public double getTemperature() {
        return temperature;
    }
}
