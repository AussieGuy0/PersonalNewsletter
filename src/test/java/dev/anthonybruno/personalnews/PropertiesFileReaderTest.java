package dev.anthonybruno.personalnews;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import dev.anthonybruno.personalnews.PropertiesFileReader;
import org.junit.jupiter.api.Test;


public class PropertiesFileReaderTest {

   private final File testPropertiesFile = new File("src/test/resources/test.properties");

   @Test()
   public void openNonExistentFile() {
      assertThrows(IllegalArgumentException.class, () -> new PropertiesFileReader(new File("WOAH")));
   }

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
