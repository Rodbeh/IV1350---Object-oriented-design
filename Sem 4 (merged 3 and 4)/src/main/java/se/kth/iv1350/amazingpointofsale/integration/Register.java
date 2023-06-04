package se.kth.iv1350.amazingpointofsale.integration;

import se.kth.iv1350.amazingpointofsale.model.DTO.SaleDTO;

/**
 * @author rodbeh
 * 
 */

/**
 * Represents a cash register that keeps track of the total amount of money received from a sale. 
 * 
 */
public class Register {
    private double amount = 0;
    
    /**
     * Constructur that initializes a new instance of the class when called.
     */
    public Register() {   
    }
    
    /**
     * Increases the amount of money in the register by adding the running total of a sale. 
     * 
     * @param saleDTO represents the details of the sale.
     */
    public void increaseRegisterAmount(SaleDTO saleDTO) {
        this.amount += saleDTO.getRunningTotal();
        System.out.println("Totalt belopp i kassan är: " + this.amount + "kr");
    }
    
}
