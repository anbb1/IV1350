
package se.kth.iv1350.iv1350upg.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class AmountTest {
    
    public AmountTest() {
    }
    Amount testAmount;
    double startAmount=1.2;
    @BeforeEach
    public void setUp() {
        testAmount= new Amount(startAmount);
    }
    
    @AfterEach
    public void tearDown() {
        testAmount=null;
    }

    @Test
    public void testNewAmount() {
        testAmount= new Amount(2.5);
        double expResult=2.5;
        double result = testAmount.getAmount();
        assertEquals(expResult,result,"not same amount");
    }
    
    @Test
    public void testOldAmount(){
        double expResult=startAmount;
        double result = testAmount.getAmount();
        assertEquals(expResult,result,"not same amount");
    }
    
    @Test
    public void testAddedAmounts(){
        testAmount= new Amount(startAmount + 2.5);
        double expResult=startAmount + 2.5;
        double result = testAmount.getAmount();
        assertEquals(expResult,result,"not same amount");
    }   
    
    
}
