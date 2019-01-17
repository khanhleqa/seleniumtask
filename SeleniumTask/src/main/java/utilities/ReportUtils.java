package utilities;

import org.apache.log4j.Logger;
import org.testng.Reporter;

public class ReportUtils {

    public static final Logger LOGGER = Logger.getLogger(ReportUtils.class);

    public static void writeFailedLog(String message) {
        LOGGER.error(message);
        Reporter.log(message);
        System.err.println(message);
    }

    public static void writeInfoLog(String message) {
        System.out.println(message);
        if (LOGGER.isDebugEnabled()) LOGGER.debug(message);
        else LOGGER.info(message);
    }

    public static void writeBlockedLog(String message) {
        LOGGER.fatal(message);
    }

}
