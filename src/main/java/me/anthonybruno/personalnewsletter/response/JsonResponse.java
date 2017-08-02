package me.anthonybruno.personalnewsletter.response;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;

import java.io.IOException;

public class JsonResponse<T extends JsonNode> extends WebResponse {

    public JsonResponse(HttpResponse httpResponse) {
        super(httpResponse);
    }

    public T getJson() {
        try {
            return (T) new ObjectMapper().readTree(getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
