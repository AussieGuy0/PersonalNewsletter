package me.anthonybruno.personalnewsletter.response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import me.anthonybruno.personalnewsletter.response.mock.HttpResponseMock;

import java.net.http.HttpResponse;

public class JsonResponseTest {

    @Test
    public void getObjectNode() {
        HttpResponse<ObjectNode> response = generateJsonResponse("{\"key\":\"value\"}");
        ObjectNode objectNode = response.body();

        assertEquals("value", objectNode.get("key").asText());
    }

    @Test
    public void getArrayNode() {
        HttpResponse<ArrayNode> response = generateJsonResponse("[1,2,3]");
        ArrayNode arrayNode = response.body();

        assertEquals(1, arrayNode.get(0).asInt());
        assertEquals(2, arrayNode.get(1).asInt());
        assertEquals(3, arrayNode.get(2).asInt());
    }


    private <T> HttpResponse<T> generateJsonResponse(String json) {
        return new HttpResponseMock<>((T) JsonUtils.parseJson(json));
    }
}
