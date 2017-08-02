package me.anthonybruno.personalnewsletter.response.mock;

import org.apache.http.Header;
import org.apache.http.HttpEntity;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class HttpEntityMock implements HttpEntity {

    private final InputStream inputStream;

    public HttpEntityMock(String body) {
        inputStream = new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public boolean isChunked() {
        return false;
    }

    @Override
    public long getContentLength() {
        return 0;
    }

    @Override
    public Header getContentType() {
        return null;
    }

    @Override
    public Header getContentEncoding() {
        return null;
    }

    @Override
    public InputStream getContent() throws IOException, UnsupportedOperationException {
        return inputStream;
    }

    @Override
    public void writeTo(OutputStream outputStream) throws IOException {

    }

    @Override
    public boolean isStreaming() {
        return false;
    }

    @Override
    public void consumeContent() throws IOException {

    }
}
