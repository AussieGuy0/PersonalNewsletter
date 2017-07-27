package me.anthonybruno.personalnewsletter.util;

import java.io.InputStream;
import java.util.Scanner;

public class IOUtils {

    public static String convertStreamToString(InputStream inputStream) {
        try (Scanner scan = new Scanner(inputStream)) {
            return scan.useDelimiter("\\A").hasNext() ? scan.next() : "";
        }
    }
}
