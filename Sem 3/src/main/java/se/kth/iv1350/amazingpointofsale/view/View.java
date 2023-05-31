package se.kth.iv1350.amazingpointofsale.view;

import java.util.ArrayList;
import se.kth.iv1350.amazingpointofsale.controller.Controller;
import se.kth.iv1350.amazingpointofsale.model.DTO.ItemDTO;

/**
 * This is a placeholder for the real view. It contains a hardcoded execution with calls to all
 * system operations in the controller. 
 */
public class View {
    private final Controller contr;
   
    /**
     * Creates a new instance, that uses the specified controller for all calls to other layers.
     * 
     * @param contr The controller to use for all calls to other layers. 
     */
    public View(Controller contr) {
        this.contr = contr;
    }
    
    /**
     * Performs a fake sale, by calling all system operations in the controller. 
     */
    public void runFakeExecution() {
        startSale();
        scanItem();
        //addDiscount();
        endSale();
    }
    
    private void startSale() {
        contr.startSale();
        System.out.println();
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("\t\t          Ny försäljning påbörjad");
        System.out.println("-------------------------------------------------------------------------");
    }

    private void scanItem() {
        contr.scanItem("7310865000361", 1);
        contr.scanItem("7318690066903", 3);
        contr.scanItem("7311041060216", 1);
        contr.scanItem("8715800002315", 1);
        contr.scanItem("7310340002279", 2);
        contr.scanItem("7310865005168", 3);   
        
        updateAndDisplayItems();
    }
    
    private void updateAndDisplayItems() {
        ArrayList<ItemDTO> currentItems = contr.getCurrentItemList();
       
        
        int totalQuantitySold = 0;
        double runningTotal = 0;
        for (ItemDTO item : currentItems) {
            totalQuantitySold += item.getQuantitySold();
            runningTotal += item.getPrice() * item.getQuantitySold();
            System.out.println(itemToString(item, runningTotal));
        }
        
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Totalt antal varor: " + totalQuantitySold);
    }
    
    private String itemToString(ItemDTO item, double runningTotal) {
        return "Varubeskrivning: " + item.getItemInformation() + "\n" +
                "Pris: " + String.format("%.2f kr", item.getPrice()) + "\n" +
                "Antal: " + item.getQuantitySold() + "\n" +
                "Total summa: " + String.format("%.2f kr", runningTotal) + "\n";
    }
    
    /*private void addDiscount() {
        boolean discountAdded = contr.CheckIfCustomerIsMember("8002151234");
        if(discountAdded) {
            System.out.println("Rabatt har lagts till.");
        } else {
            System.out.println("Rabatt kunde inte läggas till.");
        }
    }*/
    
    private void endSale() {
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("\t\t          Försäljning är avslutad");
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("Totalt: %-1.2fkr%n", contr.getTotal());
        System.out.println("Mottaget kontant: " + 500 + "kr");
        System.out.println("-------------------------------------------------------------------------");
        contr.endSale(500);
        contr.generateReceipt();
    }
    
}
