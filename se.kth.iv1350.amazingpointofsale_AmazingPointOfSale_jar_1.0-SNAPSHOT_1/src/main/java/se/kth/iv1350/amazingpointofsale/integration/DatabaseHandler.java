package se.kth.iv1350.amazingpointofsale.integration;

import se.kth.iv1350.amazingpointofsale.model.DTO.ItemDTO;
import se.kth.iv1350.amazingpointofsale.model.DTO.SaleDTO;

/**
 * @author rodbeh
 * 
 */

/**
 *
 * Represent a "Controller" that is responsible for all communication between databases
 * and sends it to the Controller class.
 */
public class DatabaseHandler {
    private final ExternalInventorySystem externalInventorySystem;
    private final ExternalAccountingSystem externalAccountingSystem;
    private final MemberDatabase memberDatabase;
    private final Register register;
    private final Printer printer;
    
    /**
     * Creates a new instance of the DatabaseHandler.
     */
    public DatabaseHandler() {
        externalInventorySystem = new ExternalInventorySystem();
        externalAccountingSystem = new ExternalAccountingSystem();
        memberDatabase = new MemberDatabase();
        register = new Register();
        printer = new Printer();
    }
    
    /**
     * Retrieves the ItemDTO with the corresponding inputed itemIdentifier.
     * 
     * @param itemIdentifier is the EAN-code for every item.
     * @return ItemDTO from the External Inventory System with the corresponding itemIdentifier.
     */
    public ItemDTO getItem(String itemIdentifier) {
        return externalInventorySystem.getItem(itemIdentifier);
    }
    
    /**
     * Updates the sale log by logging the sale information
     * and increase the register amount.
     * 
     * @param saleDTO contains information about the sale.
     */
    public void updateSaleLog(SaleDTO saleDTO) {
        externalAccountingSystem.logSaleInfo(saleDTO);
        register.increaseRegisterAmount(saleDTO);
    }
    
    /**
     * Retrieves MemberDatabase.
     * 
     * @return The value of memberDatabase.
     */
    public MemberDatabase getMemberDatabase() {
        return memberDatabase;
    }
    
    /**
     * Prints the receipt of the current sale.
     * 
     * @param saleDTO contains information about the sale.
     */
    public void printReceipt(SaleDTO saleDTO) {
        printer.printReceipt(saleDTO);
    }
}