package se.kth.iv1350.iv1350upg.model;

/**
 * Contains address information
 * @author Anders
 */
public class Address {
    private final String streetAddress;
    private final int postCode;
    private final String city;
    private final String country;
    /**
     *creates an immutable instance of address
     * 
     */
    Address(String streetAddress,int postCode,String city,String country)
    {
        this.streetAddress = streetAddress;
        this.postCode = postCode;
        this.city =city;
        this.country = country;
    }
    
    public String getStreetAddress()
    {
        return streetAddress;
    }
    
    public int getPostCode()
    {
        return postCode;
    }
    
    public String getCity()
    {
        return city;
    }
    
    public String getCountry()
    {
        return country;
    }
}
