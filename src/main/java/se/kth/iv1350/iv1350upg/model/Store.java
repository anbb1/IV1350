
package se.kth.iv1350.iv1350upg.model;

/**
 * Contains information about a store 
 * @author Anders
 */
public class Store {
    
    private final String name;
    private final Address address;
    
    Store(String name,Address address)
    {
        this.name = name;
        this.address=address;
    }
    
    public String getName()
    {
        return name;
    }
    
    public Address getAddress()
    {
        return address;
    }
    
}
