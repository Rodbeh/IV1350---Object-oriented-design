package se.kth.iv1350.amazingpointofsale.view;

import se.kth.iv1350.amazingpointofsale.model.SaleObserver;

/**
 * @author rodbeh
 * 
 */

/**
 * This class implements the SaleObserver interface and provides the view 
 * for displaying the total revenue. 
 */
public class TotalRevenueView implements SaleObserver {
    private double totalRevenue;
    
    /**
     * Constructs a new TotalRevenueView instance.
     */
    public TotalRevenueView() { 
    }
    
    /**
     * Updates the total revenue with the new total provided and prints it to the display. 
     * 
     * @param total is the new total revenue to update with. 
     */
    @Override
    public void newTotalRevenue(double total) {
        this.totalRevenue += total;
        printTotalRevenue(this.totalRevenue);
    }
    
    private void printTotalRevenue(double amount) {
        System.out.println("-------------------------------INTÄKTSVY---------------------------------");
        System.out.printf("Totala intäkter: %-1.2fkr%n", amount);
        System.out.println("-------------------------------------------------------------------------");
    }
}
