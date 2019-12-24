package dev.anthonybruno.personalnews;

import dev.anthonybruno.personalnews.ClassPathFile;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClassPathFileTest {

    @Test
    public void getFile() {
        File file = new ClassPathFile("test.properties").getFile();
        assertNotNull(file);
        assertTrue(file.exists());
        assertTrue(file.isFile());
    }
}
