package me.anthonybruno.personalnewsletter.weather;

import com.fasterxml.jackson.databind.node.ObjectNode;
import me.anthonybruno.personalnewsletter.JsonResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class OpenWeatherApiService implements WeatherService {

    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&APPID={appId}&units=metric"; //TODO: Make units settable

    private CloseableHttpClient httpClient =  HttpClientBuilder.create().build();
    private final String apiKey;

    public OpenWeatherApiService(String apiKey) {
        this.apiKey = apiKey;
    }

    private String createUrl(double latitude, double longitude) {
        return API_URL.replace("{lat}", latitude + "").replace("{lon}", longitude + "").replace("{appId}", apiKey);
    }

    @Override
    public Weather getTodaysWeather(Location location) {
        Weather weather;
        HttpUriRequest request = new HttpGet(createUrl(location.getLatitude(), location.getLongitude()));
        try {
            try (CloseableHttpResponse r = httpClient.execute(request)) {
                JsonResponse<ObjectNode> response = new JsonResponse<>(r);
                ObjectNode node = response.getJson();
                int temp = node.get("main").get("temp").asInt();
                String type = node.get("weather").get(0).get("main").asText();
                weather = new Weather(temp, type);

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
    public List<Weather> getWeekForecast(Location location) { //TODO: This
        throw new NotImplementedException();
    }
}
