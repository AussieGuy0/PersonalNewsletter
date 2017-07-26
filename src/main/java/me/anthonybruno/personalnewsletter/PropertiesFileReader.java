package me.anthonybruno.personalnewsletter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileReader implements AutoCloseable {

    private final FileInputStream fileInputStream;
    private final Properties properties = new Properties();

    public PropertiesFileReader(File propertyFile) {
        try {
            fileInputStream = new FileInputStream(propertyFile);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
        try {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    @Override
    public void close() throws Exception {
        fileInputStream.close();
    }
}
