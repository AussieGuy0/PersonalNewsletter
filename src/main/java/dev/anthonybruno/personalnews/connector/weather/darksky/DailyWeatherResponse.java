package dev.anthonybruno.personalnews.connector.weather.darksky;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class DailyWeatherResponse {

    private final Instant time;
    private final String summary;
    private final String icon;
    private final double temperatureHigh;

    @JsonCreator
    public DailyWeatherResponse(@JsonProperty("time") long time,
                                @JsonProperty("summary") String summary,
                                @JsonProperty("icon") String icon,
                                @JsonProperty("temperatureHigh") double temperatureHigh) {
        this.time = Instant.ofEpochSecond(time);
        this.summary = summary;
        this.icon = icon;
        this.temperatureHigh = temperatureHigh;
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

    public double getTemperatureHigh() {
        return temperatureHigh;
    }
}
