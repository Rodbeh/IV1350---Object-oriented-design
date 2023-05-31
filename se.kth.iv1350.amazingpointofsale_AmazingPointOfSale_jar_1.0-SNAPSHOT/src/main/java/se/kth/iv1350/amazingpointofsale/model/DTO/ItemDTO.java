package se.kth.iv1350.amazingpointofsale.model.DTO;

/**
 * @author rodbeh
 * 
 */

/**
 * 
 * DTO for the items that are used in a sale.
 */
public class ItemDTO {
    private final String itemIdentifier;
    private final String itemInformation;
    private final double price;
    private final double VAT;
    private int quantitySold;
    
    /**
     * Constructor
     * 
     * @param itemIdentifier  identifies the item with a specified combination of numbers.
     * @param itemInformation is the information of the item.
     * @param price           is the price of the item.
     * @param VAT             is the VAT rate based on the item.
     * @param quantitySold    is the quantity that is sold of the item. 
     */
    public ItemDTO(String itemIdentifier, String itemInformation, double price, double VAT, int quantitySold) {
        this.itemIdentifier = itemIdentifier;
        this.itemInformation = itemInformation;
        this.price = price;
        this.VAT = VAT;
        this.quantitySold = quantitySold;    
    }
    
    /**
     * 
     * @return the specified itemIdentifier.
     */
    public String getItemIdentifier() {
        return itemIdentifier;
    }
    
    /**
     * 
     * @return the information of the item.
     */
    public String getItemInformation() {
        return itemInformation;
    }
    
    /**
     * 
     * @return the price of the item.
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * 
     * @return the VAT rate based on the item.
     */
    public double getVAT() {
        return VAT;
    }
    
    /**
     * 
     * @return the quantity sold of the item.
     */
    public int getQuantitySold() {
        return quantitySold;
    }
    
    /**
     * Sets the quantity sold of the item.
     * 
     * @param quantitySold the quantity sold to set
     */
    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }
    
}