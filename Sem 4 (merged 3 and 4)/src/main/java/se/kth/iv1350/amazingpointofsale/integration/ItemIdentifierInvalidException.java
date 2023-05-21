package se.kth.iv1350.amazingpointofsale.integration;

/**
 * @author rodbeh
 * 
 */

/**
 * This class defines an exception called ItemIdentifierInvalidException that gets 
 * thrown when an item identifier is not found.  
 * 
 */
public class ItemIdentifierInvalidException extends Exception {
    
    /**
     * Creates a new instance of ItemIdentifierInvalidException.
     * 
     * @param message is for storing the exception message that gets logged. 
     */
    public ItemIdentifierInvalidException(String message) {
        super(message);
    }
    
}
