package me.anthonybruno.personalnewsletter.response;

import me.anthonybruno.personalnewsletter.response.mock.HttpResponseMock;
import org.apache.http.HttpResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WebResponseTest {

    @Test
    public void getString() {
        String bodyText = "this is body text";
        WebResponse response = new WebResponse(generateResponse(bodyText));
        assertEquals(bodyText, response.getBodyString());
    }

    private HttpResponse generateResponse(String body) {
        return new HttpResponseMock(body);
    }
}
