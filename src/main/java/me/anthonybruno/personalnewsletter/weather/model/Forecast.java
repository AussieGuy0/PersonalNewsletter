package me.anthonybruno.personalnewsletter.weather.model;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class Forecast {

    private final List<WeatherAndDate> weatherOnDates = new ArrayList<>();

    public Forecast() {
    }

    public void addWeather(ZonedDateTime dateTime, Weather weather) {
        weatherOnDates.add(new WeatherAndDate(weather, dateTime));
    }

    private static class WeatherAndDate {

        private final Weather weather;
        private final ZonedDateTime date;

        private WeatherAndDate(Weather weather, ZonedDateTime date) {
            this.weather = weather;
            this.date = date;
        }

        @Override
        public String toString() {
            return  weather + "(" + date + ")";
        }
    }

}
