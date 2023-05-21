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
    
    private Item(String itemIdentifier, String itemInformation, double price, double VAT, int quantitySold) {
        this.itemIdentifier = itemIdentifier;
        this.itemInformation = itemInformation;
        this.price = price;
        this.VAT = VAT;
        this.quantitySold = quantitySold;
    }
    
    /**
     * Creates and returns an Item object based on the parameters.
     * 
     * @param itemIdentifier identifies the item with a specified combination of numbers.
     * @param itemInformation is the information of the item.
     * @param price is the price of the item.
     * @param VAT is the VAT rate based on the item.
     * @param quantitySold is the quantity that is sold of the item. 
     * @return the created Item object. 
     */
    public static Item createItem(String itemIdentifier, String itemInformation, double price, double VAT, int quantitySold) {
        return new Item(itemIdentifier, itemInformation, price, VAT, quantitySold);
    }
    
    /**
     * Converts the current Item object to an ItemDTO object. 
     * 
     * @return the ItemDTO representing the current Item object. 
     */
    public ItemDTO toDTO() {
        return new ItemDTO(itemIdentifier, itemInformation, price, VAT, quantitySold);
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
        return (price * quantitySold);
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

