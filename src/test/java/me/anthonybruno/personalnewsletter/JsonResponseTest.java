package me.anthonybruno.personalnewsletter;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import me.anthonybruno.personalnewsletter.web.HttpResponseMock;
import org.apache.http.HttpResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JsonResponseTest {

    @Test
    public void getObjectNode() {
        JsonResponse<ObjectNode> response = new JsonResponse<>(generateResponse("{\"key\":\"value\"}"));
        ObjectNode objectNode = response.getJson();

        assertEquals("value", objectNode.get("key").asText());
    }

    @Test
    public void getArrayNode() {
        JsonResponse<ArrayNode> response = new JsonResponse<>(generateResponse("[1,2,3]"));
        ArrayNode arrayNode = response.getJson();

        assertEquals(1, arrayNode.get(0).asInt());
        assertEquals(2, arrayNode.get(1).asInt());
        assertEquals(3, arrayNode.get(2).asInt());
    }

    @Test(expected = ClassCastException.class) //TODO: Maybe have custom exception
    public void getArrayAsObject() {
        JsonResponse<ObjectNode> response = new JsonResponse<>(generateResponse("[1,2,3]"));
        ObjectNode node = response.getJson();
    }

    private HttpResponse generateResponse(String body) {
        return new HttpResponseMock(body);
    }
}
