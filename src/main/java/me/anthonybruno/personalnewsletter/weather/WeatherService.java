package me.anthonybruno.personalnewsletter.weather;

import java.util.Date;
import java.util.List;

public interface WeatherService {

    Weather getTodaysWeather(Location location);

    Weather getWeatherOnDate(Date date, Location location);

    List<Weather> getWeekForecast(Location location);
}
