package me.anthonybruno.personalnewsletter;

import me.anthonybruno.personalnewsletter.util.IOUtils;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.io.InputStream;

public class WebResponse {

    private final HttpResponse httpResponse;

    public WebResponse(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    public String getBodyString() {
        return IOUtils.convertStreamToString(getInputStream());
    }

    public InputStream getInputStream() {
        try {
            return httpResponse.getEntity().getContent();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
