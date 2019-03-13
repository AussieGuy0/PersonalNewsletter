package me.anthonybruno.personalnewsletter.connector.weather.openweather;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import me.anthonybruno.personalnewsletter.response.JsonBodyHandler;
import me.anthonybruno.personalnewsletter.util.UrlBuilder;
import me.anthonybruno.personalnewsletter.connector.weather.WeatherService;
import me.anthonybruno.personalnewsletter.connector.weather.model.Forecast;
import me.anthonybruno.personalnewsletter.connector.weather.model.City;
import me.anthonybruno.personalnewsletter.connector.weather.model.Weather;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.*;
import java.util.HashSet;
import java.util.Set;

public class OpenWeatherApiService implements WeatherService {

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private static final String CURRENT_WEATHER_URL = BASE_URL + "weather";
    private static final String FORECAST_URL = BASE_URL + "forecast";

    private HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(4)).build();
    private final String apiKey;

    public OpenWeatherApiService(String apiKey) {
        this.apiKey = apiKey;
    }


    @Override
    public Weather getCurrentWeather(City location) {
        HttpRequest request = createGetRequest(CURRENT_WEATHER_URL, location);
        try {
            HttpResponse<ObjectNode> response = httpClient.send(request, new JsonBodyHandler<>());
            return convertResponseObjectToWeather(response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Weather getWeatherOnDate(LocalDate date, City location) { //TODO: This
        throw new UnsupportedOperationException();
    }

    @Override
    public Forecast getWeekForecast(City location) {
        Forecast forecast = new Forecast();
        HttpRequest request = createGetRequest(FORECAST_URL, location);
        try {
            HttpResponse<ObjectNode> response = httpClient.send(request, new JsonBodyHandler<>());
            ObjectNode node = response.body();
            ArrayNode weatherNodeList = (ArrayNode) node.get("list");
            Set<Integer> daysFound = new HashSet<>();
            weatherNodeList.forEach(o -> {
                ObjectNode weatherJson = (ObjectNode) o;
                Instant instant = Instant.ofEpochSecond(o.get("dt").asLong());
                ZonedDateTime date = ZonedDateTime.ofInstant(instant, location.getTimeZone());
                if (!daysFound.contains(date.getDayOfMonth()) && date.getHour() > 9) {
                    forecast.addWeather(date, convertResponseObjectToWeather(weatherJson));
                    daysFound.add(date.getDayOfMonth());
                }
            });
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return forecast;
    }


    private Weather convertResponseObjectToWeather(ObjectNode node) {
        int temp = node.get("main").get("temp").asInt();
        String type = node.get("weather").get(0).get("main").asText();
        return new Weather(temp, type);
    }

    private URI createUrl(String url, City location) {
        UrlBuilder urlBuilder = new UrlBuilder(url);
        if (location.getName() != null && location.getCountryCode() != null) {
            urlBuilder.setUrlParam("q", location.getName() + "," + location.getCountryCode());
        } else {
            urlBuilder.setUrlParam("lat", String.valueOf(location.getLatitude()));
            urlBuilder.setUrlParam("lon", String.valueOf(location.getLongitude()));
        }
        urlBuilder.setUrlParam("APPID", apiKey);
        urlBuilder.setUrlParam("units", "metric");
        return urlBuilder.build();
    }

    private HttpRequest createGetRequest(String url, City location) {
        URI uri = createUrl(url, location);
        return HttpRequest.newBuilder(uri).GET().build();
    }

}