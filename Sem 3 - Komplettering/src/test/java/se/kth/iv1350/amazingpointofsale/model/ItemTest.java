package se.kth.iv1350.amazingpointofsale.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.amazingpointofsale.model.DTO.ItemDTO;

/**
 * @author rodbeh
 * 
 */
public class ItemTest {
    private Item item;
    
    public ItemTest() {
    }
    
    @BeforeEach
    public void setUp() {
        ItemDTO itemDTO = new ItemDTO("123456789", "Test Item", 10.0, 0.25, 0);
        int quantitySold = 2;
        item = new Item(itemDTO, quantitySold);
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testSetQuantitySold() {
        int newQuantitySold = 5;
        item.setQuantitySold(newQuantitySold);
        assertEquals(newQuantitySold, item.getQuantitySold(), "Quantity sold was not set correctly.");
    }
    
}
