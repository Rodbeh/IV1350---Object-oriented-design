package se.kth.iv1350.amazingpointofsale.model.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import se.kth.iv1350.amazingpointofsale.model.Item;
import se.kth.iv1350.amazingpointofsale.model.Receipt;
import se.kth.iv1350.amazingpointofsale.model.Sale;

/**
 * @author rodbeh
 * 
 */

/**
 * 
 * DTO for a sale in a pos.
 */
public class SaleDTO {
    private final LocalDateTime saleTimeDate;
    private final ArrayList<Item> itemList;
    private final Receipt receipt;
    private final double runningTotal;
    private final double amountPaid;
    private final double VAT;
    
    /**
     * Creates a new instance.
     * 
     * @param sale is the current Sale object.
     */
    public SaleDTO(Sale sale) {
        this.saleTimeDate = sale.getSaleTimeDate();
        this.itemList = sale.getItemList();
        this.receipt = sale.getReceipt();
        this.runningTotal = sale.getRunningTotal();
        this.amountPaid = sale.getAmountPaid();
        this.VAT = sale.getVAT();
    }
    
    /**
     * 
     * @return the running total + VAT rate
     */
    //public double getTotal() {
    //    return this.runningTotal + this.VAT;
    //}
    
    /**
     * 
     * @return this running total without VAT
     */
    public double getTotalWithoutVAT() {
        return this.runningTotal - this.VAT;
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
        return itemList;
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
     * @return the running total.
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
     * 
     * @return the amount of VAT rate based on item. 
     */
    public double getVAT() {
        return VAT;
    }
 
}