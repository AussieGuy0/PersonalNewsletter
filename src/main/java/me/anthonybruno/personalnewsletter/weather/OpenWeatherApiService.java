package me.anthonybruno.personalnewsletter.weather;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import me.anthonybruno.personalnewsletter.response.JsonResponse;
import me.anthonybruno.personalnewsletter.weather.model.Location;
import me.anthonybruno.personalnewsletter.weather.model.Weather;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OpenWeatherApiService implements WeatherService {

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private static final String CURRENT_WEATHER_URL = BASE_URL + "weather?lat={lat}&lon={lon}&APPID={appId}&units=metric"; //TODO: Make units settable
    private static final String FORECAST_URL = BASE_URL + "forecast?lat={lat}&lon={lon}&APPID={appId}&units=metric"; //TODO: Make units settable

    private CloseableHttpClient httpClient =  HttpClientBuilder.create().build();
    private final String apiKey;

    public OpenWeatherApiService(String apiKey) {
        this.apiKey = apiKey;
    }


    @Override
    public Weather getTodaysWeather(Location location) {
        Weather weather;
        HttpUriRequest request = new HttpGet(createUrl(CURRENT_WEATHER_URL, location.getLatitude(), location.getLongitude()));
        try {
            try (CloseableHttpResponse r = httpClient.execute(request)) {
                JsonResponse<ObjectNode> response = new JsonResponse<>(r);
                ObjectNode node = response.getJson();
                weather = convertResponseObjectToWeather(node);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return weather;
    }

    @Override
    public Weather getWeatherOnDate(Date date, Location location) { //TODO: This
        throw new NotImplementedException();
    }

    @Override
    public List<Weather> getWeekForecast(Location location) {
        List<Weather> list = new ArrayList<>();
        HttpUriRequest request = new HttpGet(createUrl(FORECAST_URL, location.getLatitude(), location.getLongitude()));
        try {
            try (CloseableHttpResponse r = httpClient.execute(request)) {
                JsonResponse<ObjectNode> response = new JsonResponse<>(r);
                ObjectNode node = response.getJson();
                ArrayNode weatherNodeList = (ArrayNode) node.get("list");

                weatherNodeList.forEach(o -> list.add(convertResponseObjectToWeather((ObjectNode) o))); //FIXME: Filter so only one weather per day
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
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

    private HttpGet createGetRequest(String url, Location location) {
       return new HttpGet(createUrl(url, location.getLatitude(), location.getLongitude()));
    }

}
