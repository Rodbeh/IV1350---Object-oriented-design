package se.kth.iv1350.amazingpointofsale.model;

/**
 * @author rodbeh
 * 
 */

/**
 * This class implements the observer pattern, which keeps track
 * of updates to the total revenue. 
 */
public interface SaleObserver {
    
    /**
     * The method updates the observer.
     * 
     * @param total is the total revenue of the sale.
     */
    void newTotalRevenue(double total);
    
}
