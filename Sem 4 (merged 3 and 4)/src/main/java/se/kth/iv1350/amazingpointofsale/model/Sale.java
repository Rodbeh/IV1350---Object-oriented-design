package se.kth.iv1350.amazingpointofsale.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    private final List<SaleObserver> saleObservers = new ArrayList<>();
    private final ItemFactory itemFactory;
    
    /**
     * Creates a new instance and saves the time of the sale. 
     * @param itemFactory is used to create items for the sale. 
     */
    public Sale(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
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
     * @return returns the running total  + VAT rate
     */
    //public double getTotal() {
    //    return this.runningTotal + this.VAT;
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
     * Registers the sale of an item, updates the total and VAT and returns the
     * updated display.
     * 
     * @param itemDTO is the item to be registered for the sale.
     * @param quantitySold is the quantity of the item sold.
     * @return the updated display after registering the item sale. 
     */
    public String registerItemToSaleLog(ItemDTO itemDTO, int quantitySold) {
         Item item = itemFactory.createItem(itemDTO, quantitySold);
        
        if (itemHasBeenRegistered(itemDTO)) {
            increaseQuantity(itemDTO, quantitySold);
        } else {
            itemList.add(item);
            item.setQuantitySold(quantitySold);
        }
        
        updateRunningTotalAndVAT(itemDTO, quantitySold);
        
        return updateTheDisplay(itemDTO, quantitySold);
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
    
    /**
    * Returns a string with the description of the item and the running total of the sale.
    *
    * @param item the item being sold
    * @param quantity of the item being sold
    * @return a string with the description and running total
    */
    private String updateTheDisplay(ItemDTO item, int quantity) {
        String vara = String.format("Varubeskrivning: %s", item.getItemInformation());    
        String pris = String.format("Pris: %.2f kr", item.getPrice());
        String antal = String.format("Antal: %d", quantity);
        String totalSumma = String.format("Total summa: %.2f kr", runningTotal);
        return vara + "\n" + pris + "\n" + antal + "\n" + totalSumma + "\n";
    } 
    
    /**
     * This metod adds one or more sale observers to the list of sale observers
     * and then notifies all of them.
     * 
     * @param saleObserver is a list of objects that implement the SaleObserver interface.
     */
    public void addSaleObservers(List<SaleObserver> saleObserver) {
        this.saleObservers.addAll(saleObserver);
        notifyObservers();
    }
    
    private void notifyObservers() {
        for(SaleObserver obs : this.saleObservers) {
            obs.newTotalRevenue(getRunningTotal());
        }
    }
    
}
