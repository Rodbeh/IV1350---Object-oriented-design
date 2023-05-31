package se.kth.iv1350.amazingpointofsale.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import se.kth.iv1350.amazingpointofsale.model.DTO.ItemDTO;

/**
 * @author rodbeh
 * 
 */

/**
 * One single sale made by one single customer and payed with one payment.
 */
public class Sale {
    private LocalDateTime saleTimeDate;
    private final Receipt receipt;
    private final ArrayList<Item> itemList = new ArrayList<>();
    private double runningTotal = 0;
    private double amountPaid = 0;
    private double VAT = 0;
    private Discount discount;
    private boolean discountApplied = false;
    private double totalDiscount = 0;
    
    /**
     * Creates a new instance and saves the time of the sale. 
     */
    public Sale() {
        setTimeAndDateOfSale();
        receipt = new Receipt(this);
    }
        
    /**
     * A private method that registers the date and time at the start of the sale. 
     */
    private void setTimeAndDateOfSale() {
        saleTimeDate = LocalDateTime.now();
    }

    /**
     * 
     * @return the time the sale started.
     */
    public LocalDateTime getSaleTimeDate() {
        return saleTimeDate;
    }

    /**
     * 
     * @return all the items in the sale.
     */
    public ArrayList<Item> getItemList() {
        return this.itemList;
    }

    /**
     * 
     * @return the current receipt.
     */
    public Receipt getReceipt() {
        return receipt;
    }

    /**
     * 
     * @return returns the running total  + VAT rate
     */
    //public double getTotal() {
      //  return this.runningTotal + this.VAT;
    //}
    
    /**
     * 
     * @return the running total without the VAT rate included.
     */
    public double getTotalWithoutVAT() {
        return this.runningTotal - this.VAT;  
    }
    
    /**
     * 
     * @return running total.
     */
    public double getRunningTotal() {
        return runningTotal;
    }
    
    /**
     * 
     * @return the amount that the customer paid.
     */
    public double getAmountPaid() {
       return amountPaid;
    }
    
    /**
     * Sets the amount that the customer pays.
     * 
     * @param amountPaid the amount that the customer pays.
     */
    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    /**
     * @return amount of VAT rate based on item.
     */
    public double getVAT() {
        return VAT;
    }
    
    /**
     * 
     * @return change to the customer.
     */
    public double getChange() {
        return getAmountPaid() - getRunningTotal();
    }
    
    /**
     * Registers the sale of an item and updates the total and VAT.
     * 
     * @param item is the registered item.
     * @param quantity is the quantity of the sold item. 
     */
    public void registerItemToSaleLog(ItemDTO item, int quantity) {
        if (itemHasBeenRegistered(item)) {
            increaseQuantity(item, quantity);
        } else {
            itemList.add(new Item(item, quantity));
            itemList.get(itemList.size() - 1).setQuantitySold(quantity);
        }
        
        updateRunningTotalAndVAT(item, quantity);  
    }
    
    /**
     * Checks if the given item is already in the list of sold items.
     * 
     * @param item is the item that is getting checked.
     * @return true if item has been registered in sale, otherwise false.
     */
    private boolean itemHasBeenRegistered(ItemDTO item) {
        for (Item soldItem : itemList) {
            if (soldItem.getItemIdentifier().equals(item.getItemIdentifier())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Adds the given quantity to the sold quantity of the item with the same identifier in the list of sold items.
     * 
     * @param item is the item that gets sold.
     * @param quantity is if the item is already in the sale.
     */
    private void increaseQuantity(ItemDTO item, int quantity) {
        for (Item soldItem : itemList) {
            if (soldItem.getItemIdentifier().equals(item.getItemIdentifier())) {
                int newQuantity = soldItem.getQuantitySold() + quantity;
                soldItem.setQuantitySold(newQuantity);
            }
        }
    }
    
    /**
     * Updates the running total and VAT of a sale by adding the price and VAT of the given item and quantity to their respective running totals.
     * 
     * @param item the item being sold.
     * @param quantity the quantity of the item being sold.
     */
    private void updateRunningTotalAndVAT(ItemDTO item, int quantity) {
        double price = item.getPrice() * quantity;
        runningTotal += price;
        VAT += price * item.getVAT();
    }
    
    /**
     * Applies a discount to a sale.
     */
    public void discount() {
        this.discountApplied = true;
        this.discount = new Discount(this.itemList);
        this.totalDiscount = discount.calculateDiscountAmount1() + discount.calculateDiscountAmount2(this.runningTotal);
    }
    
    /**
     * Applies a discount to a sale by modifying running total.
     */
    public void applyDiscount() {
        this.runningTotal -= this.totalDiscount;
        this.discountApplied = true;
    }
    
}