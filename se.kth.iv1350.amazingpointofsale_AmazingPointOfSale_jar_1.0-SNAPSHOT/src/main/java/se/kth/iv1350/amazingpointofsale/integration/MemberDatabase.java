package se.kth.iv1350.amazingpointofsale.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.amazingpointofsale.integration.DTO.CustomerDTO;

/**
 * @author rodbeh
 * 
 */

/**
 * The MemberDatabase class has a record of all the customers who are members of the store.
 * 
 */
public class MemberDatabase {
    private final List<CustomerDTO> members = new ArrayList<>();

    /**
     * Creates a new object with an initial member added. 
     */
    public MemberDatabase() {
        this.members.add(new CustomerDTO("Elisabeth Nilsson", "8002151234"));
    }
    
    /**
     * Checks to see if a customer is a member of the store.
     * 
     * @param personalNumber is the personal number of the customer to check
     * @return true if customer is a member, otherwise false.
     */
    public boolean checkMembership(String personalNumber) {
        for(CustomerDTO customer : this.members) {
            if(customer.getPersonalNumber().equals(personalNumber)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns a list of all the members in the store. 
     * 
     * @return a list of CustomerDTO objects that represent the members.
     */
    public List<CustomerDTO> getMembers() {
        return this.members;
    } 
    
}
