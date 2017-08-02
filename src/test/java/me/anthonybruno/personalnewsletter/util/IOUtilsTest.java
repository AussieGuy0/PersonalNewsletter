package me.anthonybruno.personalnewsletter.util;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class IOUtilsTest {

    @Test
    public void streamToString() {
        String originalString = "abc 123 \n \n !@@#$%A^A^A^";
        InputStream stream = new ByteArrayInputStream(originalString.getBytes());

        String fromStream = IOUtils.convertStreamToString(stream);

        assertEquals(originalString, fromStream);
    }
}
