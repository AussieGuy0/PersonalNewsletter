package dev.anthonybruno.personalnews.util;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

public class UrlBuilder {

    private final String baseUrl;
    private final Map<String, String> urlParams = new LinkedHashMap<>(); //Linked hashmap to ensure order

    public UrlBuilder(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setUrlParam(String key, String value) {
        urlParams.put(key, value);
    }

    public URI build() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(baseUrl).append("?");
        urlParams.forEach((key, value) -> {
            stringBuilder.append(key).append("=").append(value);
            stringBuilder.append("&");
        });
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return URI.create(stringBuilder.toString());
    }
}
