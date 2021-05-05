
package se.kth.iv1350.iv1350upg.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import se.kth.iv1350.iv1350upg.integration.Amount;

public class CashRegisterTest {
    
    
    public CashRegisterTest() {
    }
    private CashRegister testCash;
    private final double startValue=100;
    private Amount startAmount;
    
    @BeforeEach
    public void setUp() {
        startAmount=new Amount(startValue);
        testCash=new CashRegister(startAmount);
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddAmountToCashRegister() {
        double added=50;
        Amount addTest=new Amount(added);
        testCash.addAmountToCashRegister(addTest);
        double expResult = startValue+added;
        double result = testCash.getCashInRegister().getAmount();
        assertEquals(expResult,result,"Amount not added correctly");
    }

    @Test
    public void testAddNegativ()
    {
        double added=-50;
        Amount addTest=new Amount(added);
        testCash.addAmountToCashRegister(addTest);
        double expResult = startValue+added;
        double result = testCash.getCashInRegister().getAmount();
        assertEquals(expResult,result,"Amount not added correctly");
    }
    
    @Disabled
    @Test
    public void testAddNull()
    {
        Amount addTest=null;
        testCash.addAmountToCashRegister(addTest);
        double expResult = startValue;
        double result = testCash.getCashInRegister().getAmount();
        assertEquals(expResult,result,"added amount when null");
    }
}
