package se.kth.iv1350.amazingpointofsale.integration.DTO;

/**
 * @author rodbeh
 * 
 */

/**
 * DTO for a customer that is a member.
 */
public class CustomerDTO {
    private final String name;
    private final String personalNumber;
    
    /**
     * CustomerDTO is a constructor that initializes the object. 
     * 
     * @param name is the members full name.
     * @param personalNumber is to identify the customer.
     */
    public CustomerDTO(String name, String personalNumber) {
        this.name = name;
        this.personalNumber = personalNumber;
    }
    
    /**
     * 
     * @return the name of the customer.
     */
    public String getName() {
        return name;
    }
    
    /**
     * 
     * @return the personal number of the customer.
     */
    public String getPersonalNumber() {
        return personalNumber;
    }
    
}
