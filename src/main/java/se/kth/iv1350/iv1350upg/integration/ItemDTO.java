package se.kth.iv1350.iv1350upg.integration;

/**
 *  This class models an item sold in the store. Immutable
 * @author Anders
 */
public class ItemDTO {
    
    private final ItemID itemID;
    private final String name;
    private final Amount price;
    private final int VATax;
    private final String itemDescription;
    
    public ItemDTO(ItemID itemID,String name,Amount price,int VATax,String itemDescription)
    {
        this.itemID=itemID;
        this.name=name;
        this.price=price;
        this.VATax=VATax;
        this.itemDescription=itemDescription;   
    }
    
    public ItemID getItemID()
    {
        return itemID;
    }
    
    public String getName()
    {
        return name;
    }
    
    public Amount getPrice()
    {
        return price;
    }
    
    public int getVATax()
    {
        return VATax;
    }
    
    public String getItemDescription()
    {
        return itemDescription;
    }
}
