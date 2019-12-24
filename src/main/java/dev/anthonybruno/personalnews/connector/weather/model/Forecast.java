package dev.anthonybruno.personalnews.connector.weather.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Forecast {

    private final ZoneId timezone;
    private final List<WeatherAndDate> weatherOnDates = new ArrayList<>();

    public Forecast(ZoneId timezone) {
        this.timezone = timezone;
    }

    public void addWeather(LocalDate onDate, Weather weather) {
        weatherOnDates.add(new WeatherAndDate(onDate, weather));
    }

    public List<WeatherAndDate> getWeatherList() {
        return weatherOnDates;
    }

    public ZoneId getTimezone() {
        return timezone;
    }

    public static class WeatherAndDate {

        private final LocalDate onDate;
        private final Weather weather;

        private WeatherAndDate(LocalDate onDate, Weather weather) {
            this.onDate = onDate;
            this.weather = weather;
        }

        @Override
        public String toString() {
            return weather + "(" + onDate + ")";
        }

        public Weather getWeather() {
            return weather;
        }

        public LocalDate getDate() {
            return onDate;
        }
    }

}
