package se.kth.iv1350.amazingpointofsale.integration;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.amazingpointofsale.model.DTO.ItemDTO;
import se.kth.iv1350.amazingpointofsale.model.DTO.SaleDTO;
import se.kth.iv1350.amazingpointofsale.model.Sale;

/**
 * @author rodbeh
 * 
 */
public class DatabaseHandlerTest {
    private DatabaseHandler databaseHandler;
    
    @BeforeEach
    public void setUp() {
        databaseHandler = new DatabaseHandler();
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetItem() {
        String itemIdentifier = "7310865000361";
        ItemDTO itemDTO = databaseHandler.getItem(itemIdentifier);
        assertNotNull(itemDTO);
        assertEquals(itemIdentifier, itemDTO.getItemIdentifier());
    }
    
    @Test
    public void testUpdateSaleLog() {
        Register register = new Register();
        Sale sale = new Sale();
        SaleDTO saleDTO = new SaleDTO(sale);
    
        register.increaseRegisterAmount(saleDTO);
        double expectedAmount = saleDTO.getRunningTotal();
        double actualAmount = register.getAmount();
    
        assertEquals(expectedAmount, actualAmount, 0.01); // Assuming doubl
    }
    
}
