package se.kth.iv1350.amazingpointofsale.controller;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.amazingpointofsale.integration.DatabaseHandler;
import se.kth.iv1350.amazingpointofsale.model.DTO.ItemDTO;
import se.kth.iv1350.amazingpointofsale.model.Item;
import se.kth.iv1350.amazingpointofsale.model.Receipt;
import se.kth.iv1350.amazingpointofsale.model.Sale;

/**
 * @author rodbeh
 * 
 */
public class ControllerTest {
    private Controller controller;
    private DatabaseHandler databaseHandler;
    
    @BeforeEach
    public void setUp() {
        databaseHandler = new DatabaseHandler();
        controller = new Controller(databaseHandler);
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testStartSale() {
        controller.startSale();
        Sale sale = controller.getSale();
        assertNotNull(sale);
    }

    @Test
    public void testScanItem() {
        controller.startSale();
        controller.scanItem("7310865000361", 2);
        Sale sale = controller.getSale();

        ArrayList<Item> itemList = sale.getItemList();
        assertEquals(1, itemList.size());
        Item item = itemList.get(0);
        assertEquals("7310865000361", item.getItemIdentifier());
        assertEquals("Mjölk 3% 1,5l Arla Ko", item.getItemInformation());
        assertEquals(20.95, item.getPrice());
        assertEquals(0.12, item.getVAT());
        assertEquals(2, item.getQuantitySold());
    }

    @Test
    public void testGetTotal() {
        controller.startSale();
        controller.scanItem("7310865000361", 2);
        double total = controller.getTotal();
        assertEquals(41.9, total);
    }
    
    @Test
    public void testGetCurrentItemList() {
        controller.startSale();
        controller.scanItem("7310865000361", 2);
        controller.scanItem("7318690066903", 1);

        ArrayList<ItemDTO> itemDTOList = controller.getCurrentItemList();

        assertEquals(2, itemDTOList.size());
    
        ItemDTO item1 = itemDTOList.get(0);
        assertEquals("7310865000361", item1.getItemIdentifier());
        assertEquals("Mjölk 3% 1,5l Arla Ko", item1.getItemInformation());
        assertEquals(20.95, item1.getPrice());
        assertEquals(0.12, item1.getVAT());
        assertEquals(2, item1.getQuantitySold());

        ItemDTO item2 = itemDTOList.get(1);
        assertEquals("7318690066903", item2.getItemIdentifier());
        assertEquals("Ägg Frigående M 15-p ICA", item2.getItemInformation());
        assertEquals(42.95, item2.getPrice());
        assertEquals(0.12, item2.getVAT());
        assertEquals(1, item2.getQuantitySold());
    }
    
    @Test
    public void testEndSale() {
        controller.startSale();
        controller.scanItem("7310865000361", 2);
        controller.scanItem("7318690066903", 1);
        controller.endSale(84.85);

        assertEquals(84.85, controller.getSale().getAmountPaid());
    }
    
    @Test
    public void testGenerateReceiptNotNull() {
        controller.startSale();
        controller.scanItem("7310865000361", 2);
        controller.scanItem("7318690066903", 1);
        Receipt receipt = controller.generateReceipt();

        assertNotNull(receipt);
    }
    
    @Test
    public void testGenerateReceiptSaleInformation() {
        controller.startSale();
        controller.scanItem("7310865000361", 2);
        controller.scanItem("7318690066903", 1);
        Receipt receipt = controller.generateReceipt();
        
        Sale sale = controller.getSale();
        assertEquals(sale, receipt.getSale());
    }
    
}
