package dev.anthonybruno.personalnews.response;

import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonBodyHandler<T extends JsonNode> implements HttpResponse.BodyHandler<T> {


    @Override
    public HttpResponse.BodySubscriber<T> apply(final HttpResponse.ResponseInfo responseInfo) {
        return new JsonBodySubscriber<>();
    }
}
