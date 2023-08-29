package org.example.util.log;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class FileLoggingConfigurer {
    public static void configure() {
        LogManager.getLogManager().reset();
        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(Level.INFO);

        try {
            FileHandler fileHandler = new FileHandler("logs/myapp.log");
            fileHandler.setLevel(Level.INFO);
            rootLogger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
