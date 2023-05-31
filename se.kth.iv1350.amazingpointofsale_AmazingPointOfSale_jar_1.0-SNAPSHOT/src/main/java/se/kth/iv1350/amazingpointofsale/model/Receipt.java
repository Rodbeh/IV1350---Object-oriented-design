package se.kth.iv1350.amazingpointofsale.model;

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
     * Retrieves the current sale object.
     * 
     * @return the current sale object.
     */
    public Sale getSale() {
        return sale;
    }
    
}