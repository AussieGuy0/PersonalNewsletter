package me.anthonybruno.personalnewsletter.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Scanner;

public class IOUtils {

    public static String convertStreamToString(InputStream inputStream) {
        try {
            return new String(inputStream.readAllBytes(), Charset.forName("UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
