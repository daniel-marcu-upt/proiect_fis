package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemService {
    private static final String APPLICATION_FOLDER = ".registration-example";
    private static final String USER_FOLDER = System.getProperty("user.home");
    public static final Path APPLICATION_HOME_PATH = Paths.get(USER_FOLDER, APPLICATION_FOLDER);

    private static Nitrite database;

    public static void initDatabase(){
        database = Nitrite.builder()
                .filePath(getPathToFile("registration-example.db").toFile())
                .openOrCreate("test", "test");

    }
    public static Nitrite getDatabase(){
        return database;
    }
    public static Path getPathToFile(String... path) {
        return APPLICATION_HOME_PATH.resolve(Paths.get(".", path));
    }
}
