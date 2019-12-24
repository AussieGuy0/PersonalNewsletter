package dev.anthonybruno.personalnews.connector.common;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Http {

    public static HttpClient getDefaultClient() {
         return HttpClient.newBuilder()
                 .connectTimeout(Duration.ofSeconds(4))
                 .build();
    }

    public static boolean responseIsOk(HttpResponse<?> response) {
        return response.statusCode() >= 200 && response.statusCode() < 300;
    }
}
