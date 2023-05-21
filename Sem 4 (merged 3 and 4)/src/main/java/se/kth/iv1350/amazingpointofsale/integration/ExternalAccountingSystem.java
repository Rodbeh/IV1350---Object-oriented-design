package se.kth.iv1350.amazingpointofsale.integration;

import se.kth.iv1350.amazingpointofsale.model.DTO.SaleDTO;

/**
 * @author rodbeh
 * 
 */

/**
 * This class represents the accounting system. It is not implemented as
 * it is not required for the functionality of the POS. 
 */
public class ExternalAccountingSystem {
    
    /**
     * Initializes the accounting system and should only be called once. 
     */
    public ExternalAccountingSystem() { 
    }
    
    /**
     * Stores information about a sale. 
     * 
     * @param sale contains information about the items sold and total price, 
     * by printing a message to the console.
     */
    //public void storeSaleInfo(Sale sale) {
      //  System.out.println("Försäljningsinformation lagras.");
    //}
    
    /**
     * Updates the accounting system.
     * 
     * @param saleDTO is the information that should get stored in the accounting system. 
     */
    public void logSaleInfo(SaleDTO saleDTO) {
        System.out.println(">>>> Försäljningsinformation har uppdaterats i redovisningssystemet.");
    } 
    
}
