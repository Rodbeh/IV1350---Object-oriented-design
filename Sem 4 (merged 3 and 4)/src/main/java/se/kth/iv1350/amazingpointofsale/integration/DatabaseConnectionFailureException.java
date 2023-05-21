package se.kth.iv1350.amazingpointofsale.integration;

/**
 * @author rodbeh
 * 
 */

/**
 * This class represents the exception of when the database cannot be reached.
 * 
 */
public class DatabaseConnectionFailureException extends RuntimeException {
    
    /**
     * Creates a new instance of the DatabaseConnectionFailureException.
     * 
     * @param message is the message that is used when storing the message that gets logged.
     */
    public DatabaseConnectionFailureException(String message) {
        super(message);
    }
    
}
