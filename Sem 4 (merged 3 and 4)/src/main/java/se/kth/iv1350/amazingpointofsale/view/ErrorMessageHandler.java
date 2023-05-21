package se.kth.iv1350.amazingpointofsale.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author rodbeh
 * 
 */

/**
 * This class creates error messages.
 */
public class ErrorMessageHandler {
    
    /**
     * Displays the error message. 
     * 
     * @param message is the error message. 
     */
    void showErrorMessage(String message) {
        StringBuilder errorMessageBuilder = new StringBuilder();
        errorMessageBuilder.append("\n");
        errorMessageBuilder.append(createTime());
        errorMessageBuilder.append("\n");
        errorMessageBuilder.append("ERROR: ");
        errorMessageBuilder.append("\n");
        errorMessageBuilder.append(message);
        System.out.println(errorMessageBuilder);     
    }
    
    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
    
}
