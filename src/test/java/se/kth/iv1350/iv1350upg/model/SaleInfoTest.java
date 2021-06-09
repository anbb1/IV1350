
package se.kth.iv1350.iv1350upg.model;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.iv1350upg.integration.Amount;
import se.kth.iv1350.iv1350upg.integration.ExternalSystemIntegration;
import se.kth.iv1350.iv1350upg.integration.InventorySystemContactFailureException;
import se.kth.iv1350.iv1350upg.integration.ItemDTO;
import se.kth.iv1350.iv1350upg.integration.ItemID;
import se.kth.iv1350.iv1350upg.integration.NoItemFoundException;


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
        int addedElements=0;
        try {
            addedElements = listSetupOne();
        } catch (NoItemFoundException | InventorySystemContactFailureException ex) {
            Logger.getLogger(SaleInfoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int expResult = addedElements;
        int result = testSale.getItemList().size();
        assertEquals(expResult, result, "failure to add all items");
    }

    @Test
    public void testNullID()
    {
        ItemDTO expResult=null;
        ItemDTO result=null;
        try {
            result = testSale.addItem(null, 1);
        } catch (NoItemFoundException | InventorySystemContactFailureException ex) {
            
        }
        assertEquals(expResult,result,"is not null");
    }
    
    @Test
    public void testAddBadID()
    {
        int addedElements=0;
        try {
            addedElements = listSetupOne();
        } catch (NoItemFoundException | InventorySystemContactFailureException ex) {
        }
        ItemID itemID100 = new ItemID(100);
        
        try {
            testSale.addItem(itemID100, 1);
        } catch (NoItemFoundException | InventorySystemContactFailureException ex) {   
        }
        try {
            testSale.addItem(itemID100, 2);
        } catch (NoItemFoundException | InventorySystemContactFailureException ex) {
        }
        
        int expResult = addedElements;
        int result = testSale.getItemList().size();
        assertEquals(expResult, result, "added bad item");
    }
    
    @Test
    public void testAddBadQuantityItem()
    {
        ItemID itemID1 = new ItemID(1);
        
        try {
            testSale.addItem(itemID1, 0);
        } catch (NoItemFoundException | InventorySystemContactFailureException ex) {
        }

        int expResult = 0;
        int result = testSale.getItemList().size();
        
        assertEquals(expResult, result, "added zero quantity item");
        
        try {
            testSale.addItem(itemID1, -1);
        } catch (NoItemFoundException | InventorySystemContactFailureException ex) {
        }
        result = testSale.getItemList().size();
        
        assertEquals(expResult, result, "added negativ quantity item");   
    }
    
    @Test
    public void testIncreaseQuantity()
    {
        ItemID itemID2 = new ItemID(2);
        int expResult=6;
        
        try {
            testSale.addItem(itemID2, expResult-2);
        } catch (NoItemFoundException | InventorySystemContactFailureException ex) {
        }

        try {
            testSale.addItem(itemID2, 2);
        } catch (NoItemFoundException | InventorySystemContactFailureException ex) {
            
        }
        int result= testSale.getItemList().get(0).getQuantity();
        assertEquals(expResult, result, "failed to add quantity");
    }
    @Test
    public void testIncreaseInvalidQuantity()
    {
        ItemID itemID2 = new ItemID(2);
        int expResult=4;
        try {
            testSale.addItem(itemID2, expResult);
        } catch (NoItemFoundException | InventorySystemContactFailureException ex) {
           
        }
        
        try {
            testSale.addItem(itemID2, 0);
        } catch (NoItemFoundException | InventorySystemContactFailureException ex) {
            
        }
        int result= testSale.getItemList().get(0).getQuantity();
        assertEquals(expResult, result, "added a quantity when zero items should be added"); 
        
        try {
            testSale.addItem(itemID2, -1);
        } catch (NoItemFoundException | InventorySystemContactFailureException ex) {
           
        }
        result= testSale.getItemList().get(0).getQuantity();
        assertEquals(expResult, result, "added negativ quantity"); 
    }
    
    
    
    
    @Test
    public void testNrOfListElements()
    {
        try {
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
        } catch (NoItemFoundException | InventorySystemContactFailureException ex) {
        }
    }
    
    
    @Test
    public void testUpdatePriceTotals()
    {
        try {
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
        } catch (NoItemFoundException | InventorySystemContactFailureException ex) {
            
        }
        
    }
    
    @Test
    public void testUpdateVATaxTotals()
    {
        try {
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
        } catch (NoItemFoundException | InventorySystemContactFailureException ex) {
            
        }
    }
    
    
    private int listSetupOne() throws NoItemFoundException, InventorySystemContactFailureException
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
