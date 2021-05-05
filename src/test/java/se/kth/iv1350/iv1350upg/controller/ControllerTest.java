
package se.kth.iv1350.iv1350upg.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import se.kth.iv1350.iv1350upg.integration.Amount;
import se.kth.iv1350.iv1350upg.integration.ItemDTO;
import se.kth.iv1350.iv1350upg.integration.ItemID;


public class ControllerTest {
    
    public ControllerTest() {
    }
    
    private Controller ctrl;
    private final ItemID[] id={new ItemID(1),new ItemID(2),new ItemID(3),new ItemID(4),new ItemID(5),new ItemID(6),new ItemID(7)};
    
    
    @BeforeEach
    public void setUp() {
        ctrl = new Controller();
        
    }
    
    @AfterEach
    public void tearDown() {
        ctrl=null;
        
    }

    @Test
    public void testStartNewSale() {
        ctrl.addItemSingle(id[1]);
        Amount resultBefore = ctrl.updateRunningTotal();
        ctrl.startNewSale();
        Amount resultAfter = ctrl.updateRunningTotal();
        
        assertNotEquals(resultBefore.getAmount(),resultAfter.getAmount(),"result read from old sale");
        assertEquals(0,resultAfter.getAmount(),"new sale value not correct");
    }
    

    @Test
    public void testAddNullItem() {
        
        ItemID itemID = null;
        int quantity = 2;
        ItemDTO expResult = null;
        ItemDTO result = ctrl.addItem(itemID, quantity);
        assertEquals(expResult, result,"return != null ");
        assertEquals(0,ctrl.updateRunningTotal().getAmount(),"some sale info was edited");
    }
    
    @Test
    public void testAddItem()
    {
        int quantity = 3;
        ItemDTO item = ctrl.addItem(id[6], quantity);
        double price = item.getPrice().getAmount() * quantity;
        double priceWithTax = price+price*(double)item.getVATax()/100;
        double result = ctrl.updateRunningTotal().getAmount();
        
        assertEquals(priceWithTax,result,"not Same");
    }
    
    @Test
    public void testAddNullItemSingle() {
        ItemID itemID = null;
        
        ItemDTO expResult = null;
        ItemDTO result = ctrl.addItemSingle(itemID);
        assertEquals(expResult, result,"return != null ");
        assertEquals(0,ctrl.updateRunningTotal().getAmount(),"some sale info was edited");
    }

    
    @Disabled
    @Test
    public void testPayment() {

        Amount paid = null;
        Amount expResult = null;
        Amount result = ctrl.Payment(paid);
        assertEquals(expResult, result);
    }
    
}
