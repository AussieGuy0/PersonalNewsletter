package dev.anthonybruno.personalnews.connector.weather.darksky;

import com.fasterxml.jackson.databind.node.ObjectNode;
import dev.anthonybruno.personalnews.connector.common.Http;
import dev.anthonybruno.personalnews.connector.weather.WeatherService;
import dev.anthonybruno.personalnews.connector.weather.model.Location;
import dev.anthonybruno.personalnews.connector.weather.model.Forecast;
import dev.anthonybruno.personalnews.connector.weather.model.Temperature;
import dev.anthonybruno.personalnews.connector.weather.model.Weather;
import dev.anthonybruno.personalnews.response.JsonBodyHandler;
import dev.anthonybruno.personalnews.response.JsonUtils;
import dev.anthonybruno.personalnews.util.UrlBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.ZonedDateTime;

public class DarkSkyWeatherService implements WeatherService {

    private final static String baseUrl = "https://api.darksky.net/";
    private final static String forecastUrl = baseUrl + "forecast/";

    private final String apiKey;
    private final HttpClient httpClient = Http.getDefaultClient();

    public DarkSkyWeatherService(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public Weather getCurrentWeather(Location location) {
        ForecastResponse forecastResponse = makeRequest(location);
        WeatherResponse current = forecastResponse.getCurrently();
        return new Weather(Temperature.ofCelsius(current.getTemperature()), current.getSummary());
    }

    @Override
    public Forecast getWeekForecast(Location location) {
        ForecastResponse forecastResponse = makeRequest(location);
        Forecast forecast = new Forecast(forecastResponse.getTimezone());
        for (DailyWeatherResponse dailyWeather : forecastResponse.getDaily().getData()) {
            ZonedDateTime time = ZonedDateTime.ofInstant(dailyWeather.getTime(), forecast.getTimezone());
            forecast.addWeather(time.toLocalDate(), responseToWeather(dailyWeather));
        }
        return forecast;
    }

    private Weather responseToWeather(DailyWeatherResponse response) {
        return new Weather(Temperature.ofCelsius(response.getTemperatureHigh()), response.getSummary());
    }

    private ForecastResponse makeRequest(Location location) {
        try {
            HttpResponse<ObjectNode> response = httpClient.send(createRequest(location), new JsonBodyHandler<>());
            //TODO: Check if response is valid
            return JsonUtils.readJson(response.body(), ForecastResponse.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpRequest createRequest(Location location) {
        URI uri = createForecastUrl(location);
        return HttpRequest.newBuilder(uri).GET().build();
    }

    private URI createForecastUrl(Location location) {
        UrlBuilder builder = new UrlBuilder(forecastUrl + apiKey + "/" + location.getLatitude() + "," + location.getLongitude());
        builder.setUrlParam("units", "si");
        return builder.build();
    }

}
