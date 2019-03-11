package me.anthonybruno.personalnewsletter.response;

import java.io.InputStream;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Flow;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonBodySubscriber<T extends JsonNode> implements HttpResponse.BodySubscriber<T> {

   private final HttpResponse.BodySubscriber<InputStream> wrappedSubscriber = HttpResponse.BodySubscribers.ofInputStream();

   @Override
   public CompletionStage<T> getBody() {
      return wrappedSubscriber.getBody().thenApply(JsonUtils::parseJson);
   }

   @Override
   public void onSubscribe(final Flow.Subscription subscription) {
      wrappedSubscriber.onSubscribe(subscription);
   }

   @Override
   public void onNext(final List<ByteBuffer> item) {
      wrappedSubscriber.onNext(item);
   }

   @Override
   public void onError(final Throwable throwable) {
      wrappedSubscriber.onError(throwable);
   }

   @Override
   public void onComplete() {
      wrappedSubscriber.onComplete();
   }
}
