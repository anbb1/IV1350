
package se.kth.iv1350.iv1350upg.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class InventorySystemTest {
    
    public InventorySystemTest() {
    }
    InventorySystem testInv;
    @BeforeEach
    public void setUp() {
        testInv = new InventorySystem();
    }
    
    @AfterEach
    public void tearDown() {
        testInv=null;
    }

    @Test
    public void testFetchInventoryInfoApple() {
        int IDToFetch=1;
        ItemID testID= new ItemID(IDToFetch);
        ItemDTO testDTO=testInv.fetchInventoryInfo(testID);
        
        int expIDResult=IDToFetch;
        int IDresult= testDTO.getItemID().getID();
        String expNameResult = "Apple";
        String nameResult = testDTO.getName();
        double expPriceResult = 8;
        double priceResult = testDTO.getPrice().getAmount();
        int expVATaxResult = 6;
        int VATaxResult = testDTO.getVATax();
        String expItemDescResult = "Royalsmith";
        String itemDescResult =testDTO.getItemDescription();
        
        assertEquals(expIDResult,IDresult, "ID not same");
        assertEquals(expNameResult,nameResult,"name not same");
        assertEquals(expPriceResult,priceResult,"price not same");
        assertEquals(expVATaxResult,VATaxResult,"VATax not same");
        assertEquals(expItemDescResult,itemDescResult,"description not same");
    }
    @Test
    public void testFetchInventoryInfoCarrot() {
        int IDToFetch=3;
        ItemID testID= new ItemID(IDToFetch);
        ItemDTO testDTO=testInv.fetchInventoryInfo(testID);
        
        int expIDResult=IDToFetch;
        int IDresult= testDTO.getItemID().getID();
        String expNameResult = "Carrot";
        String nameResult = testDTO.getName();
        double expPriceResult = 40;
        double priceResult = testDTO.getPrice().getAmount();
        int expVATaxResult = 6;
        int VATaxResult = testDTO.getVATax();
        String expItemDescResult = "2kg";
        String itemDescResult =testDTO.getItemDescription();
        
        assertEquals(expIDResult,IDresult, "ID not same");
        assertEquals(expNameResult,nameResult,"name not same");
        assertEquals(expPriceResult,priceResult,"price not same");
        assertEquals(expVATaxResult,VATaxResult,"VATax not same");
        assertEquals(expItemDescResult,itemDescResult,"description not same");
    }
    
    @Test
    public void testIDNotInList()
    {
        int IDToFetch=100;
        ItemID testID= new ItemID(IDToFetch);
        ItemDTO testDTO=testInv.fetchInventoryInfo(testID);
        
        ItemDTO expResult=null;
        ItemDTO result=testDTO;
        assertEquals(expResult,result,"result is not null");
    }
    
    
}
