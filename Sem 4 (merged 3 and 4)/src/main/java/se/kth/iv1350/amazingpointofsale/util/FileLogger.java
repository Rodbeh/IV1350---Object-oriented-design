package se.kth.iv1350.amazingpointofsale.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author rodbeh
 * 
 */

/**
 * This class represents a logger that prints messages to a file. 
 * 
 */
public class FileLogger implements Logger {
    private static FileLogger instance;
    private PrintWriter fileToLog;
    
    /**
     *  Creates a new instance and log file. 
     */
    private FileLogger() {
        try {
            fileToLog = new PrintWriter(new FileWriter("log.txt"), true);
        } catch(IOException ioexception) {
            System.out.println("An error occured while initializing the logger.");
            ioexception.printStackTrace();
        }
    }
    
    /**
     * Returns the singleton instance of the FileLogger class.
     * 
     * @return the singleton instance of FileLogger. 
     */
    public static FileLogger getInstance() {
        if (instance == null) {
            instance = new FileLogger();
        }
        return instance;
    }

    /**
     * Writes the specified message to the log file. 
     * 
     * @param message is the string that contains the message to be logged.
     */
    public void log(String message) {
        fileToLog.println(message);
    }

    /**
     * Prints and logs the thrown exception to a txt file.
     * 
     * @param exception is the exception to be logged. 
     */
    @Override
    public void log(Exception exception) {
        StringBuilder logBuilder = new StringBuilder();
        logBuilder.append(createTime());
        logBuilder.append(" An exception was thrown: ");
        logBuilder.append("<").append(exception.getMessage()).append(">");
        fileToLog.println(logBuilder);
        exception.printStackTrace(fileToLog);
        fileToLog.println("\n");
    }
    
    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
    
}
