package dev.anthonybruno.personalnews.connector.weather.darksky;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DailyForecastResponse {

    private final String summary;
    private final String icon;
    private final List<DailyWeatherResponse> data;

    public DailyForecastResponse(@JsonProperty("summary") String summary,
                                 @JsonProperty("icon") String icon,
                                 @JsonProperty("data") List<DailyWeatherResponse> data) {
        this.summary = summary;
        this.icon = icon;
        this.data = data;
    }

    public String getSummary() {
        return summary;
    }

    public String getIcon() {
        return icon;
    }

    public List<DailyWeatherResponse> getData() {
        return data;
    }
}
