package dev.anthonybruno.personalnews.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import dev.anthonybruno.personalnews.util.IOUtils;
import org.junit.jupiter.api.Test;


public class IOUtilsTest {

    @Test
    public void streamToString() {
        String originalString = "abc 123 \n \n !@@#$%A^A^A^";
        InputStream stream = new ByteArrayInputStream(originalString.getBytes());

        String fromStream = IOUtils.convertStreamToString(stream);

        assertEquals(originalString, fromStream);
    }
}
