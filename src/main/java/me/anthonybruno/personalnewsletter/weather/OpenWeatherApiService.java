package me.anthonybruno.personalnewsletter.weather;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import me.anthonybruno.personalnewsletter.response.JsonBodyHandler;
import me.anthonybruno.personalnewsletter.weather.model.Location;
import me.anthonybruno.personalnewsletter.weather.model.Weather;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OpenWeatherApiService implements WeatherService {

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private static final String CURRENT_WEATHER_URL = BASE_URL + "weather?lat={lat}&lon={lon}&APPID={appId}&units=metric"; //TODO: Make units settable
    private static final String FORECAST_URL = BASE_URL + "forecast?lat={lat}&lon={lon}&APPID={appId}&units=metric"; //TODO: Make units settable

    private HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(4)).build();
    private final String apiKey;

    public OpenWeatherApiService(String apiKey) {
        this.apiKey = apiKey;
    }


    @Override
    public Weather getTodaysWeather(Location location) {
        HttpRequest request = createGetRequest(CURRENT_WEATHER_URL, location);
        try {
            HttpResponse<ObjectNode> response = httpClient.send(request, new JsonBodyHandler<>());
            return convertResponseObjectToWeather(response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Weather getWeatherOnDate(LocalDate date, Location location) { //TODO: This
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Weather> getWeekForecast(Location location) {
        List<Weather> list = new ArrayList<>();
        HttpRequest request = createGetRequest(FORECAST_URL, location);
        try {
                HttpResponse<ObjectNode> response = httpClient.send(request, new JsonBodyHandler<>());
                ObjectNode node = response.body();
                ArrayNode weatherNodeList = (ArrayNode) node.get("list");
                weatherNodeList.forEach(o -> list.add(convertResponseObjectToWeather((ObjectNode) o))); //FIXME: Filter so only one weather per day
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private Weather convertResponseObjectToWeather(ObjectNode node) {
        int temp = node.get("main").get("temp").asInt();
        String type = node.get("weather").get(0).get("main").asText();
        return new Weather(temp, type);
    }

    private String createUrl(String url, double latitude, double longitude) {
        return url.replace("{lat}", latitude + "").replace("{lon}", longitude + "").replace("{appId}", apiKey);
    }

    private HttpRequest createGetRequest(String url, Location location) {
        URI uri = URI.create(createUrl(url, location.getLatitude(), location.getLongitude()));
        return HttpRequest.newBuilder(uri).GET().build();
    }

}
