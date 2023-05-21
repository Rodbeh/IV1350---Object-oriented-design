package se.kth.iv1350.amazingpointofsale.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author rodbeh
 * 
 */

/**
 * Prints the log messages to the display. 
 * 
 */
public class ConsoleLogger implements Logger {
    
    /**
     * Prints the log messages to the display, instead of a file.
     * 
     * @param exception is the exception to be logged. 
     */
    @Override
    public void log(Exception exception) {  
        StringBuilder logBuilder = new StringBuilder();
        logBuilder.append("\n");
        logBuilder.append(createTime());
        logBuilder.append("\n");
        logBuilder.append(" An exception was thrown: ");
        logBuilder.append("<").append(exception.getMessage()).append(">");
        System.out.println(logBuilder);
        exception.printStackTrace(System.out);
    }
    
    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
    
}
