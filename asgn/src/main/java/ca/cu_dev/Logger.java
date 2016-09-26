package ca.cu_dev;

/**
 * Created by chris on 26-Sep-16.
 */
public class Logger {

    private static final String ERROR = "Error";
    private static final String INFO = "INFO";

    public void i(String message) {
        log(Logger.INFO, message);
    }

    private void log(String level, String message) {
        System.out.println(level + "\t" + message);
    }

    public void e(String message) {
        log(Logger.ERROR, message);
    }
}
