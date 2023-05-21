package se.kth.iv1350.amazingpointofsale.model;

import java.time.format.DateTimeFormatter;

/**
* @author rodbeh
* 
*/

/**
* This class represents a receipt. It serves to prove the payment of one sale. 
* 
*/
public class Receipt {
    private final Sale sale;

    /**
    * Creates a new instance
    * 
    * @param sale is the current expiring sale
    */
    public Receipt(Sale sale) {
        this.sale = sale;
    }
    
    /**
    * Implementation of the toString method for the Receipt
    * 
    * @return a formatted string representation of the receipt, which includes
    * information about the sale items, totals, payment and change. 
    */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=========================================================================\n");
        sb.append("                                KVITTO\n");
        sb.append("=========================================================================\n\n");
        sb.append("Datum och tid för försäljningen: ")
            .append(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(sale.getSaleTimeDate()))
            .append("\n\n");
        sb.append("VAROR:\n");
        sb.append("-------------------------------------------------------------------------\n");
        sb.append(String.format("%-35s%10s%15s\n", "Artikel", "Antal", "Pris"));
        sb.append("-------------------------------------------------------------------------\n");
        for (Item item : sale.getItemList()) {
          sb.append(item.getItemInformation())
            .append(" ".repeat(Math.max(0, 42 - item.getItemInformation().length())))
            .append(String.format("%3d", item.getQuantitySold()))
            .append(" ".repeat(Math.max(0, 10 - String.format("%3d", item.getQuantitySold()).length())))
            .append(String.format("%9.2f kr", item.getPrice())) 
            .append("\n");
        }
        sb.append("-------------------------------------------------------------------------\n");
        sb.append("\nSUMMOR:\n");
        sb.append("-------------------------------------------------------------------------\n");
        sb.append("Netto (exkl. moms):\t")
            .append(String.format("%.2f kr", sale.getTotalWithoutVAT()))
            .append("\n");
        sb.append("Moms:\t\t\t")
            .append(String.format("%.2f kr", sale.getVAT()))
            .append("\n");
        sb.append("Totalt (inkl. moms):\t")
            .append(String.format("%.2f kr", sale.getRunningTotal()))
            .append("\n");
        sb.append("-------------------------------------------------------------------------\n");
        sb.append("\nBETALNING:\n");
        sb.append("-------------------------------------------------------------------------\n");
        sb.append("Betalt:\t\t\t")
            .append(String.format("%.2f kr", sale.getAmountPaid()))
            .append("\n");
        sb.append("Växel:\t\t\t")
            .append(String.format("%.2f kr", sale.getChange()))
            .append("\n");
        sb.append("=========================================================================\n");
        sb.append("                    Tack för att du handlar hos oss!\n");
        sb.append("=========================================================================\n");
        return sb.toString();
    }

}
