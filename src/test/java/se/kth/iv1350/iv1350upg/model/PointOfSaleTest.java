
package se.kth.iv1350.iv1350upg.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PointOfSaleTest {
    
    public PointOfSaleTest() {
    }
    PointOfSale testPOS;
    
    @BeforeEach
    public void setUp() {
        testPOS=new PointOfSale();
    }
    
    @AfterEach
    public void tearDown() {
        testPOS=null;
    }

    @Test
    public void testGetPrinter() {
        
        boolean expResult = true, result = false;        
        if(testPOS.getPrinter()!=null)
        {
            result=true;
        }
        assertEquals(expResult,result,"printer is null");
    }

    @Test
    public void testGetStore() {
        boolean expResult = true, result = false;        
        if(testPOS.getStore()!=null)
        {
            result=true;
        }
        assertEquals(expResult,result,"store is null");
    }

    @Test
    public void testGetCashRegister() {
        boolean expResult = true, result = false;        
        if(testPOS.getCashRegister()!=null)
        {
            result=true;
        }
        assertEquals(expResult,result,"cashRegister is null");
    }

    @Test
    public void testGetExternalSystems() {
        boolean expResult = true, result = false;        
        if(testPOS.getExternalSystems()!=null)
        {
            result=true;
        }
        assertEquals(expResult,result,"externalSystems is null");
    }    
    
    
    
    
}
