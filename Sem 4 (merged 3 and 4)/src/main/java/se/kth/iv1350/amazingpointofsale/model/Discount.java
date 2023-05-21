package se.kth.iv1350.amazingpointofsale.model;

import java.util.List;

/**
 * @author rodbeh
 * 
 */

/**
 * This class handles all the discount requests. 
 */
public class Discount {
    private final List<Item> itemList;
    
    /**
     * Creates an instance of discount. 
     * 
     * @param itemList is the list of items that the customer has. 
     */
    public Discount(List<Item> itemList) {
        this.itemList = itemList;
    }
    
    /**
     * Calculates the total discount amount for all items in the current sale that
     * are eligible for the "buy 2, get 1 free" promotion.
     * 
     * @return the total discount amount for all eligible items in the sale.
     */
    public double calculateDiscountAmount1() {
        double discountAmount = 0;
        for(Item item : this.itemList) {
            if (item.getQuantitySold() >= 2) {
                discountAmount += item.getPrice();
            }
        }
        return discountAmount;
    }
    
    /**
     * Calculates the discount amount to be applied when the total price
     * of the items is more than or equal to the given amount.
     * 
     * @param totalRunningPrice the total price of the items. 
     * @return the amount to be subtracted from the total price as a discount.
     */
    public double calculateDiscountAmount2(double totalRunningPrice) {
        if(totalRunningPrice >= 100) 
            return totalRunningPrice * 0.1;
        return 0;       
    }  
    
}
