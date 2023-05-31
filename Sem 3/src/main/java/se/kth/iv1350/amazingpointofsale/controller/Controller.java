package se.kth.iv1350.amazingpointofsale.controller;

import java.util.ArrayList;
import se.kth.iv1350.amazingpointofsale.integration.DatabaseHandler;
import se.kth.iv1350.amazingpointofsale.model.DTO.ItemDTO;
import se.kth.iv1350.amazingpointofsale.model.Sale;
import se.kth.iv1350.amazingpointofsale.model.DTO.SaleDTO;
import se.kth.iv1350.amazingpointofsale.integration.MemberDatabase;
import se.kth.iv1350.amazingpointofsale.model.Item;
import se.kth.iv1350.amazingpointofsale.model.Receipt;

/**
 * @author rodbeh
 * 
 */

/**
 * This is the application's only controller. All calls to the model pass through this class. 
 */
public class Controller {
    private Sale sale;
    private final DatabaseHandler databaseHandler;
    private final MemberDatabase memberDB;
    
    /**
     * Start a new sale. This is the first method called in the application. 
     */
    public void startSale() {
       sale = new Sale();
    }
    
    /**
     * Retrieves the current sale object representing the current sale.
     * 
     * @return the sale object representing the current sale. 
     */
    public Sale getSale() {
        return sale;
    }
    
    /**
     * Scans an item with the specified item identifier and quantity, and registers
     * it in the current sale.
     * 
     * @param itemIdentifier is the unique item identifier of the item that gets scanned during the sale.
     * @param quantity is the quantity of the items that get scanned during the sale.
     * @return a string representation of the item and the quantity.
     */
    public void scanItem(String itemIdentifier, int quantity) {
        ItemDTO item = databaseHandler.getItem(itemIdentifier);
        sale.registerItemToSaleLog(item, quantity);
    }
    
    /**
     * Calculates and returns the running total of the current sale.
     * 
     * @return the running total of the sale.
     */
    public double getTotal() {
        return sale.getRunningTotal();
    }
    
    /**
     * Calculates and returns the change to the customer after the sale has been completed.
     * 
     * @return the amount of change that has to be given to the customer.
     */
    public double getChange() {
        return sale.getChange();
    }
    
    /**
     * Retrieves the current list of items in the sale and returns ItemDTO objects
     * representing the items with relevant information.
     * 
     * @return an ArrayList of ItemDTO objects representing the current items in the sale.
     */
    public ArrayList<ItemDTO> getCurrentItemList() {
        ArrayList<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item : sale.getItemList()) {
            itemDTOList.add(new ItemDTO(item.getItemIdentifier(), item.getItemInformation(), item.getPrice(), item.getVAT(), item.getQuantitySold()));
        }
        return itemDTOList;
    }
    
    /**
     * Adds a discount to the current sale if the provided personal number matches a membership in the member database. 
     * 
     * @param personalNumber is the customers personal number.
     * @return a boolean that represents whether a discount was added or not.
     */
    public boolean discountAppliedIfCustomerMember(String personalNumber) {
        if(memberDB.checkMembership(personalNumber)) {
            sale.discount();
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Ends the current sale by setting the amount paid by the customer, 
     * creating a sale data transfer, updates the sale log and prints the receipt. 
     * 
     * @param amountPaid is the amount paid by the customer.
     */
    public void endSale(double amountPaid) {
        sale.setAmountPaid(amountPaid);
        SaleDTO saleDTO = new SaleDTO(sale);
        databaseHandler.updateSaleLog(saleDTO);
        databaseHandler.printReceipt(saleDTO);
    }
    
    /**
     * Creates a receipt object based on the current sale and returns it.
     * 
     * @return the generated receipt object representing the current sale.
     */
    public Receipt generateReceipt() {
        Receipt receipt = new Receipt(sale);
        return receipt;
    }
    
    /**
     * Creates a new instance of the Controller class with the specified database handler.
     * 
     * @param databaseHandler is the database handler object that is going to be used by controller.
     */
    public Controller(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
        this.memberDB = databaseHandler.getMemberDatabase();
    }    
    
}