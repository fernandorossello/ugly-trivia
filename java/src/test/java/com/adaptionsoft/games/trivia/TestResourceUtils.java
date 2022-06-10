package com.adaptionsoft.games.trivia;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestResourceUtils {

    public static String readFile(String filePath) throws IOException {
        final URL resource = TestResourceUtils.class.getClassLoader().getResource(filePath);
        return Files.readString(Path.of(resource.getPath()));
    }

}
