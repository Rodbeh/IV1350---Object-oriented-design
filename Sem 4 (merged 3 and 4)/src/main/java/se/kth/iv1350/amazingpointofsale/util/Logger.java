package se.kth.iv1350.amazingpointofsale.util;

/**
 * @author rodbeh
 * 
 */

/**
 * This interface represents a contract for a log handler. It is
 * responsible for logging messages. 
 * 
 */
public interface Logger {
    
    /**
     * The message is printed to the log.
     * 
     * @param exception is the exception thrown. 
     */
    void log(Exception exception);
    
}
