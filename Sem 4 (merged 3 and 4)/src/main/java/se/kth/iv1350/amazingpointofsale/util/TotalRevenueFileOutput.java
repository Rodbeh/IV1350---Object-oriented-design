package se.kth.iv1350.amazingpointofsale.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import se.kth.iv1350.amazingpointofsale.model.SaleObserver;

/**
 * @author rodbeh
 * 
 */

/**
 * Represents a class that logs the total revenue to a file.
 */
public class TotalRevenueFileOutput implements SaleObserver {
    private double totalRevenue;
    private PrintWriter fileToLog;
    
    /**
     * Creates an instance of TotalRevenueFileOutput and initializes the PrintWriter.
     */
    public TotalRevenueFileOutput() {
        try {
            fileToLog = new PrintWriter(new FileWriter("total_revenue.txt"), true);
        } catch(IOException ioexception) {
            System.out.println("An error occured while initializing the logger.");
            ioexception.printStackTrace();
        }
    }

    /**
     * The method updates the total revenue and logs it to the file using
     * the printToFile method.
     * 
     * @param amountPaid is the amount paid in the sale.
     */
    @Override
    public void newTotalRevenue(double amountPaid) {
        this.totalRevenue += amountPaid;
        printToFile(this.totalRevenue);
    }
    
    private void printToFile(double amount) {
        fileToLog.println("--------------------------------------------------------------------------");
        fileToLog.println("Tid och datum: " + createTime());
        fileToLog.printf("Totala intäkter sedan försäljningsprogrammet startade: %-1.2fkr%n", amount);
        fileToLog.println("--------------------------------------------------------------------------");
    }

    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
    
}
