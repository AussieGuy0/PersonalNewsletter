package me.anthonybruno.personalnewsletter;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PropertiesFileReaderTest {

    private final File testPropertiesFile = new File("src/test/resources/test.properties");

    @Test
    public void readOneValue() {
        PropertiesFileReader propertiesFileReader = new PropertiesFileReader(testPropertiesFile);
        String value = propertiesFileReader.getProperty("key1");
        assertEquals("value1", value);
    }

    @Test
    public void readTwoValues() {
        PropertiesFileReader propertiesFileReader = new PropertiesFileReader(testPropertiesFile);
        String value = propertiesFileReader.getProperty("key1");
        assertEquals("value1", value);
        String value2 = propertiesFileReader.getProperty("key2");
        assertEquals("value2", value2);
    }

    @Test
    public void readNonExistentValue() {
        PropertiesFileReader propertiesFileReader = new PropertiesFileReader(testPropertiesFile);
        assertNull(propertiesFileReader.getProperty("not-real!"));

    }

}
