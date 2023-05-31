package se.kth.iv1350.amazingpointofsale.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.amazingpointofsale.model.DTO.ItemDTO;

/**
 * @author rodbeh
 * 
 */

/**
 * This class represents the external inventory system for the application. It currently only contains a hard-coded
 * inventory that can not be extended.
 */
public class ExternalInventorySystem {
    private final List<ItemDTO> inventory = new ArrayList<>();
    
    /**
     * Creates a new instance.
     */
    ExternalInventorySystem() {
        inventory.add(new ItemDTO("7310865000361", "Mjölk 3% 1,5l Arla Ko", 20.95, 0.12, 0));
        inventory.add(new ItemDTO("7318690066903", "Ägg Frigående M 15-p ICA", 42.95, 0.12, 0));
        inventory.add(new ItemDTO("7311041060216", "Vetemjöl 2kg Kungsörnen", 21.50, 0.12, 0));
        inventory.add(new ItemDTO("8715800002315", "Bordssalt Extra fint med jod 125g Jozo", 8.95, 0.12, 0));
        inventory.add(new ItemDTO("7310340002279", "Strösocker 2kg Dansukker", 28.90, 0.12, 0));
        inventory.add(new ItemDTO("7310865005168", "Smör Normalsaltat 82% 500g Svenskt smör", 60.90, 0.12, 0));
    }
    
    /**
     * Returns the item with the corresponding inputed itemIdentifier.
     * 
     * @param itemIdentifier is the EAN-code for every item.
     * @return the itemDTO with the corresponding itemIdentifier.
     * @throws ItemIdentifierInvalidException when the item identifier is invalid (unchecked).
     * @throws DatabaseConnectionFailureException when the database cannot be reached (unchecked).
     */
    public ItemDTO getItem(String itemIdentifier) throws ItemIdentifierInvalidException, DatabaseConnectionFailureException {
        for (ItemDTO itemDTO : inventory) {
            if (itemDTO.getItemIdentifier().equals(itemIdentifier)) {
                return itemDTO;
            }
        }
        if (itemIdentifier.equals("404")) {
            throw new DatabaseConnectionFailureException("Databasen kunde inte nås!");
        }
        throw new ItemIdentifierInvalidException("Varan med streckkoden " + itemIdentifier + " är ogiltig!");
    }
    
    /**
     * @return whole inventory.
     */
    public List<ItemDTO> getInventory() {
        return inventory;
    }
    
}
