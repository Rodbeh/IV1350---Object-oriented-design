package se.kth.iv1350.amazingpointofsale.model;

import se.kth.iv1350.amazingpointofsale.model.DTO.ItemDTO;

/**
 * @author rodbeh
 * 
 */

/**
 * Represents an item in a pos. The fields store the item's item identifier, information, price,
 * VAT and quantity sold. 
 * 
 */
public class Item {
    private final String itemIdentifier;
    private final String itemInformation;
    private final double price;
    private final double VAT;
    private int quantitySold;
    
    /**
     * A constructor that takes an ItemDTO object and a quantitySold parameter.
     * 
     * @param item is the item. 
     * @param quantitySold is the quantity sold of an item.
     */
    public Item(ItemDTO item, int quantitySold) {
        this.itemIdentifier = item.getItemIdentifier();
        this.itemInformation = item.getItemInformation();
        this.price = item.getPrice();
        this.VAT = item.getVAT();
        this.quantitySold = item.getQuantitySold();
    }
    
    /**
     * 
     * @return the item identifier of the item object.
     */
    public String getItemIdentifier() {
        return itemIdentifier;
    }
    
    /**
     * 
     * @return the item information of the item object.
     */
    public String getItemInformation() {
        return itemInformation;
    }
    
    /**
     * 
     * @return the total price of the item object based on the quantity sold.
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * 
     * @return the VAT of the item object.
     */
    public double getVAT() {
        return VAT;
    }
    
    /**
     * 
     * @return the quantity sold of the item object.
     */
    public int getQuantitySold() {
        return quantitySold;
    }
    
    /**
     * This method sets the quantity sold of the item object. 
     * 
     * @param quantitySold of the item object.
     */
    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }
    
}