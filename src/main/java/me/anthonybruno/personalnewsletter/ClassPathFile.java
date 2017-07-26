package me.anthonybruno.personalnewsletter;

import java.io.File;

public class ClassPathFile {

    private final String resourceName;

    public ClassPathFile(String resourceName) {
       this.resourceName = resourceName;
    }

    public File getFile() {
        return new File(getClassLoader().getResource(resourceName).getFile());
    }

    private ClassLoader getClassLoader() {
        return this.getClass().getClassLoader();
    }
}
