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
        inventory = null;
    }

    @Test
    public void testItemInInventory() {
        try {
            ItemDTO expResult = new ItemDTO("7318690066903", "Ägg Frigående M 15-p ICA", 42.95, 0.12, 0);
            String itemIdentifier = "7318690066903";
            ItemDTO result = inventory.getItem(itemIdentifier);

            assertEquals(expResult.getItemIdentifier(), result.getItemIdentifier(), "Hittade inte det angivna varan korrekt.");
            assertTrue(result.getItemInformation().contains("Ägg Frigående M 15-p ICA"));
        } catch (ItemIdentifierInvalidException exception) {
            fail("Ett exception kastades när det inte borde ha gjort det: " + exception.getMessage());
        }
    }
    
    @Test
    public void testItemNotInInventoryException() {
        String invalidItemIdentifier = "0000000000000";
        try {
            inventory.getItem(invalidItemIdentifier);
            fail("Förväntat ItemIdentifierInvalidException kastades inte.");
        } catch (ItemIdentifierInvalidException exception) {
            assertEquals("Varan med streckkoden " + invalidItemIdentifier + " är ogiltig!", exception.getMessage(),
                "Felaktigt undantagsmeddelande för ogiltig streckkod.");
        }
    }
    
    @Test
    public void testGetItemThrowsDatabaseConnectionFailureException() {
        String invalidItemIdentifier = "404";
        try {
            inventory.getItem(invalidItemIdentifier);
            fail("Förväntat DatabaseConnectionFailureException kastades inte.");
        } catch (DatabaseConnectionFailureException exception) {
            assertEquals("Databasen kunde inte nås!", exception.getMessage(),
                "Felaktigt undantagsmeddelande för streckkoden 404.");
        } catch (ItemIdentifierInvalidException exception) {
            fail("Ett oväntat undantag kastades: " + exception.getMessage());
        }
    }

    @Test
    public void testCheckIfItemPriceMatch() {
        double expResult = 21.50;
        double result = 0.0;
        try {
            result = inventory.getItem("7311041060216").getPrice();
        } catch (ItemIdentifierInvalidException exception) {
            fail("Ett oväntat undantag kastades: " + exception.getMessage());
        }
        assertEquals(expResult, result, "Det återfunna priset på varan matchar inte det förväntade priset.");
    }

    @Test
    public void testCheckIfItemPriceDoesNotMatch() {
        try {
            double expResult = 0;
            double result = inventory.getItem("7311041060216").getPrice();
            assertNotEquals(expResult, result, "Det återfunna priset på varan stämmer överens, vilket den inte borde göra.");
        } catch (ItemIdentifierInvalidException exception) {
            fail("Ett oväntat undantag kastades: " + exception.getMessage());
        }
    }

    @Test
    public void testCheckIfItemNameMatch() {
        String expResult = "Strösocker 2kg Dansukker";
        try {
            String result = inventory.getItem("7310340002279").getItemInformation();
            assertEquals(expResult, result, "Det återfunna namnet på varan stämmer inte.");
        } catch (ItemIdentifierInvalidException exception) {
            fail("Ett oväntat undantag kastades: " + exception.getMessage());
        }
    }

    @Test
    public void testCheckIfItemVATRateMatch() {
        try {
            double expResult = 0.12;
            double result = inventory.getItem("7310865005168").getVAT();
            assertEquals(expResult, result, "Varans moms värde stämmer inte.");
        } catch (ItemIdentifierInvalidException e) {
            fail("Ett oväntat ItemIdentifierInvalidException kastades: " + e.getMessage());
        } catch (DatabaseConnectionFailureException e) {
            fail("Ett oväntat DatabaseConnectionFailureException kastades: " + e.getMessage());
        }
    }
    
}
