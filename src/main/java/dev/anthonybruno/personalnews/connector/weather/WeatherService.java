package dev.anthonybruno.personalnews.connector.weather;

import dev.anthonybruno.personalnews.connector.weather.model.Location;
import dev.anthonybruno.personalnews.connector.weather.model.Forecast;
import dev.anthonybruno.personalnews.connector.weather.model.Weather;

public interface WeatherService {

    Weather getCurrentWeather(Location location);

    Forecast getWeekForecast(Location location);
}
