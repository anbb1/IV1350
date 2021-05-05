
package se.kth.iv1350.iv1350upg.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.iv1350upg.integration.Amount;
import se.kth.iv1350.iv1350upg.integration.ExternalSystemIntegration;
import se.kth.iv1350.iv1350upg.integration.ItemDTO;
import se.kth.iv1350.iv1350upg.integration.ItemID;


public class SaleInfoTest {
    
    public SaleInfoTest() {
    }
    
    private SaleInfo testSale;
    private ExternalSystemIntegration sysInt=new ExternalSystemIntegration();
    
    
    @BeforeEach
    public void setUp() {
        testSale=new SaleInfo(sysInt.getInventorySystem());
    }
    
    @AfterEach
    public void tearDown() {
        testSale=null;
    }
    
    
    @Test
    public void testAddItem() {
        int addedElements = listSetupOne();
        
        int expResult = addedElements;
        int result = testSale.getItemList().size();
        assertEquals(expResult, result, "failure to add all items");
    }

    @Test
    public void testNullID()
    {
        ItemDTO expResult=null;
        ItemDTO result = testSale.addItem(null, 1);
        assertEquals(expResult,result,"is not null");
    }
    
    @Test
    public void testAddBadID()
    {
        int addedElements = listSetupOne();
        ItemID itemID100 = new ItemID(100);
        
        testSale.addItem(itemID100, 1);
        testSale.addItem(itemID100, 2);
        
        int expResult = addedElements;
        int result = testSale.getItemList().size();
        assertEquals(expResult, result, "added bad item");
    }
    
    @Test
    public void testAddBadQuantityItem()
    {
        ItemID itemID1 = new ItemID(1);
        
        testSale.addItem(itemID1, 0);

        int expResult = 0;
        int result = testSale.getItemList().size();
        
        assertEquals(expResult, result, "added zero quantity item");
        
        testSale.addItem(itemID1, -1);
        result = testSale.getItemList().size();
        
        assertEquals(expResult, result, "added negativ quantity item");   
    }
    
    @Test
    public void testIncreaseQuantity()
    {
        ItemID itemID2 = new ItemID(2);
        int expResult=6;
        
        testSale.addItem(itemID2, expResult-2);

        testSale.addItem(itemID2, 2);
        int result= testSale.getItemList().get(0).getQuantity();
        assertEquals(expResult, result, "failed to add quantity");
    }
    @Test
    public void testIncreaseInvalidQuantity()
    {
        ItemID itemID2 = new ItemID(2);
        int expResult=4;
        testSale.addItem(itemID2, expResult);
        
        testSale.addItem(itemID2, 0);
        int result= testSale.getItemList().get(0).getQuantity();
        assertEquals(expResult, result, "added a quantity when zero items should be added"); 
        
        testSale.addItem(itemID2, -1);
        result= testSale.getItemList().get(0).getQuantity();
        assertEquals(expResult, result, "added negativ quantity"); 
    }
    
    
    
    
    @Test
    public void testNrOfListElements()
    {
        ItemID itemID1 = new ItemID(1);
        ItemID itemID2 = new ItemID(2);
        ItemID itemID3 = new ItemID(3);
        testSale.addItem(itemID1, 1);
        testSale.addItem(itemID2, 2);
        testSale.addItem(itemID3, 3);
        int expResult=3;
        int result=testSale.getItemList().size();
        assertEquals(expResult, result, "all items added correctly");  
        
        testSale.addItem(itemID1, 3);
        testSale.addItem(itemID2, 2);
        testSale.addItem(itemID3, 1);
        result=testSale.getItemList().size();
        assertEquals(expResult, result, "item already in list added as separate item"); 
    }
    
    
    @Test
    public void testUpdatePriceTotals()
    {
        listSetupOne();
        Amount StartPriceTotal=testSale.getItemPriceTotal();
        int quantity = 5;
        ItemID itemID5= new ItemID(5);
        ItemDTO addedItem=testSale.addItem(itemID5, quantity);
        
        double expResult=(double) StartPriceTotal.getAmount() + (quantity * addedItem.getPrice().getAmount());
        double result = testSale.getItemPriceTotal().getAmount();
        assertEquals(expResult, result, "Item value not updated correctly");
        
        testSale.addItem(itemID5, quantity);
        expResult=(double) StartPriceTotal.getAmount() + (2*quantity * addedItem.getPrice().getAmount());
        result = testSale.getItemPriceTotal().getAmount();
        assertEquals(expResult, result, "Item value not updated correctly");
        
    }
    
    @Test
    public void testUpdateVATaxTotals()
    {
        listSetupOne();
        Amount StartVATaxTotal=testSale.getItemVATaxTotal();
        int quantity = 7;
        ItemID itemID5= new ItemID(7);
        ItemDTO addedItem=testSale.addItem(itemID5, quantity);
        
        double expResult=(double) StartVATaxTotal.getAmount() + ((double)quantity * addedItem.getPrice().getAmount()*(double)addedItem.getVATax()/100);
        double result = testSale.getItemVATaxTotal().getAmount();
        assertEquals(expResult, result, "Item tax value not updated correctly");
        
        testSale.addItem(itemID5, quantity);
        expResult=(double) StartVATaxTotal.getAmount() + (2*(double)quantity * addedItem.getPrice().getAmount()*(double)addedItem.getVATax()/100);
        result = testSale.getItemVATaxTotal().getAmount();
        assertEquals(expResult, result, "Item tax value not updated correctly");
    }
    
    
    private int listSetupOne()
    {
        ItemID itemID1 = new ItemID(1);
        ItemID itemID3 = new ItemID(3);
        int quantity1 = 1,quantity3 = 3;
        
        testSale.addItem(itemID1, quantity1);
        testSale.addItem(itemID3, quantity3);
        testSale.addItem(itemID1, quantity3);
        testSale.addItem(itemID3, quantity1);
        
        int elementsAdded=2;
        return elementsAdded;
    }
}
