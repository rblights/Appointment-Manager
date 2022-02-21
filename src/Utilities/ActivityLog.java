package Utilities;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/** Records login activity. */
public class ActivityLog {

    private static Logger logger;
    private static String currentTime = DTFormatter.format.format(LocalDateTime.now().atZone(ZoneId.of("UTC")));

    /** Creates login_activity.txt. */
    public static void createLog() {
        logger = Logger.getLogger("login_activity.txt");

        try {
            FileHandler fileHandler = new FileHandler("login_activity.txt", true);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        ActivityLog.logger = logger;
    }

    public static String getCurrentTime() {
        return currentTime;
    }
}
