package se.kth.iv1350.amazingpointofsale.integration;

import java.time.format.DateTimeFormatter;
import se.kth.iv1350.amazingpointofsale.model.DTO.SaleDTO;
import se.kth.iv1350.amazingpointofsale.model.Item;
import se.kth.iv1350.amazingpointofsale.model.Receipt;

/**
 * @author rodbeh
 * 
 */

/**
 * Represents a printer used for printing the stores receipts.
 * 
 */
public class Printer {
    
    /**
     * A constructor for the Printer class.
     */
    public Printer() {
        
    }
    
    /**
     * Prints a receipt for a sale.
     * 
     * @param saleDTO contains information about the sale, including the receipt to be printed. 
     */
    public void printReceipt(SaleDTO saleDTO) {
        System.out.println(">>>> Skriver ut kvitto");
        String receiptString = generateReceiptString(saleDTO.getReceipt());
        System.out.println(receiptString);
    }
    
    private String generateReceiptString(Receipt receipt) {
        StringBuilder sb = new StringBuilder();
        sb.append("=========================================================================\n");
        sb.append("                                KVITTO\n");
        sb.append("=========================================================================\n\n");
        sb.append("Datum och tid för försäljningen: ")
            .append(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(receipt.getSale().getSaleTimeDate()))
            .append("\n\n");
        sb.append("VAROR:\n");
        sb.append("-------------------------------------------------------------------------\n");
        sb.append(String.format("%-35s%10s%15s\n", "Artikel", "Antal", "Pris"));
        sb.append("-------------------------------------------------------------------------\n");
        for (Item item : receipt.getSale().getItemList()) {
          sb.append(item.getItemInformation())
            .append(" ".repeat(Math.max(0, 42 - item.getItemInformation().length())))
            .append(String.format("%3d", item.getQuantitySold()))
            .append(" ".repeat(Math.max(0, 10 - String.format("%3d", item.getQuantitySold()).length())))
            .append(String.format("%9.2f kr", item.getPrice() * item.getQuantitySold())) 
            .append("\n");
        }
        sb.append("-------------------------------------------------------------------------\n");
        sb.append("\nSUMMOR:\n");
        sb.append("-------------------------------------------------------------------------\n");
        sb.append("Netto (exkl. moms):\t")
            .append(String.format("%.2f kr", receipt.getSale().getTotalWithoutVAT()))
            .append("\n");
        sb.append("Moms:\t\t\t")
            .append(String.format("%.2f kr", receipt.getSale().getVAT()))
            .append("\n");
        sb.append("Totalt (inkl. moms):\t")
            .append(String.format("%.2f kr", receipt.getSale().getRunningTotal()))
            .append("\n");
        sb.append("-------------------------------------------------------------------------\n");
        sb.append("\nBETALNING:\n");
        sb.append("-------------------------------------------------------------------------\n");
        sb.append("Betalt:\t\t\t")
            .append(String.format("%.2f kr", receipt.getSale().getAmountPaid()))
            .append("\n");
        sb.append("Växel:\t\t\t")
            .append(String.format("%.2f kr", receipt.getSale().getChange()))
            .append("\n");
        sb.append("=========================================================================\n");
        sb.append("                    Tack för att du handlar hos oss!\n");
        sb.append("=========================================================================\n");
        return sb.toString();
    }
    
}
