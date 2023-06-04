package se.kth.iv1350.amazingpointofsale.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.amazingpointofsale.model.DTO.ItemDTO;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author rodbeh
 */

public class SaleTest {
    private Sale sale;
    private ItemDTO fakeItem;
    private ItemDTO anotherFakeItem;

    @BeforeEach
    public void setUp() {
        sale = new Sale();
        fakeItem = new ItemDTO("1234", "item", 149.99, 0.10, 10);
        anotherFakeItem = new ItemDTO("4567", "anotherItem", 299.99, 0.20, 20);
        sale.registerItemToSaleLog(fakeItem, 1);
    }
    
    @AfterEach
    public void tearDown() {
        sale = null;
        fakeItem = null;
    }
    
    @Test
    public void testItemAlreadyScanned() {
        sale.registerItemToSaleLog(fakeItem, 5);
        int expResult = 6;
        int result = sale.getItemList().get(0).getQuantitySold();
        assertEquals(expResult, result, "Antalet uppdaterades inte korrekt.");
    }

    @Test
    public void testGetItemPrice() {
        double expResult = 149.99;
        double result = sale.getItemList().get(0).getPrice();
        assertEquals(expResult, result, "Priset på varan stämde inte överens.");
    }
    
    @Test
    public void testGetName() {
        String expResult = "item";
        String result = sale.getItemList().get(0).getItemInformation();
        assertEquals(expResult, result, "Namnet på varan stämde inte överens.");
    }
    
    @Test
    public void testGetItemIdentifier() {
        String expResult = "1234";
        String result = sale.getItemList().get(0).getItemIdentifier();
        assertEquals(expResult, result, "Streckkoden matchade inte.");  
    }
    
    @Test
    public void testGetItemVATRate() {
        double expResult = 0.10;
        double result = sale.getItemList().get(0).getVAT();
        assertEquals(expResult, result, "Varans moms värde stämde inte överens.");
    }
    
    @Test
    public void testRegisterItem() {
        sale.registerItemToSaleLog(anotherFakeItem, 4);
        int expResult = 2;
        int result = sale.getItemList().size();
        assertEquals(expResult, result, "Varan lades inte till i korgen.");
    }
    
}
