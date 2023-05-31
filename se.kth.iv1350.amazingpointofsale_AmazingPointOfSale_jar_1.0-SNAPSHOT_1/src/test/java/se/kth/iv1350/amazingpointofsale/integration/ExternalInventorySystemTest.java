package se.kth.iv1350.amazingpointofsale.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.amazingpointofsale.model.DTO.ItemDTO;

/**
 *
 * @author rodbeh
 */
public class ExternalInventorySystemTest {
    
    private ExternalInventorySystem inventory;    
    
    @BeforeEach
    public void setUp() {
        inventory = new ExternalInventorySystem();
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testCheckIfItemInInventory() {
        ItemDTO expResult = new ItemDTO("7318690066903", "Ägg Frigående M 15-p ICA", 42.95, 0.12, 0);
        String itemIdentifier = "7318690066903";
        ItemDTO result = inventory.getItem(itemIdentifier);

        assertEquals(expResult.getItemIdentifier(), result.getItemIdentifier(), "Hittade inte det angivna varan korrekt.");
        assertTrue(result.getItemInformation().contains("Ägg Frigående M 15-p ICA"));
    }
    
    @Test
    public void testCheckIfItemPriceMatch() {
        double expResult = 21.50;
        double result = inventory.getItem("7311041060216").getPrice();
        assertEquals(expResult, result, "Den hittade priset på varan stämmer inte.");
    }
    
    @Test
    public void testCheckIfItemPriceDoesNotMatch() {
        double expResult = 0;
        double result = inventory.getItem("7311041060216").getPrice();
        assertNotEquals(expResult, result, "Den hittade priset på varan stämmer överens, vilket den inte borde göra.");
    }
    
    @Test
    public void testCheckIfItemNameMatch() {
        String expResult = "Strösocker 2kg Dansukker";
        String result = inventory.getItem("7310340002279").getItemInformation();
        assertEquals(expResult, result, "Den hittade namnet på varan stämmer inte.");
    }
    
    @Test
    public void testCheckIfItemVATRateMatch() {
        double expResult = 0.12;
        double result = inventory.getItem("7310865005168").getVAT();
        assertEquals(expResult, result, "Varans moms värde stämmer inte.");
    }

}
