package me.anthonybruno.personalnewsletter.connector.weather;

import me.anthonybruno.personalnewsletter.connector.weather.model.City;
import me.anthonybruno.personalnewsletter.connector.weather.model.Forecast;
import me.anthonybruno.personalnewsletter.connector.weather.model.Weather;

import java.time.LocalDate;

public interface WeatherService {

    Weather getCurrentWeather(City location);

    Weather getWeatherOnDate(LocalDate date, City location);

    Forecast getWeekForecast(City location);
}
