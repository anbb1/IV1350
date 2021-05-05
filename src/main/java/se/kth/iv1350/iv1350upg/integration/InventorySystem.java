package se.kth.iv1350.iv1350upg.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.iv1350upg.model.ItemWithQuantity;

/**
 * This class handles communications to the external Inventory system.
 * Contains code simulating the inventory database.
 * @author Anders
 */
public class InventorySystem 
{
    private ArrayList<ItemDTO> fakeDB;
    /**
     * creates an instance of the InventorysSystem class.
     * creates the simulated inventory database.
     */
    InventorySystem()
    {    
        fakeDB= new ArrayList<>();
        createFakeInventoryDB();
    }
    
    /**
     * Sends information that external systems use to identify an item.
     * @param itemToFetch is the information sent do external systems 
     * @return information about an identified item.
     */
    public ItemDTO fetchInventoryInfo(ItemID itemToFetch)
    {
        ItemDTO foundItem = fakeDBSearch(itemToFetch);
        ItemDTO newItem=null;
        if(foundItem!=null)
        {
            ItemID newItemID= new ItemID(foundItem.getItemID().getID());
            newItem = new ItemDTO(newItemID, foundItem.getName(), foundItem.getPrice(), foundItem.getVATax(), foundItem.getItemDescription());
        }
        return newItem;
    }
    /**
     * Send information about sold items to the inventory data base.
     * @param ItemList list of sold products
     */
    public void updateInventory(List<ItemWithQuantity> ItemList)
    {
        //stuff
    }
    
    
    private void createFakeInventoryDB()
    {
        ItemID idList[]={new ItemID(1),new ItemID(2),new ItemID(4),new ItemID(5),new ItemID(6),new ItemID(7),new ItemID(3),new ItemID(8)}; 
        String nameList[]={"Apple","Toy Boat","Taco","Water","Flower","Milk","Carrot","Dusseldorf"};
        Amount priceList[]={new Amount(8),new Amount(89),new Amount(24.50),new Amount(15),new Amount(20),new Amount(14.90),new Amount(40),new Amount(4000)};
        int VATaxList[]={6,25,6,6,25,6,6,12};
        String itemDescriptionList[]={"Royalsmith","Plastic","Hot&Chicken","50cl","Brown Lily","1.5%fat","2kg","5%"};
        
        for(int i=0;i<idList.length;i++)
        {
           ItemDTO itemForFakeDB=new ItemDTO(idList[i],nameList[i],priceList[i],VATaxList[i],itemDescriptionList[i]);
           fakeDB.add(itemForFakeDB);
        }
    }
    
    private ItemDTO fakeDBSearch(ItemID itemToFetch)
    {
        boolean matchFound=false;
        ItemDTO returnValue=null; 
        for(int i=0;i<fakeDB.size()&&matchFound==false;i++)
        {
            matchFound = fakeDBCheckItemID(itemToFetch,fakeDB.get(i));
            if(matchFound)
            {
                returnValue= fakeDB.get(i);
            }
        }
        return returnValue;
    }
    
    private boolean fakeDBCheckItemID(ItemID itemToFetch,ItemDTO itemInFakeDB)
    {
        boolean sameID= false;
        if (itemToFetch.getID()==itemInFakeDB.getItemID().getID())
        {
            sameID = true;
        }
        return sameID;
    }
    
    
}
