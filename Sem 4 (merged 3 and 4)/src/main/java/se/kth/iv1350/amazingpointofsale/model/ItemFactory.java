package se.kth.iv1350.amazingpointofsale.model;

import se.kth.iv1350.amazingpointofsale.model.DTO.ItemDTO;

/**
 * @author rodbeh
 * 
 */

/**
 * Creates and returns an Item object based on the provided ItemDTO and quantitySold. 
 * 
 */
public interface ItemFactory {
    Item createItem(ItemDTO itemDTO, int quantitySold);
}
