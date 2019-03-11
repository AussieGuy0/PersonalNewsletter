package me.anthonybruno.personalnewsletter.response;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static <T extends JsonNode> T parseJson(String json) {
        try {
            return (T) objectMapper.readTree(json);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static <T extends JsonNode> T parseJson(InputStream inputStream) {
        try {
            return (T) objectMapper.readTree(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
