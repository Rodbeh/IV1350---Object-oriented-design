package se.kth.iv1350.amazingpointofsale.model;

import se.kth.iv1350.amazingpointofsale.model.DTO.ItemDTO;

/**
 * @author rodbeh
 * 
 */

/**
 * This class is an implementation of the ItemFactory interface that creates
 * Item objects based on ItemDTO and quantitySold.
 * 
 */
public class DefaultItemFactory implements ItemFactory {

    /**
     * 
     * Creates an Item object based on the provided ItemDTO and quantitySold. 
     * 
     * @param itemDTO is the ItemDTO that includes item information.
     * @param quantitySold is the quantity of items that get sold. 
     * @return the created Item object. 
     */
    @Override
    public Item createItem(ItemDTO itemDTO, int quantitySold) {
        return Item.createItem(itemDTO.getItemIdentifier(), itemDTO.getItemInformation(), itemDTO.getPrice(), itemDTO.getVAT(), quantitySold);
    }
    
}
