package se.kth.iv1350.amazingpointofsale.integration;

import se.kth.iv1350.amazingpointofsale.model.DTO.SaleDTO;

/**
 * @author rodbeh
 * 
 */

/**
 * Represents a printer used for printing the stores receipts.
 * 
 */
public class Printer {
    
    /**
     * Constructur that initializes a new instance of the class when called.
     */
    public Printer() {  
    }
    
    /**
     * Prints a receipt for a sale.
     * 
     * @param saleDTO contains information about the sale, including the receipt to be printed. 
     */
    public void printReceipt(SaleDTO saleDTO) {
        System.out.println(">>>> Skriver ut kvitto");
        System.out.println(saleDTO.getReceipt());
    }
    
}
