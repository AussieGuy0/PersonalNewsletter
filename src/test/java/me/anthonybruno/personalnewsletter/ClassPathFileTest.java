package me.anthonybruno.personalnewsletter;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ClassPathFileTest {

    @Test
    public void getFile() {
        File file = new ClassPathFile("test.properties").getFile();
        assertNotNull(file);
        assertTrue(file.exists());
        assertTrue(file.isFile());
    }
}
