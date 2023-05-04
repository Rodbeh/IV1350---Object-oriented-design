package se.kth.iv1350.amazingpointofsale.integration;

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
    
    private final ItemDTO[] inventory; 
    
    /**
     * Creates a new instance.
     */
    public ExternalInventorySystem() {
        this.inventory = new ItemDTO[8];
        this.inventory[0] = new ItemDTO("7310865000361", "Mjölk 3% 1,5l Arla Ko", 20.95, 0.12, 0);
        this.inventory[1] = new ItemDTO("7318690066903", "Ägg Frigående M 15-p ICA", 42.95, 0.12, 0);
        this.inventory[2] = new ItemDTO("7311041060216", "Vetemjöl 2kg Kungsörnen", 21.50, 0.12, 0);
        this.inventory[3] = new ItemDTO("8715800002315", "Bordssalt Extra fint med jod 125g Jozo", 8.95, 0.12, 0);
        this.inventory[4] = new ItemDTO("7310340002279", "Strösocker 2kg Dansukker", 28.90, 0.12, 0);
        this.inventory[5] = new ItemDTO("7310865005168", "Smör Normalsaltat 82% 500g Svenskt smör", 60.90, 0.12, 0);
    }
    
    /**
     * Returns the item with the corresponding inputed itemIdentifier.
     * 
     * @param itemIdentifier is the EAN-code for every item.
     * @return the itemDTO with the corresponding itemIdentifier.
     */
    public ItemDTO getItem(String itemIdentifier) {
            for (ItemDTO itemDTO : inventory) {
                if (itemDTO.getItemIdentifier().equals(itemIdentifier)) {
                    return itemDTO;
            }
        }
        return null;
    } 
    
    /**
     * 
     * @return whole inventory.
     */
    public ItemDTO[] getInventory() {
        return inventory;
    }
}
