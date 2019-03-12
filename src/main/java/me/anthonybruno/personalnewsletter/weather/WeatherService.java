package me.anthonybruno.personalnewsletter.weather;

import me.anthonybruno.personalnewsletter.weather.model.Forecast;
import me.anthonybruno.personalnewsletter.weather.model.Location;
import me.anthonybruno.personalnewsletter.weather.model.Weather;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface WeatherService {

    Weather getTodaysWeather(Location location);

    Weather getWeatherOnDate(LocalDate date, Location location);

    Forecast getWeekForecast(Location location);
}
